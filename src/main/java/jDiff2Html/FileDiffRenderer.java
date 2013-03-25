package jDiff2Html;

import jDiff2Html.model.FileDiff;
import org.apache.velocity.VelocityContext;

public class FileDiffRenderer {
    private final VelocityRenderer velocityRenderer;

    public FileDiffRenderer() {
        velocityRenderer = new VelocityRenderer();
    }

    public String render(FileDiff file) {
        final String templateName = "fileDiff.vm";
        final VelocityContext context = new VelocityContext();
        context.put("file", file);

        return velocityRenderer.renderTemplate(templateName, context);
    }

}
