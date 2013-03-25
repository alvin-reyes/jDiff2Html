package jDiff2Html;

import jDiff2Html.model.FileDiff;
import org.apache.velocity.VelocityContext;

import java.util.List;

public class PageRenderer {
    public static final String TEMPLATE_NAME = "page.vm";
    public static final String FILE_DIFFS_KEY = "fileDiffs";
    public static final String HEADER_INFO_KEY = "headerInfo";

    private FileDiffRenderer fileDiffRenderer;
    private VelocityRenderer velocityRenderer;

    public PageRenderer(FileDiffRenderer fileDiffRenderer, VelocityRenderer velocityRenderer) {
        this.fileDiffRenderer = fileDiffRenderer;
        this.velocityRenderer = velocityRenderer;
    }

    public String render(String headerInfo, List<FileDiff> fileDiffs) {
        return velocityRenderer.renderTemplate(TEMPLATE_NAME, setUpContext(headerInfo, fileDiffs));
    }

    private VelocityContext setUpContext(String headerInfo, List<FileDiff> fileDiffs) {
        final VelocityContext context = new VelocityContext();
        context.put(HEADER_INFO_KEY, headerInfo);
        context.put(FILE_DIFFS_KEY, renderFileDiffs(fileDiffs));
        return context;
    }

    private String renderFileDiffs(List<FileDiff> fileDiffs) {
        StringBuilder stringBuilder = new StringBuilder();
        for (FileDiff fileDiff : fileDiffs) {
            stringBuilder.append(fileDiffRenderer.render(fileDiff));
        }

        return stringBuilder.toString();
    }
}
