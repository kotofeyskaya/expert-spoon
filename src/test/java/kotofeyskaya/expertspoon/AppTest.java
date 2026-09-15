package kotofeyskaya.expertspoon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private static final String EXPECTED_OUTPUT = "Hello World!" + System.lineSeparator();
    private static final Object STDOUT_LOCK = new Object();

    @Test
    public void mainPrintsHelloWorldWithNoArgs() {
        assertEquals(EXPECTED_OUTPUT, runAppAndCaptureOutput(new String[0]));
    }

    @Test
    public void mainPrintsHelloWorldWithSingleArg() {
        assertEquals(EXPECTED_OUTPUT, runAppAndCaptureOutput(new String[] {"ignored"}));
    }

    @Test
    public void mainPrintsHelloWorldWithMultipleArgs() {
        assertEquals(EXPECTED_OUTPUT, runAppAndCaptureOutput(new String[] {"a", "b", "c"}));
    }

    @Test
    public void mainAcceptsNullArgs() {
        assertEquals(EXPECTED_OUTPUT, runAppAndCaptureOutput(null));
    }

    @Test
    public void mainCanBeInvokedRepeatedly() {
        assertEquals(EXPECTED_OUTPUT, runAppAndCaptureOutput(new String[0]));
        assertEquals(EXPECTED_OUTPUT, runAppAndCaptureOutput(new String[0]));
    }

    @Test
    public void mainAcceptsArrayContainingNullElement() {
        assertEquals(EXPECTED_OUTPUT, runAppAndCaptureOutput(new String[] {"first", null, "third"}));
    }

    @Test
    public void mainHandlesLargeArgumentArray() {
        String[] args = new String[100];
        for (int i = 0; i < args.length; i++) {
            args[i] = "arg-" + i;
        }
        assertEquals(EXPECTED_OUTPUT, runAppAndCaptureOutput(args));
    }

    @Test
    public void mainDoesNotMutateProvidedArguments() {
        String[] args = new String[] {"alpha", "beta"};
        String[] original = args.clone();

        runAppAndCaptureOutput(args);

        assertArrayEquals(original, args);
    }

    @Test
    public void mainDoesNotReplaceSystemOut() {
        synchronized (STDOUT_LOCK) {
            ByteArrayOutputStream stdout = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            PrintStream captureOut = new PrintStream(stdout);

            try {
                System.setOut(captureOut);
                App.main(new String[0]);
                assertSame(captureOut, System.out);
            } finally {
                System.setOut(originalOut);
            }
        }
    }

    @Test
    public void mainDoesNotWriteToSystemErr() {
        assertTrue(runAppAndCaptureStreams(new String[0])[1].isEmpty());
    }

    private String runAppAndCaptureOutput(String[] args) {
        return runAppAndCaptureStreams(args)[0];
    }

    private String[] runAppAndCaptureStreams(String[] args) {
        synchronized (STDOUT_LOCK) {
            ByteArrayOutputStream stdout = new ByteArrayOutputStream();
            ByteArrayOutputStream stderr = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            PrintStream originalErr = System.err;

            try {
                System.setOut(new PrintStream(stdout));
                System.setErr(new PrintStream(stderr));
                assertDoesNotThrow(() -> App.main(args));
            } finally {
                System.setOut(originalOut);
                System.setErr(originalErr);
            }
            return new String[] {
                stdout.toString(StandardCharsets.UTF_8),
                stderr.toString(StandardCharsets.UTF_8)
            };
        }
    }
}
