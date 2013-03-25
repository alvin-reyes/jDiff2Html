package jDiff2Html;

import jDiff2Html.model.FileDiff;
import org.apache.velocity.VelocityContext;

import java.util.List;

public class PageRenderer {
    public static final String TEMPLATE_NAME = "page.vm";

    private FileDiffRenderer fileDiffRenderer;
    private VelocityRenderer velocityRenderer;

    public PageRenderer(FileDiffRenderer fileDiffRenderer, VelocityRenderer velocityRenderer) {
        this.fileDiffRenderer = fileDiffRenderer;
        this.velocityRenderer = velocityRenderer;
    }

    public String render(List<FileDiff> fileDiffs) {
        return velocityRenderer.renderTemplate(TEMPLATE_NAME, setUpContext(fileDiffs));
    }

    private VelocityContext setUpContext(List<FileDiff> fileDiffs) {
        final VelocityContext context = new VelocityContext();
        context.put("fileDiffs", renderFileDiffs(fileDiffs));
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
