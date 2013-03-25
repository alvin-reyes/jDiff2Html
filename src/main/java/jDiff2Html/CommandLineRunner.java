package jDiff2Html;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CommandLineRunner
{
    private JDiff2Html jDiff2Html;

    public CommandLineRunner(JDiff2Html jDiff2Html) {
        this.jDiff2Html = jDiff2Html;
    }

    public static void main( String[] args )
    {
        final JDiff2Html transformer = injectDependencies();
        new CommandLineRunner(transformer).run(args);
    }

    private static JDiff2Html injectDependencies() {
        final DiffReader diffReader = new DiffReader();
        final PageRenderer pageRenderer = new PageRenderer(new FileDiffRenderer(), new VelocityRenderer());
        return new JDiff2Html(diffReader, pageRenderer);
    }

    public void run(String[] args) {
        try {
            if (args.length != 1) {
                jDiff2Html.transform(System.in, System.out);
            } else {
                jDiff2Html.transform(System.in, System.out, new FileInputStream(args[0]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
