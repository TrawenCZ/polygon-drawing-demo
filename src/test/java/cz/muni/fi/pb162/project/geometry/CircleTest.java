package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests Circle class.
 *
 * @author Marek Sabo
 */
public class CircleTest {

    private static final Vertex2D CENTER = new Vertex2D(2, 1);
    private static final double RADIUS = 2.5;
    private Circle circle;

    @Before
    public void setUp() {
        circle = new Circle(CENTER, RADIUS);
    }

    @Test
    public void attributes2AndFinal() {
        BasicRulesTester.attributesAmount(Circle.class, 2);
        BasicRulesTester.attributesFinal(Circle.class);
    }

    @Test
    public void getters() {
        assertThat(circle.getRadius()).isEqualTo(RADIUS);
        assertThat(circle.getCenter()).isEqualToComparingFieldByField(CENTER);
    }

    @Test
    public void constructorWithoutParameters() {
        Circle actual = new Circle();
        assertThat(actual.getRadius()).isEqualTo(1);
        assertThat(actual.getCenter()).isEqualToComparingFieldByField(new Vertex2D(0, 0));
    }

    @Test
    public void toStringMessage() {
        assertThat(circle.toString())
                .isEqualTo("Circle: center=" + circle.getCenter() + ", radius=" + circle.getRadius());
    }
}
