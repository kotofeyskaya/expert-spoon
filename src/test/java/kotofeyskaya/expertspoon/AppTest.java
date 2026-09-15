package kotofeyskaya.expertspoon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void mainPrintsHelloWorld() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(output));
            App.main(new String[0]);
        } finally {
            System.setOut(originalOut);
        }

        assertEquals("Hello World!" + System.lineSeparator(), output.toString());
    }

    @Test
    public void javaMathStillWorksForOnePlusOne() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void javaMathStillWorksForThreeTimesThree() {
        assertEquals(9, 3 * 3);
    }

    @Test
    public void emptyStringHasLengthZero() {
        assertEquals(0, "".length());
    }

    @Test
    public void helloContainsEll() {
        assertTrue("hello".contains("ell"));
    }

    @Test
    public void stringTrimRemovesOuterSpaces() {
        assertEquals("hello", " hello ".trim());
    }

    @Test
    public void toUpperCaseConvertsLetters() {
        assertEquals("HELLO", "hello".toUpperCase());
    }

    @Test
    public void startsWithMatchesPrefix() {
        assertTrue("maven".startsWith("ma"));
    }

    @Test
    public void endsWithMatchesSuffix() {
        assertTrue("junit".endsWith("nit"));
    }
}
