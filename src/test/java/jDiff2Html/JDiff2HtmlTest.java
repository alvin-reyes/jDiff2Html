package jDiff2Html;

import jDiff2Html.model.FileDiff;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class JDiff2HtmlTest {

    @Mock
    DiffReader reader;

    @Mock
    PageRenderer renderer;
    private JDiff2Html jDiff2Html;

    @Before
    public void setUp() {
        initMocks(this);
        jDiff2Html = new JDiff2Html(reader, renderer);
    }

    @Test
    public void shouldTransformInputToOutput() throws Exception {
        final String input = "some input";
        final String expectedOutput = "ItHasBeenTransformed!";
        List<FileDiff> fileDiffs = new ArrayList<FileDiff>();

        when(reader.read(input)).thenReturn(fileDiffs);
        when(renderer.render(any(String.class), eq(fileDiffs))).thenReturn(expectedOutput);

        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        jDiff2Html.transform(in, out);

        assertThat(out.toString(), equalTo(expectedOutput));
    }

    @Test
    public void shouldReadInOptionalHeaderFile() throws Exception {
        final String input = "some input";
        final String expectedOutput = "ItHasBeenTransformed!";
        final String headerInfo = "headerInfo";
        List<FileDiff> fileDiffs = new ArrayList<FileDiff>();

        when(reader.read(input)).thenReturn(fileDiffs);
        when(renderer.render(any(String.class), eq(fileDiffs))).thenReturn(expectedOutput);

        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream headerInfoStream = new ByteArrayInputStream(headerInfo.getBytes());

        jDiff2Html.transform(in, out, headerInfoStream);

        verify(renderer).render(headerInfo, fileDiffs);
    }

    @Test
    public void shouldGiveTheRendererAnEmptyStringIfNotGivenAHeaderFile() throws Exception {
        final String input = "some input";
        final String expectedOutput = "ItHasBeenTransformed!";
        List<FileDiff> fileDiffs = new ArrayList<FileDiff>();

        when(reader.read(input)).thenReturn(fileDiffs);
        when(renderer.render(any(String.class), eq(fileDiffs))).thenReturn(expectedOutput);

        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        jDiff2Html.transform(in, out, null);

        verify(renderer).render("", fileDiffs);
    }
}
