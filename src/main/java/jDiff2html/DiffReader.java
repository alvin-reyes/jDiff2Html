package jDiff2Html;

import jDiff2Html.model.FileDiff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class DiffReader {
    public List<FileDiff> read(String diffText) {

        String[] lines = readLines(diffText);
        final ArrayList<FileDiff> fileDiffs = new ArrayList<FileDiff>();

        return fileDiffs;
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
        return (String[]) lines.toArray();
    }
}
