package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.helper.OutputTester;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testing print output of Demo class.
 *
 * @author Marek Sabo
 */
public class DemoTest {

    private static final String EXPECTED_OUTPUT =
            "{\n" +
                    "  \"vrchol a\": {\n" +
                    "    \"x\": -100.0,\n" +
                    "    \"y\": 0.0\n" +
                    "  },\n" +
                    "  \"vrchol b\": {\n" +
                    "    \"x\": 0.0,\n" +
                    "    \"y\": -100.0\n" +
                    "  },\n" +
                    "  \"vrchol c\": {\n" +
                    "    \"x\": 0.0,\n" +
                    "    \"y\": 100.0\n" +
                    "  },\n" +
                    "  \"vrchol d\": {\n" +
                    "    \"x\": -100.0,\n" +
                    "    \"y\": 200.0\n" +
                    "  },\n" +
                    "  \"vrchol e\": {\n" +
                    "    \"x\": -100.0,\n" +
                    "    \"y\": 200.0\n" +
                    "  },\n" +
                    "  \"vrchol x\": {\n" +
                    "    \"x\": 123.0,\n" +
                    "    \"y\": 456.0\n" +
                    "  }\n" +
                    "}Hello World!\n"
                            .replace("\n", System.lineSeparator());

    @Test
    public void testMainOutput() {
        assertThat(actualOutput()).isEqualTo(EXPECTED_OUTPUT);
    }

    @SuppressWarnings("AccessStaticViaInstance")
    private String actualOutput() {
        OutputTester ot = new OutputTester();
        ot.captureOutput();
        try {
            new Demo().main(null);
        } catch (IOException e) {
            System.err.println("IOException occured while running Demo class:");
            e.printStackTrace();
        }
        ot.releaseOutput();
        return ot.getOutput();
    }

}
