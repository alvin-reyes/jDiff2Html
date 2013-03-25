package jDiff2Html;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;

public class VelocityRenderer {
    public static final String TEMPLATE_PATH = "templates/";

    public String renderTemplate(String templateName, VelocityContext context) {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();

        Template t = ve.getTemplate(TEMPLATE_PATH + templateName);
        final StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();
    }
}
