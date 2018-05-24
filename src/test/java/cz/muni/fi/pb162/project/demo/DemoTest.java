package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.Demo;
import cz.muni.fi.pb162.project.helper.OutputTester;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testing print output of Demo class.
 *
 * @author Marek Sabo
 */
public class DemoTest {

    private static final String EXPECTED_OUTPUT = "Hello world!" + System.lineSeparator();

    @Test
    public void testMainOutput() {
        assertThat(actualOutput()).isEqualTo(EXPECTED_OUTPUT);
    }

    @SuppressWarnings("AccessStaticViaInstance")
    private String actualOutput() {
        OutputTester ot = new OutputTester();
        ot.captureOutput();
        new Demo().main(null);
        ot.releaseOutput();
        return ot.getOutput();
    }

}
