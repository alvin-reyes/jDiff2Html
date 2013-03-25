package jDiff2Html;

import jDiff2Html.model.FileDiff;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;

public class JDiff2Html {
    private DiffReader diffReader;
    private PageRenderer diffRenderer;

    public JDiff2Html(DiffReader diffReader, PageRenderer diffRenderer) {
        this.diffReader = diffReader;
        this.diffRenderer = diffRenderer;
    }

    public void transform(InputStream in, OutputStream out) throws IOException {
        final OutputStreamWriter streamWriter = new OutputStreamWriter(out);

        try {
            final List<FileDiff> fileDiffs = diffReader.read(IOUtils.toString(in, "UTF-8"));
            final String renderedHtml = diffRenderer.render(fileDiffs);
            streamWriter.write(renderedHtml);
        } finally {
            streamWriter.close();
        }


    }
}
