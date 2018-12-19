package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.exception.MissingVerticesException;
import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import cz.muni.fi.pb162.project.utils.SimpleMath;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests SimplePolygon class.
 *
 * @author Marek Sabo
 */
public class SimplePolygonTest {

    private SimplePolygon polygon;
    private static final Vertex2D[] VERTICES = {
            new Vertex2D(-3, -1),
            new Vertex2D(-2, -2),
            new Vertex2D(-4, -1)
    };

    @Before
    public void setUp() {
        polygon = new MockPolygon();
    }

    @Test
    public void classAttributes() {
        BasicRulesTester.attributesAmount(SimplePolygon.class, 0);
    }

    @Test
    public void getHeight() {
        assertThat(polygon.getHeight())
                .isEqualTo(SimpleMath.maxY(polygon) - SimpleMath.minY(polygon));
    }

    @Test
    public void getWidth() {
        assertThat(polygon.getWidth())
                .isEqualTo(SimpleMath.maxX(polygon) - SimpleMath.minX(polygon));
    }

    @Test
    public void toStringMessage() {
        assertThat(polygon.toString()).isEqualTo("Polygon: vertices = [-3.0, -1.0] [-2.0, -2.0] [-4.0, -1.0]");
    }

    @Test
    public void missingVertices() {
        assertThatThrownBy(() -> new SimplePolygon(new Vertex2D[] { VERTICES[0], VERTICES[1] }) {
            @Override
            public Vertex2D getVertex(int index) {
                return null;
            }

            @Override
            public int getNumVertices() {
                return 0;
            }
        }).isExactlyInstanceOf(MissingVerticesException.class);
    }

    private class MockPolygon extends SimplePolygon {

        private MockPolygon() {
            super(VERTICES);
        }

        @Override
        public Vertex2D getVertex(int index) {
            return VERTICES[index % VERTICES.length];
        }

        @Override
        public int getNumVertices() {
            return VERTICES.length;
        }

    }

}
