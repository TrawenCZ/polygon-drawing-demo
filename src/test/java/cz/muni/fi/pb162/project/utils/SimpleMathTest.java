package cz.muni.fi.pb162.project.utils;

import cz.muni.fi.pb162.project.geometry.Triangle;
import cz.muni.fi.pb162.project.geometry.Vertex2D;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Tests math utils class.
 *
 * @author Marek Sabo
 */
public class SimpleMathTest {

    private Triangle triangle1 = new Triangle(
            new Vertex2D(50, 50),
            new Vertex2D(70, 70),
            new Vertex2D(90, 90)
    );

    private Triangle triangle2 = new Triangle(
            new Vertex2D(-50, -50),
            new Vertex2D(-70, -70),
            new Vertex2D(-90, -90)
    );

    @Test
    public void checksLowestX() {
        assertThat(SimpleMath.minX(triangle1)).isEqualTo(minX(polygonToArray(triangle1)));
        assertThat(SimpleMath.minX(triangle2)).isEqualTo(minX(polygonToArray(triangle2)));
    }

    @Test
    public void checksHighestX() {
        assertThat(SimpleMath.maxX(triangle1)).isEqualTo(maxX(polygonToArray(triangle1)));
        assertThat(SimpleMath.maxX(triangle2)).isEqualTo(maxX(polygonToArray(triangle2)));
    }

    @Test
    public void checksLowestY() {
        assertThat(SimpleMath.minY(triangle1)).isEqualTo(minY(polygonToArray(triangle1)));
        assertThat(SimpleMath.minY(triangle2)).isEqualTo(minY(polygonToArray(triangle2)));
    }

    @Test
    public void checksHighestY() {
        assertThat(SimpleMath.maxY(triangle1)).isEqualTo(maxY(polygonToArray(triangle1)));
        assertThat(SimpleMath.maxY(triangle2)).isEqualTo(maxY(polygonToArray(triangle2)));
    }

    public static double triangleWidth(Triangle t) {
        Vertex2D[] array = polygonToArray(t);
        return maxX(array) - minX(array);
    }

    public static double triangleHeight(Triangle t) {
        Vertex2D[] array = polygonToArray(t);
        return maxY(array) - minY(array);
    }

    private static Vertex2D[] polygonToArray(Triangle t) {
        return new Vertex2D[] { t.getVertex(0), t.getVertex(1), t.getVertex(2) };
    }

    private static double minX(Vertex2D[] array) {
        return Arrays.stream(array).min(Comparator.comparingDouble(Vertex2D::getX)).orElseThrow(null).getX();
    }

    private static double maxX(Vertex2D[] array) {
        return Arrays.stream(array).max(Comparator.comparingDouble(Vertex2D::getX)).orElseThrow(null).getX();
    }

    private static double minY(Vertex2D[] array) {
        return Arrays.stream(array).min(Comparator.comparingDouble(Vertex2D::getY)).orElseThrow(null).getY();
    }

    private static double maxY(Vertex2D[] array) {
        return Arrays.stream(array).max(Comparator.comparingDouble(Vertex2D::getY)).orElseThrow(null).getY();
    }

    @Test
    @SuppressWarnings("AccessStaticViaInstance")
    public void testClass() {
        new SimpleMath();
    }
}
