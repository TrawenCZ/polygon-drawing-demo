package cz.muni.fi.pb162.project.geometry;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.within;

/**
 * Tests 2D square class.
 *
 * @author Marek Sabo
 */
public class SquareTest {

    private static final int CENTER_X = 12;
    private static final int CENTER_Y = 6;
    private static final Vertex2D CENTER = new Vertex2D(CENTER_X, CENTER_Y);

    private static final double EDGE_LENGTH = 10;
    private static final double HALF_EDGE = EDGE_LENGTH / 2.0;

    private static final double DELTA = 0.001;

    private final Square square1 = new Square(CENTER, EDGE_LENGTH);
    private final Square square2 = new Square(new Circle(CENTER, HALF_EDGE));

    private final Vertex2D[] vertices = new Vertex2D[] {
            new Vertex2D(CENTER_X - HALF_EDGE, CENTER_Y), // left
            new Vertex2D(CENTER_X, CENTER_Y - HALF_EDGE), // bottom
            new Vertex2D(CENTER_X + HALF_EDGE, CENTER_Y), // right
            new Vertex2D(CENTER_X, CENTER_Y + HALF_EDGE), // top
    };

    @Test
    public void constructor1() {
        testGetterOutOfRange(square1);
        testGetterInRange(square1);
    }

    @Test
    public void constructor2() {
        testGetterOutOfRange(square2);
        testGetterInRange(square2);
    }

    private void testGetterOutOfRange(Square square) {
        assertThat(square.getVertex(-1)).isNull();
        assertThat(square.getVertex(4)).isNull();
        assertThat(square.getVertex(20)).isNull();
    }

    private void testGetterInRange(Square square) {
        for (int i = 0; i < 4; i++) {
            assertThat(square.getVertex(i)).isEqualToComparingFieldByField(vertices[i]);
        }
    }

    @Test
    public void getCenter() {
        assertThat(square1.getCenter()).isEqualToComparingFieldByField(CENTER);
        assertThat(square2.getCenter()).isEqualToComparingFieldByField(CENTER);
    }

    @Test
    public void getRadius() {
        assertThat(square1.getRadius()).isCloseTo(HALF_EDGE, within(DELTA));
        assertThat(square2.getRadius()).isCloseTo(HALF_EDGE, within(DELTA));
    }


}
