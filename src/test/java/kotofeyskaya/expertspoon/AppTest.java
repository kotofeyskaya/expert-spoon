package kotofeyskaya.expertspoon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private static final String EXPECTED_OUTPUT = "Hello World!" + System.lineSeparator();

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

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
    public void mainOutputStartsWithHello() {
        assertTrue(runAppAndCaptureOutput(new String[0]).startsWith("Hello"));
    }

    @Test
    public void mainOutputEndsWithLineSeparator() {
        assertTrue(runAppAndCaptureOutput(new String[0]).endsWith(System.lineSeparator()));
    }

    @Test
    public void mainOutputContainsWorld() {
        assertTrue(runAppAndCaptureOutput(new String[0]).contains("World"));
    }

    @Test
    public void mainDoesNotThrow() {
        assertDoesNotThrow(() -> runAppAndCaptureOutput(new String[0]));
    }

    private String runAppAndCaptureOutput(String[] args) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(output));
            App.main(args);
        } finally {
            System.setOut(originalOut);
        }
        return output.toString();
    }
}
