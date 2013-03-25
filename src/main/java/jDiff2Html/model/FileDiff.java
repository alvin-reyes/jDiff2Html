package jDiff2Html.model;

import java.util.List;

public class FileDiff {
    private String index;
    private String separator;
    private String fromRevision;
    private String toRevision;
    private List<LineDiff> lines;

    public FileDiff(String index, String separator, String fromRevision, String toRevision, List<LineDiff> lines) {
        this.index = index;
        this.separator = separator;
        this.fromRevision = fromRevision;
        this.toRevision = toRevision;
        this.lines = lines;
    }

    @ReadByTemplate
    public String getIndex() {
        return index;
    }

    @ReadByTemplate
    public String getSeparator() {
        return separator;
    }

    @ReadByTemplate
    public String getFromRevision() {
        return fromRevision;
    }

    @ReadByTemplate
    public String getToRevision() {
        return toRevision;
    }

    @ReadByTemplate
    public List<LineDiff> getLines() {
        return lines;
    }
}
