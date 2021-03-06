package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Simple Vertex2D tests.
 * @author Marek Sabo
 */
public class Vertex2DTest {
    private Vertex2D vertex2D;

    private static final double X = -1.2;
    private static final double Y = 2.4;
    private static final double DELTA = 0.001;

    @Before
    public void setUp() {
        vertex2D = createVertex(X, Y);
    }

    private Vertex2D createVertex(double x, double y) {
        Vertex2D v = new Vertex2D();
        v.setX(x);
        v.setY(y);
        return v;
    }

    @Test
    public void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Vertex2D.class, 2);
        BasicRulesTester.methodsAmount(Vertex2D.class, 7);
    }

    @Test
    public void gettersAndSetters() {
        assertThat(vertex2D.getX()).isEqualTo(X);
        assertThat(vertex2D.getY()).isEqualTo(Y);
    }

    @Test
    public void attributesAre0() {
        Vertex2D v = new Vertex2D();
        assertVertex(v, 0.0, 0.0);
    }

    @Test
    public void getInfo() {
        assertThat(vertex2D.getInfo()).isEqualTo("[" + X + ", " + Y + "]");
    }

    @Test
    public void sumCoordinates() {
        assertThat(vertex2D.sumCoordinates()).isCloseTo(X + Y, within(DELTA));
    }

    @Test
    public void moveVertex() {
        final double XX = -3.3;
        final double YY = -5.5;
        Vertex2D negativeVertex = createVertex(XX, YY);

        vertex2D.move(negativeVertex);

        assertVertex(vertex2D, XX + X, YY + Y);
        assertVertex(negativeVertex, XX, YY);
    }

    private void assertVertex(Vertex2D v, double x, double y) {
        assertThat(v.getX()).isCloseTo(x, within(DELTA));
        assertThat(v.getY()).isCloseTo(y, within(DELTA));
    }
}
