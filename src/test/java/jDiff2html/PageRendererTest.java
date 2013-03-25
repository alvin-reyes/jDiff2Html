package jDiff2Html;

import jDiff2Html.model.FileDiff;
import jDiff2Html.model.LineDiff;
import org.apache.velocity.VelocityContext;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PageRendererTest {

    @Mock
    private FileDiffRenderer mockFileDiffRenderer;
    @Mock
    private VelocityRenderer mockVelocityRenderer;

    @Test
    public void shouldRenderPage() throws Exception {
        initMocks(this);

        String expectedOutput = "someHtmlGunk";
        final PageRenderer pageRenderer = new PageRenderer(mockFileDiffRenderer, mockVelocityRenderer);

        FileDiff fileDiff = new FileDiff("a", "b", "c", "123", "@11 22@", new ArrayList<LineDiff>());
        List<FileDiff> fileDiffs = new ArrayList<FileDiff>();
        fileDiffs.add(fileDiff);

        final String fileDiffRenderedOutput = "fileDiffRenderedOutput";
        when(mockFileDiffRenderer.render(fileDiff)).thenReturn(fileDiffRenderedOutput);

        ArgumentCaptor<VelocityContext> contextCaptor = ArgumentCaptor.forClass(VelocityContext.class);
        when(mockVelocityRenderer.renderTemplate(eq("page.vm"), contextCaptor.capture())).thenReturn(expectedOutput);

        final String output = pageRenderer.render(fileDiffs);
        assertThat(output, equalTo(expectedOutput));

        final VelocityContext context = contextCaptor.getValue();
        assertThat((String) context.get("fileDiffs"), equalTo(fileDiffRenderedOutput));
    }


}
