package cz.muni.fi.pb162.project.geometry;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests colored polygon.
 *
 * @author Marek Sabo
 */
public class ColoredPolygonTest {


    private Vertex2D[] vertices = {
            new Vertex2D(-3, -1),
            new Vertex2D(-2, -2),
            new Vertex2D(-1, -1)
    };

    private Polygon polygon = new CollectionPolygon(vertices);

    private Vertex2D[] randomVertices = {
            new Vertex2D(0, 0),
            new Vertex2D(-2, -3),
            new Vertex2D(5, 1)
    };

    @Test
    public void coloredPolygon() {
        ColoredPolygon coloredPolygon = new ColoredPolygon(polygon, Color.BLUE);
        assertThat(coloredPolygon.getPolygon()).isSameAs(polygon);
        assertThat(coloredPolygon.getColor()).isEqualTo(Color.BLUE);
    }

    @Test
    public void equalsSame() {
        ColoredPolygon coloredPolygon = new ColoredPolygon(polygon, Color.BLUE);
        assertThat(coloredPolygon).isEqualTo(new ColoredPolygon(polygon, Color.BLUE));
    }

    @Test
    public void equalsDifferent() {
        ColoredPolygon coloredPolygon = new ColoredPolygon(polygon, Color.BLUE);
        assertThat(coloredPolygon).isNotEqualTo(new ColoredPolygon(new CollectionPolygon(randomVertices), Color.BLACK));
        assertThat(coloredPolygon).isNotEqualTo(null);
        assertThat(coloredPolygon).isNotEqualTo(new Circle());
    }

    @Test
    public void hashesSame() {
        ColoredPolygon coloredPolygon = new ColoredPolygon(polygon, Color.BLUE);
        assertThat(coloredPolygon.hashCode()).isEqualTo(new ColoredPolygon(polygon, Color.BLUE).hashCode());
    }
}
