package jDiff2Html;

import java.io.IOException;

public class CommandLineRunner
{
    public static void main( String[] args )
    {
        final JDiff2Html jDiff2Html = new JDiff2Html(new DiffReader(), new PageRenderer(new FileDiffRenderer(), new VelocityRenderer()));
        try {
            jDiff2Html.transform(System.in, System.out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
