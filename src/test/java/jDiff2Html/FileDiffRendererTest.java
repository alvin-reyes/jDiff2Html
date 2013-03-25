package jDiff2Html;

import jDiff2Html.model.FileDiff;
import jDiff2Html.model.LineDiff;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;


public class FileDiffRendererTest {
    @Test
    public void shouldRenderHeaderOfFileDiffSection() throws Exception {
        final String index = "Index: path/to/file";
        final String separator = "===================================================================";
        final String fromRevision = "--- path/to/file\t(revision 1721)";
        final String toRevision = "+++ path/to/file\t(revision 1722)";

        final FileDiff fileDiff = new FileDiff(index, separator, fromRevision, toRevision, new ArrayList<LineDiff>());

        String expectedOutput =
                "<div class=\"filecontainer\">\r\n" +
                        "<div class=\"line file\">" + index + "</div>\r\n" +
                        "<div class=\"line separator\">" + separator + "</div>\r\n" +
                        "<div class=\"line minus3\">" + fromRevision + "</div>\r\n" +
                        "<div class=\"line plus3\">" + toRevision + "</div>\r\n" +
                "</div>";

        FileDiffRenderer renderer = new FileDiffRenderer();

        String output = renderer.render(fileDiff);

        assertThat(output, equalTo(expectedOutput));
    }

    @Test
    public void shouldRenderFileDiffSection() throws Exception {
        final String index = "";
        final String separator = "";
        final String fromRevision = "";
        final String toRevision = "";
        final String lineNumbers = "@@ -56,6 +57,7 @@";
        final String line1 = "somecontextline";
        final String line2 = "+            somePlusLine";
        final String line3 = "-           someMinusLine";
        final String line4 = "otherContextLine";
        final List<LineDiff> lines = Arrays.asList(
                new LineDiff(lineNumbers),
                new LineDiff(line1),
                new LineDiff(line2),
                new LineDiff(line3),
                new LineDiff(line4)
        );

        final FileDiff fileDiff = new FileDiff(index, separator, fromRevision, toRevision, lines);

        String expectedOutput =
                "<div class=\"filecontainer\">\r\n" +
                        "<div class=\"line file\">" + index + "</div>\r\n" +
                        "<div class=\"line separator\">" + separator + "</div>\r\n" +
                        "<div class=\"line minus3\">" + fromRevision + "</div>\r\n" +
                        "<div class=\"line plus3\">" + toRevision + "</div>\r\n" +
                        "<div class=\"line LineNumbers\">" + lineNumbers + "</div>\r\n" +
                        "<div class=\"line Context\">" + line1 + "</div>\r\n" +
                        "<div class=\"line Plus\">" + line2 + "</div>\r\n" +
                        "<div class=\"line Minus\">" + line3 + "</div>\r\n" +
                        "<div class=\"line Context\">" + line4 + "</div>\r\n" +
                "</div>";

        FileDiffRenderer renderer = new FileDiffRenderer();

        String output = renderer.render(fileDiff);

        assertThat(output, equalTo(expectedOutput));
    }
}