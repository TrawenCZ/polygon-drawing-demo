package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Simple Vertex2D tests.
 *
 * @author Marek Sabo
 */
public class Vertex2DTest {
    private Vertex2D vertex2D;

    private static final double X = -1.2;
    private static final double Y = 2.4;

    @Before
    public void setUp() {
        vertex2D = new Vertex2D(X, Y);
    }

    @Test
    public void finalAttributes() {
        BasicRulesTester.attributesFinal(Vertex2D.class);
    }

    @Test
    public void distanceValidInput() {
        double distance = new Vertex2D(-1.2, 1.2).distance(new Vertex2D(1.3, 1.3));
        assertThat(distance).isBetween(2.5, 2.503);
        distance = new Vertex2D(-1.2, -1.2).distance(new Vertex2D(1.3, 1.3));
        assertThat(distance).isBetween(3.5, 3.6);
        distance = new Vertex2D(1.2, 1.2).distance(new Vertex2D(1.3, 1.3));
        assertThat(distance).isBetween(0.1, 0.15);
        distance = new Vertex2D(-1.2, -1.2).distance(new Vertex2D(-1.3, -1.3));
        assertThat(distance).isBetween(0.1, 0.15);
    }

    @Test
    public void distanceNullInput() {
        assertThatCode(
                () -> assertThat(vertex2D.distance(null)).isEqualTo(-1.0)
        )
                .as("Should return -1 as indicator of wrong input")
                .doesNotThrowAnyException();
    }

    @Test
    public void getters() {
        assertThat(vertex2D.getX()).isEqualTo(X);
        assertThat(vertex2D.getY()).isEqualTo(Y);
    }

    @Test
    public void checkToString() {
        assertThat(vertex2D.toString()).isEqualTo("[" + X + ", " + Y + "]");
    }

    @Test
    public void createMiddle() {
        Vertex2D v1 = new Vertex2D(-1.2, 2.4);
        Vertex2D v2 = new Vertex2D(-0.8, 2.6);
        Vertex2D res = new Vertex2D(-1, 2.5);

        assertThat(v1.createMiddle(v2)).isEqualToComparingFieldByField(res);
    }

}
