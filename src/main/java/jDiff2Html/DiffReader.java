package jDiff2Html;

import jDiff2Html.model.FileDiff;
import jDiff2Html.model.LineDiff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class DiffReader {
    public static final String INDEX_LINE_PREFIX = "Index: ";

    public List<FileDiff> read(String diffText) {
        String[] lines = readLines(diffText);
        List<List<String>> fileDiffRawTextBlocks = separateFileDiffRawTextBlocks(lines);

        final ArrayList<FileDiff> fileDiffs = new ArrayList<FileDiff>();
        for (List<String> fileDiffRawTextBlock : fileDiffRawTextBlocks) {
            fileDiffs.add(createFileDiff(fileDiffRawTextBlock));
        }

        return fileDiffs;
    }

    private FileDiff createFileDiff(List<String> fileDiffRawTextBlock) {
        final String index = fileDiffRawTextBlock.get(0);
        final String separator = fileDiffRawTextBlock.get(1);
        final String fromRevision = fileDiffRawTextBlock.get(2);
        final String toRevision = fileDiffRawTextBlock.get(3);
        final List<LineDiff> lines = createLineDiffs(fileDiffRawTextBlock.subList(4, fileDiffRawTextBlock.size()));

        return new FileDiff(index, separator, fromRevision, toRevision, lines);
    }

    private List<LineDiff> createLineDiffs(List<String> lines) {
        ArrayList<LineDiff> lineDiffs = new ArrayList<LineDiff>();
        for (String line : lines) {
            lineDiffs.add(new LineDiff(line));
        }

        return lineDiffs;
    }

    private String[] readLines(String diffText) {
        List<String> lines = new ArrayList<String>();
        final BufferedReader reader = new BufferedReader(new StringReader(diffText));
        do {
            final String line;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (line == null) break;
            lines.add(line);
        } while (true);
        return lines.toArray(new String[lines.size()]);
    }

    private List<List<String>> separateFileDiffRawTextBlocks(String[] lines) {
        List<List<String>> fileDiffRawTextBlocks = new ArrayList<List<String>>();
        List<String> currentBlock = new ArrayList<String>();
        for (int i = 0; i < lines.length; i++) {
            currentBlock.add(lines[i]);
            if (i + 1 >= lines.length || isIndexLine(lines[i + 1])) {
                fileDiffRawTextBlocks.add(currentBlock);
                currentBlock = new ArrayList<String>();
            }
        }
        return fileDiffRawTextBlocks;
    }

    private boolean isIndexLine(String line) {
        return line.startsWith(INDEX_LINE_PREFIX);
    }
}
