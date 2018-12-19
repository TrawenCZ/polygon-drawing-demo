package cz.muni.fi.pb162.project.utils;

import cz.muni.fi.pb162.project.geometry.Circle;
import cz.muni.fi.pb162.project.geometry.Measurable;
import cz.muni.fi.pb162.project.geometry.Triangle;
import cz.muni.fi.pb162.project.geometry.Vertex2D;
import cz.muni.fi.pb162.project.helper.OutputTester;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Testing print output.
 *
 * @author Marek Sabo
 */
public class GaugerTest {

    private final Triangle triangle = new Triangle(
            new Vertex2D(-100, -100),
            new Vertex2D(100, 100),
            new Vertex2D(0, 0)
    );

    @Test
    public void printsTriangle() {
        expectedMeasurable(triangle, true);
    }

    @Test
    public void printsMeasurable() {
        expectedMeasurable(triangle, false);
        expectedMeasurable(new Circle(new Vertex2D(10, 0), 50), false);
    }

    private void expectedMeasurable(Measurable measurable, boolean isTriangle) {
        String triangleString = getTriangleString(measurable, isTriangle);
        String measurableString = getMeasurableString(measurable);

        assertThat(printOutput(measurable, isTriangle)).isEqualTo(triangleString + measurableString);
    }

    private String getTriangleString(Measurable measurable, boolean isTriangle) {
        return isTriangle ? measurable.toString() + System.lineSeparator() : "";
    }

    private String getMeasurableString(Measurable measurable) {
        return "Width: " + measurable.getWidth() + System.lineSeparator() +
                "Height: " + measurable.getHeight() + System.lineSeparator();
    }

    private String printOutput(Measurable measurable, boolean isTriangle) {
        OutputTester ot = new OutputTester();
        ot.captureOutput();
        if (isTriangle) {
            Gauger.printMeasurement((Triangle) measurable);
        } else {
            Gauger.printMeasurement(measurable);
        }
        ot.releaseOutput();
        return ot.getOutput();
    }

    @Test
    @SuppressWarnings("AccessStaticViaInstance")
    public void testClass() {
        new Gauger();
    }
}
