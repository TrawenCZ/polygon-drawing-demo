package cz.muni.fi.pb162.project.geometry;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Simple Vertex2D tests.
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
    public void gettersAndSetters() {
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
