package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.comparator.VertexInverseComparator;
import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import java.util.regex.Pattern;

import java.util.SortedSet;
import java.util.TreeSet;

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
        //assertThat(vertex2D.toString()).isEqualTo("[" + X + ", " + Y + "]");
	assertThat(vertex2D.toString()).matches(Pattern.compile("^\\[" + X + "0*, " + Y + "0*\\]$"));
    }

    @Test
    public void createMiddle() {
        Vertex2D v1 = new Vertex2D(-1.2, 2.4);
        Vertex2D v2 = new Vertex2D(-0.8, 2.6);
        Vertex2D res = new Vertex2D(-1, 2.5);

        assertThat(v1.createMiddle(v2)).isEqualToComparingFieldByField(res);
    }

    @Test
    public void equalsSame() {
        assertThat(new Vertex2D(0,0))
                .isEqualTo(new Vertex2D(0,0));
        assertThat(new Vertex2D(10.8,-11.2))
                .isEqualTo(new Vertex2D(10.8,-11.2));
    }

    @Test
    public void hashesSame() {
        assertThat(new Vertex2D(0,0).hashCode())
                .isEqualTo(new Vertex2D(0,0).hashCode());
        assertThat(new Vertex2D(10.8,-11.2).hashCode())
                .isEqualTo(new Vertex2D(10.8,-11.2).hashCode());
    }

    @Test
    public void equalsDifferent() {
        assertThat(new Vertex2D(0,0)).isNotEqualTo(new Vertex2D(0,1));
        assertThat(new Vertex2D(1,0)).isNotEqualTo(new Vertex2D(0,0));
        assertThat(new Vertex2D(1,0)).isNotEqualTo(new Circle(vertex2D,0));
        assertThat(new Vertex2D(0,0)).isNotEqualTo(null);
    }

    @Test
    public void naturalOrderAscending() {
        // given
        Vertex2D v1a1 = new Vertex2D(1, 1);
        Vertex2D v1a2 = new Vertex2D(1, 2);
        Vertex2D v2a2 = new Vertex2D(2, 2);
        // then
        Assertions.assertThat(v1a1)
                .isLessThan(v1a2)
                .isLessThan(v2a2);
        Assertions.assertThat(v1a2).isLessThan(v2a2);
    }

    @Test
    public void inverseComparator() {
        // given
        SortedSet<Vertex2D> sortedSet = new TreeSet<Vertex2D>(new VertexInverseComparator());
        sortedSet.add(new Vertex2D(1, 1));
        sortedSet.add(new Vertex2D(0, 0));
        sortedSet.add(new Vertex2D(-1, 1));
        sortedSet.add(new Vertex2D(0, 1));
        sortedSet.add(new Vertex2D(1, 0));
        sortedSet.add(new Vertex2D(0.99, 23));
        // then
        Assertions.assertThat(sortedSet)
                .containsExactly(
                        new Vertex2D(1, 1),
                        new Vertex2D(1, 0),
                        new Vertex2D(0.99, 23),
                        new Vertex2D(0, 1),
                        new Vertex2D(0, 0),
                        new Vertex2D(-1, 1)
                );
    }

}
