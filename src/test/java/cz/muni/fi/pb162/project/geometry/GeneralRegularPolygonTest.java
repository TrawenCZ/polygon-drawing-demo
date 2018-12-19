package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.junit.Before;
import org.junit.Test;

import static cz.muni.fi.pb162.project.helper.BasicRulesTester.DELTA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

/**
 * Tests regular n-gon.
 *
 * @author Marek Sabo
 */
public class GeneralRegularPolygonTest {

    private static final Vertex2D CENTER = new Vertex2D(1.0, -1.0);
    private static final double RADIUS = 2.0;
    private GeneralRegularPolygon polygon;

    @Before
    public void setUp() {
        polygon = new GeneralRegularPolygon(CENTER, 6, RADIUS);
    }

    @Test
    public void implementsInterface() {
        assertThat(polygon).isInstanceOf(RegularPolygon.class);
        assertThat(polygon).isInstanceOf(Colored.class);
    }

    @Test
    public void getCenter() {
        assertThat(polygon.getCenter()).isEqualToComparingFieldByField(new Vertex2D(1.0, -1.0));
    }

    @Test
    public void getNumEdges() {
        assertThat(polygon.getNumEdges()).isEqualTo(6);
    }

    @Test
    public void getRadius() {
        assertThat(polygon.getRadius()).isEqualTo(RADIUS);
    }

    @Test
    public void getEdgeLength() {
        assertThat(polygon.getEdgeLength()).isCloseTo(RADIUS, within(DELTA));
    }

    @Test
    public void getWidthHeight() {
        assertThat(polygon.getWidth()).isCloseTo(4.0, within(DELTA));
        assertThat(polygon.getHeight()).isCloseTo(4.0, within(DELTA));
    }

    @Test
    public void getVertex() {
        assertThat(polygon.getVertex(0)).isEqualToComparingFieldByField(new Vertex2D(-1.0, -1.0));
        // others are checked in the Square class
    }

    @Test
    public void toStringMessage() {
        assertThat(polygon.toString()).isEqualTo("6-gon: center=[1.0, -1.0], radius=2.0, color=black");
    }

    @Test
    public void colorToStringLowerCase() {
        assertThat(polygon.getColor().toString()).isEqualTo("black");
        polygon.setColor(Color.YELLOW);
        assertThat(polygon.getColor().toString()).isEqualTo("yellow");
        polygon.setColor(Color.valueOf("RED")); // 100% coverage
        assertThat(polygon.getColor().toString()).isEqualTo("red");
    }

    @Test
    public void regularOctagon() {
        RegularOctagon octagon = new RegularOctagon(CENTER, RADIUS);

        assertThat(octagon.getNumEdges()).isEqualTo(8);
        assertThat(octagon).isInstanceOf(GeneralRegularPolygon.class);

        BasicRulesTester.attributesAmount(RegularOctagon.class, 0);
        BasicRulesTester.methodsAmount(RegularOctagon.class, 0);
    }
}
