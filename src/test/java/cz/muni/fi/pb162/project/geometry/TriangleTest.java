package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
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
    public void finalAttributes() {
        BasicRulesTester.attributesFinal(Triangle.class);
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
    public void equilateralTriangle() {
        Triangle t = new Triangle(
                new Vertex2D(-1.2, 0),
                new Vertex2D(1.2, 0),
                new Vertex2D(0, 2.07846097)
        );
        assertThat(t.isEquilateral()).isTrue();
    }

    @Test
    public void nonEquilateralTriangle1() {
        Triangle t = new Triangle(
                new Vertex2D(-10, 0),
                new Vertex2D(0, 0),
                new Vertex2D(0, 10)
        );
        assertThat(t.isEquilateral()).isFalse();
    }

    @Test
    public void nonEquilateralTriangle2() {
        Triangle t = new Triangle(
                new Vertex2D(-10, 0),
                new Vertex2D(3, 2),
                new Vertex2D(1, 10)
        );
        assertThat(t.isEquilateral()).isFalse();
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
        checkDivisionDepth1(triangle);
    }

    private void checkDivisionDepth1(Triangle triangle) {
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

    @Test
    public void nestedDivisionDepth1() {
        triangle.divide(1);
        checkDivisionDepth1(triangle);
    }

    @Test
    public void nestedDivisionConstructor() {
        Triangle t = new Triangle(triangle.getVertex(0), triangle.getVertex(1), triangle.getVertex(2), 2);
        assertNestedDivision2(t);
    }

    @Test
    public void nestedDivisionDepth2() {
        Triangle second = copyTriangle(triangle);
        second.divide(2);
        assertNestedDivision2(second);
    }

    // TODO cannot check depth 1, problem with equals: checkDivisionDepth1(actual);
    private void assertNestedDivision2(Triangle actual) {
        triangle.divide(1);

        // divide second depth manually
        triangle.getSubTriangle(0).divide();
        triangle.getSubTriangle(1).divide();
        triangle.getSubTriangle(2).divide();

        assertSubTriangles(actual.getSubTriangle(0), triangle.getSubTriangle(0));
        assertSubTriangles(actual.getSubTriangle(1), triangle.getSubTriangle(1));
        assertSubTriangles(actual.getSubTriangle(2), triangle.getSubTriangle(2));
    }

    private void assertSubTriangles(Triangle actual, Triangle expected) {
        assertSubTriangles(actual, new Triangle[]{
                expected.getSubTriangle(0),
                expected.getSubTriangle(1),
                expected.getSubTriangle(2)
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

    private Triangle copyTriangle(Triangle triangle) {
        return new Triangle(triangle.getVertex(0), triangle.getVertex(1), triangle.getVertex(2));
    }

}
