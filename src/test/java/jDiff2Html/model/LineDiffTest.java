package jDiff2Html.model;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LineDiffTest {
    @Test
    public void shouldReturnTypeAsContext() throws Exception {
        final LineDiff lineDiff = new LineDiff("some text");
        assertThat(lineDiff.getType(), equalTo(LineType.Context));
    }

    @Test
    public void shouldReturnTypeAsPlus() throws Exception {
        final LineDiff lineDiff = new LineDiff("+ some text");
        assertThat(lineDiff.getType(), equalTo(LineType.Plus));
    }

    @Test
    public void shouldReturnTypeAsMinus() throws Exception {
        final LineDiff lineDiff = new LineDiff("- some text");
        assertThat(lineDiff.getType(), equalTo(LineType.Minus));
    }

    @Test
    public void shouldReturnTypeAsLineNumbers() throws Exception {
        final LineDiff lineDiff = new LineDiff("@@ -23,5 +24,6 @@");
        assertThat(lineDiff.getType(), equalTo(LineType.LineNumbers));
    }
    @Test
    public void shouldDealWithEmptyString() throws Exception {
        final LineDiff lineDiff = new LineDiff("");
        assertThat(lineDiff.getType(), equalTo(LineType.Context));
    }
}
