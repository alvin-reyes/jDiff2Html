package jDiff2html;

import model.FileDiff;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

public class FileDiffRenderer {
    public String render(FileDiff file) {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, "src/main/templates");
        ve.init();

        Template t = ve.getTemplate("fileDiff.vm");

        final VelocityContext context = new VelocityContext();
        context.put("file", file);

        final StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();
    }
}
