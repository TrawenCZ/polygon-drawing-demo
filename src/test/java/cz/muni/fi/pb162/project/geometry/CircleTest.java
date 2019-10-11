package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

/**
 * Tests Circle class.
 *
 * @author Marek Sabo
 */
public class CircleTest {

    private static final Vertex2D CENTER = new Vertex2D(2, 1);
    private static final double RADIUS = 2.5;
    private Circle circle;

    public static void assertCircle(Circular actual, Circular expected) {
        assertThat(actual.getRadius()).isCloseTo(expected.getRadius(), within(0.001));
        assertThat(actual.getCenter()).isEqualToComparingFieldByField(expected.getCenter());
    }

    @Before
    public void setUp() {
        circle = new Circle(CENTER, RADIUS);
    }

    @Test
    public void classAttributesMethods() {
        assertThat(circle).isInstanceOf(GeneralRegularPolygon.class);
        BasicRulesTester.attributesAmount(Circle.class, 0);
        BasicRulesTester.methodsAmount(Circle.class, 2);
    }

    @Test
    public void getNumEdgesIsMaxValue() {
        assertThat(circle.getNumEdges()).isEqualTo(Integer.MAX_VALUE);
    }

    @Test
    public void colorIsRed() {
        assertThat(circle.getColor()).isEqualTo(Color.RED);
    }

    @Test
    public void getEdgeLengthIsZero() {
        assertThat(circle.getEdgeLength()).isEqualTo(0.0);
    }

    @Test
    public void getters() {
        assertThat(circle.getRadius()).isEqualTo(RADIUS);
        assertThat(circle.getCenter()).isEqualToComparingFieldByField(CENTER);
    }

    @Test
    public void constructorWithoutParameters() {
        assertCircle(new Circle(), new Circle(new Vertex2D(0, 0), 1));
    }

    @Test
    public void toStringMessage() {
        assertThat(circle.toString())
                .isEqualTo("Circle: center=" + circle.getCenter() + ", radius=" + circle.getRadius());
    }

    @Test
    public void width() {
        assertThat(circle.getWidth()).isEqualTo(2 * RADIUS);
    }

    @Test
    public void height() {
        assertThat(circle.getHeight()).isEqualTo(2 * RADIUS);
    }
}
