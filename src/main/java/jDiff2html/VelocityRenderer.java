package jDiff2Html;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

public class VelocityRenderer {
    public static final String TEMPLATE_PATH = "src/main/templates";

    public String renderTemplate(String templateName, VelocityContext context) {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, TEMPLATE_PATH);
        ve.init();

        Template t = ve.getTemplate(templateName);

        final StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();
    }
}
