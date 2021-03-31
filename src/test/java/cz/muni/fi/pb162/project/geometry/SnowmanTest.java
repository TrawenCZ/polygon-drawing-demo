package cz.muni.fi.pb162.project.geometry;

import org.junit.Test;

import static cz.muni.fi.pb162.project.geometry.CircleTest.assertCircle;

/**
 * Tests snowman class.
 *
 * @author Marek Sabo
 */
public class SnowmanTest {

    private Circle bottomBall = new Circle(new Vertex2D(0, 0), 100);
    private Circle secondBall = new Circle(new Vertex2D(0, 180), 80);
    private Circle thirdBall = new Circle(new Vertex2D(0, 324), 64);
    private Circle topBall = new Circle(new Vertex2D(0, 439.2), 51.2);

    @Test
    public void testDefaultDecreasingFactor() {
        assertSnowman(new Snowman(bottomBall, 0.8));
        assertSnowman(new Snowman(bottomBall, 0));
        assertSnowman(new Snowman(bottomBall, 1.001));
        assertSnowman(new Snowman(bottomBall, -1));
    }

    private void assertSnowman(Snowman snowman) {
        assertCircle(snowman.getBalls()[0], bottomBall);
        assertCircle(snowman.getBalls()[1], secondBall);
        assertCircle(snowman.getBalls()[2], thirdBall);
        assertCircle(snowman.getBalls()[3], topBall);
    }

}
