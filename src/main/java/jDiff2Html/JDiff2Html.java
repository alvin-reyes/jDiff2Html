package jDiff2Html;

import jDiff2Html.model.FileDiff;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;

public class JDiff2Html {
    public static final String ENCODING = "UTF-8";
    private DiffReader diffReader;
    private PageRenderer diffRenderer;

    public JDiff2Html(DiffReader diffReader, PageRenderer diffRenderer) {
        this.diffReader = diffReader;
        this.diffRenderer = diffRenderer;
    }

    public void transform(InputStream in, OutputStream out) throws IOException {
        transform(in, out, null);
    }

    public void transform(InputStream in, OutputStream out, InputStream headerInfoStream) throws IOException {
        final OutputStreamWriter streamWriter = new OutputStreamWriter(out);

        try {
            final List<FileDiff> fileDiffs = diffReader.read(IOUtils.toString(in, ENCODING));
            final String renderedHtml = diffRenderer.render(getHeaderInfoText(headerInfoStream), fileDiffs);
            streamWriter.write(renderedHtml);
        } finally {
            streamWriter.close();
        }
    }

    private String getHeaderInfoText(InputStream headerInfoStream) throws IOException {
        if (headerInfoStream == null) return "";

        return IOUtils.toString(headerInfoStream, ENCODING);
    }
}
