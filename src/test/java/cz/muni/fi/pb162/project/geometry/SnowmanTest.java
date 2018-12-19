package cz.muni.fi.pb162.project.geometry;

import org.junit.Test;

import static cz.muni.fi.pb162.project.geometry.CircleTest.assertCircle;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests snowman class.
 *
 * @author Marek Sabo
 */
public class SnowmanTest {

    private Circle bottomBall = new Circle(new Vertex2D(0, 0), 100);
    private Circle secondBall = new Circle(new Vertex2D(0, 180), 80);
    private Circle thirdBall = new Circle(new Vertex2D(0, 324), 64);

    @Test
    public void snowmanHas3Balls() {
        assertThat(new Snowman(bottomBall, 0.3).getBalls().length).isEqualTo(3);
    }

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
    }

}
