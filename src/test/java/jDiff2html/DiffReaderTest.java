package jDiff2Html;

import jDiff2Html.model.FileDiff;
import jDiff2Html.model.LineDiff;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@Ignore("WIP")
public class DiffReaderTest {
    @Test
    public void shouldReadASingleFileDiffAndCorrectlyParseHeaderFields() throws Exception {
        final String index = "Index: PREnvironment/Configuration/Environment/Applications.json";
        final String separator = "===================================================================";
        final String fromRevision = "--- PREnvironment/Configuration/Environment/Applications.json   (revision 1713)";
        final String toRevision = "+++ PREnvironment/Configuration/Environment/Applications.json   (revision 1721)";
        final String lineNumbers = "@@ -2708,7 +2708,7 @@";
        final String line1 = "  abc";
        final String line2 = "+ def";
        final String line3 = "- ghi";

        String diffText = index + "\n" +
                separator + "\n" +
                fromRevision + "\n" +
                toRevision + "\n" +
                lineNumbers + "\n" +
                line1 + "\n" +
                line2 + "\n" +
                line3 + "\n";

        List<FileDiff> fileDiffs = new DiffReader().read(diffText);

        assertThat(fileDiffs.size(), equalTo(1));
        final FileDiff fileDiff = fileDiffs.get(0);
        assertThat(fileDiff.getIndex(), equalTo(index));
        assertThat(fileDiff.getSeparator(), equalTo(separator));
        assertThat(fileDiff.getFromRevision(), equalTo(fromRevision));
        assertThat(fileDiff.getToRevision(), equalTo(toRevision));
        assertThat(fileDiff.getLineNumbers(), equalTo(lineNumbers));

        assertThat(fileDiff.getLines().size(), equalTo(3));
    }

    @Test
    public void shouldReadASingleFileDiffAndCorrectlyParseLines() throws Exception {
        final String index = "Index: PREnvironment/Configuration/Environment/Applications.json";
        final String separator = "===================================================================";
        final String fromRevision = "--- PREnvironment/Configuration/Environment/Applications.json   (revision 1713)";
        final String toRevision = "+++ PREnvironment/Configuration/Environment/Applications.json   (revision 1721)";
        final String lineNumbers = "@@ -2708,7 +2708,7 @@";
        final String line1 = "  abc";
        final String line2 = "+ def";
        final String line3 = "- ghi";

        String diffText = index + "\n" +
                separator + "\n" +
                fromRevision + "\n" +
                toRevision + "\n" +
                lineNumbers + "\n" +
                line1 + "\n" +
                line2 + "\n" +
                line3 + "\n";

        List<FileDiff> fileDiffs = new DiffReader().read(diffText);

        final FileDiff fileDiff = fileDiffs.get(0);

        final List<LineDiff> lines = fileDiff.getLines();
        assertThat(lines.size(), equalTo(3));
        assertThat(lines.get(0).getText(), equalTo(line1));
        assertThat(lines.get(1).getText(), equalTo(line2));
        assertThat(lines.get(2).getText(), equalTo(line3));
    }

    @Test
    public void shouldReadMultipleFileDiffs() throws Exception {
        String diffText = "Index: PREnvironment/Configuration/Environment/Applications.json\n" +
                "===================================================================\n" +
                "--- PREnvironment/Configuration/Environment/Applications.json   (revision 1713)\n" +
                "+++ PREnvironment/Configuration/Environment/Applications.json   (revision 1721)\n" +
                "@@ -2708,7 +2708,7 @@\n" +
                "               \"history-log-guid\": \"00931A84-D7D7-10FF-9E1B-AC1C8765AA77\",\n" +
                "               \"history-log-version\": \"2.0\",\n" +
                "               \"history-log-created\": \"1358944215\",\n" +
                "-              \"history-log-minid\": \"929305\",\n" +
                "+              \"history-log-minid\": \"929340\",\n" +
                "               \"database-guid\": \"0089DB36-01F4-1F57-B946-AC1C8765AA77\"\n" +
                "             },\n" +
                "             \"db_tls_cs01_pr1_p\": {\n" +
                "Index: PREnvironment/export_info.txt\n" +
                "===================================================================\n" +
                "--- PREnvironment/export_info.txt       (revision 1713)\n" +
                "+++ PREnvironment/export_info.txt       (revision 1721)\n" +
                "@@ -1,6 +1,6 @@\n" +
                " Export:          PREnvironment\n" +
                " Server:          ctizgclmipr001.ccem.tcxf.in.telstra.com.au:4000\n" +
                "-Export Started:  Thu, 21 Mar 2013 18:05:05 +1100\n" +
                "-Export Finished: Thu, 21 Mar 2013 18:09:17 +1100\n" +
                "+Export Started:  Thu, 21 Mar 2013 20:13:22 +1100\n" +
                "+Export Finished: Thu, 21 Mar 2013 20:17:35 +1100\n" +
                " Run from:        W0000021703718\n" +
                " SVN Revision:    $Rev$";

        List<FileDiff> fileDiffs = new DiffReader().read(diffText);

        assertThat(fileDiffs.size(), equalTo(2));
    }
}