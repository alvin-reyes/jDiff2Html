package jDiff2Html;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class CommandLineRunnerTest {
    @Mock
    JDiff2Html mockJDiff2Html;
    private File tempFile;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        tempFile = File.createTempFile(UUID.randomUUID().toString(), "txt");
    }

    @Test
    public void shouldRunWithoutHeaderFile() throws Exception {

        new CommandLineRunner(mockJDiff2Html).run(new String[]{});

        verify(mockJDiff2Html).transform(System.in, System.out);
    }

    @Test
    public void shouldRunWithHeaderFile() throws Exception {
        new CommandLineRunner(mockJDiff2Html).run(new String[]{ tempFile.getAbsolutePath() });

        verify(mockJDiff2Html).transform(any(InputStream.class), any(OutputStream.class), any(InputStream.class));
    }

    @After
    public void tearDown() throws Exception {
        tempFile.delete();
    }
}
