package model;

public class LineDiff {
    private String text;

    public LineDiff(String text) {
        this.text = text;
    }

    @ReadByTemplate
    public String getText() {
        return text;
    }

    public LineType getType() {
        if (text.length() == 0) return LineType.Context;

        final char firstCharacter = text.charAt(0);
        switch (firstCharacter) {
            case '+':
                return LineType.Plus;
            case  '-':
                return LineType.Minus;
            default:
                return LineType.Context;
        }
    }
}
