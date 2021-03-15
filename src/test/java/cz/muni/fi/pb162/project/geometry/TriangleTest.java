package cz.muni.fi.pb162.project.geometry;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Class testing Triangle implementation.
 *
 * @author Marek Sabo
 */
public class TriangleTest {

    private Triangle triangle;

    private final Vertex2D vertex1 = new Vertex2D(-100, -100);
    private final Vertex2D vertex2 = new Vertex2D(0, 100);
    private final Vertex2D vertex3 = new Vertex2D(100, -100);

    @Before
    public void setUp() {
        triangle = new Triangle(vertex1, vertex2, vertex3);
    }

    @Test
    public void gettersInRange() {
        assertThat(triangle.getVertex(0)).isEqualToComparingFieldByField(vertex1);
        assertThat(triangle.getVertex(1)).isEqualToComparingFieldByField(vertex2);
        assertThat(triangle.getVertex(2)).isEqualToComparingFieldByField(vertex3);

        assertThat(triangle).isEqualToComparingFieldByField(new Triangle(vertex1, vertex2, vertex3));
    }

    @Test
    public void gettersOutOfRange() {
        assertThat(triangle.getVertex(3)).isNull();
        assertThat(triangle.getVertex(4)).isNull();
        assertThat(triangle.getVertex(-1)).isNull();
    }

    @Test
    public void settersOutOfRange() {
        triangle.setVertex(3, null);
        triangle.setVertex(-1, null);
        assertThat(triangle.getVertex(0)).isNotNull();
        assertThat(triangle.getVertex(1)).isNotNull();
        assertThat(triangle.getVertex(2)).isNotNull();
    }

    @Test
    public void toStringMessage() {
        assertThat(triangle.toString()).isEqualTo("Triangle: vertices=[-100.0, -100.0] [0.0, 100.0] [100.0, -100.0]");

        Triangle t = new Triangle(
                new Vertex2D(-1.2, 0.0),
                new Vertex2D(1.2, 0.0),
                new Vertex2D(0.0, 2.07846097)
        );
        assertThat(t.toString()).isEqualTo("Triangle: vertices=[-1.2, 0.0] [1.2, 0.0] [0.0, 2.07846097]");
    }


    @Test
    public void checkIfDivided() {
        assertThat(triangle.isDivided()).isFalse();
        assertThat(triangle.divide()).isTrue();

        assertThat(triangle.isDivided()).isTrue();
        assertThat(triangle.divide()).isFalse();
    }

    @Test
    public void subTriangleGettersNull() {
        assertThat(triangle.getSubTriangle(-1)).isNull();
        assertThat(triangle.getSubTriangle(0)).isNull();
        assertThat(triangle.getSubTriangle(1)).isNull();
        assertThat(triangle.getSubTriangle(2)).isNull();
        assertThat(triangle.getSubTriangle(3)).isNull();
    }

    @Test
    public void subTriangleGettersInRangeNotNull() {
        triangle.divide();
        assertThat(triangle.getSubTriangle(0)).isNotNull();
        assertThat(triangle.getSubTriangle(1)).isNotNull();
        assertThat(triangle.getSubTriangle(2)).isNotNull();
    }

    @Test
    public void subTriangleGettersOutOfRangeNull() {
        triangle.divide();
        assertThat(triangle.getSubTriangle(-1)).isNull();
        assertThat(triangle.getSubTriangle(3)).isNull();
    }

    @Test
    public void division() {
        triangle.divide();

        assertSubTriangles(triangle, new Triangle[]{
                new Triangle(
                        new Vertex2D(-100.0, -100.0),
                        new Vertex2D(0.0, -100.0),
                        new Vertex2D(-50.0, 0.0)
                ),
                new Triangle(
                        new Vertex2D(0.0, 100.0),
                        new Vertex2D(-50.0, 0.0),
                        new Vertex2D(50.0, 0.0)
                ),
                new Triangle(
                        new Vertex2D(100.0, -100.0),
                        new Vertex2D(50.0, 0.0),
                        new Vertex2D(0.0, -100.0)
                )
        });
    }

    private void assertSubTriangles(Triangle actual, Triangle[] subTriangles) {
        Triangle[] actualTriangles = new Triangle[]{
                actual.getSubTriangle(0),
                actual.getSubTriangle(1),
                actual.getSubTriangle(2)
        };
        assertThat(actualTriangles)
                .usingElementComparator((t1, t2) -> {
                    if (t1 == null && t2 == null) return 0;
                    if (t1 == null || t2 == null) return 1;
                    return getHashCode(t1) - getHashCode(t2);
                })
                .containsExactlyInAnyOrder(subTriangles);
    }

    // TODO dirty workaround to simulate Triangle params permutation
    private int getHashCode(Triangle t) {
        int hash = 7;
        hash += 31 * t.getVertex(0).getX() + t.getVertex(0).getY();
        hash += 31 * t.getVertex(1).getX() + t.getVertex(1).getY();
        hash += 31 * t.getVertex(2).getX() + t.getVertex(2).getY();
        if (!t.isDivided()) return hash;
        hash += t.getSubTriangle(0).hashCode();
        hash += t.getSubTriangle(1).hashCode();
        hash += t.getSubTriangle(2).hashCode();
        return hash;
    }

}
