package cz.muni.fi.pb162.project.geometry;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests polygon implemented by array.
 *
 * @author Marek Sabo
 */
public class ArrayPolygonTest {

    private static final Vertex2D VERTEX_1 = new Vertex2D(-100, -100);
    private static final Vertex2D VERTEX_2 = new Vertex2D(-40, 10);
    private static final Vertex2D VERTEX_3 = new Vertex2D(50, 20);
    private static final Vertex2D VERTEX_4 = new Vertex2D(10, -20);
    private static final Vertex2D VERTEX_5 = new Vertex2D(60, -40);

    private static final Vertex2D[] ARRAY_1 = {
            VERTEX_1,
            VERTEX_2,
            VERTEX_3,
            VERTEX_4,
            VERTEX_5
    };

    private static final Vertex2D[] ARRAY_2 = {
            VERTEX_3,
            VERTEX_4,
            VERTEX_5,
            VERTEX_1,
            VERTEX_2
    };

    private static final Vertex2D[] ARRAY_3 = {
            VERTEX_2,
            VERTEX_1,
            VERTEX_3,
            VERTEX_4,
            VERTEX_5
    };


    private Vertex2D[] vertices = {
            new Vertex2D(-3, -1),
            new Vertex2D(-2, -2),
            new Vertex2D(-1, -1)
    };

    private ArrayPolygon arrayPolygon;

    @Before
    public void setUp() {
        arrayPolygon = new ArrayPolygon(vertices);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullInConstructor() {
        new ArrayPolygon(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullInConstructorArray() {
        new ArrayPolygon(new Vertex2D[]{
                new Vertex2D(-3, -1),
                new Vertex2D(-2, -2),
                null
        });
    }

    @Test
    public void constructorCopiesArray() {
        vertices[0] = new Vertex2D(42, 42);
        assertThat(arrayPolygon.getVertex(0)).isEqualTo(new Vertex2D(-3, -1));
    }

    @Test
    public void gettersPositive() {
        for (int i = 0; i < 7; i++) {
            assertThat(arrayPolygon.getVertex(i)).isEqualTo(vertices[i % vertices.length]);
        }
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void gettersOutOfRangeNegative() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("index");
        arrayPolygon.getVertex(-1);
    }

    @Test
    public void equalsSame() {
        assertThat(new ArrayPolygon(ARRAY_1))
                .as("Same polygons should be equal")
                .isEqualTo(new ArrayPolygon(ARRAY_1));

        assertThat(new ArrayPolygon(ARRAY_1))
                .isEqualToComparingFieldByFieldRecursively(new ArrayPolygon(ARRAY_1));
    }

    @Test
    public void hashesSame() {
        assertThat(new ArrayPolygon(ARRAY_1).hashCode()).isEqualTo(
                new ArrayPolygon(ARRAY_1).hashCode());
    }

    @Test
    public void equalsDifferent() {

        assertThat(arrayPolygon).isNotEqualTo(null);
        assertThat(arrayPolygon).isNotEqualTo(new Vertex2D(0, 0));

        assertThat(new ArrayPolygon(ARRAY_1))
                .as("Shifted polygons should not be equal")
                .isNotEqualTo(new ArrayPolygon(ARRAY_2));

        assertThat(new ArrayPolygon(ARRAY_1))
                .as("Different polygons should not be equal")
                .isNotEqualTo(new ArrayPolygon(new Vertex2D[]{
                        new Vertex2D(0, 0),
                        new Vertex2D(10, 20.2),
                        new Vertex2D(30, 50.8)
                }));

        assertThat(new ArrayPolygon(ARRAY_1))
                .as("Different polygons should not be equal")
                .isNotEqualTo(new ArrayPolygon(ARRAY_3));
    }
}
