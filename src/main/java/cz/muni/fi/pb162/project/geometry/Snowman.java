package cz.muni.fi.pb162.project.geometry;

/**
 * Class for creating beautiful Snowmans.
 *
 * @author Adam Slíva
 */
public class Snowman {
    public static final int STACK_COUNT = 4;
    private final Circular[] stack = new Circular[STACK_COUNT];
    private static final double DEFAULT_REDUCTION = 0.8;

    /**
     * Constructor.
     *
     * @param object Gives base of Snowman.
     * @param reduction Gives reduction between balls.
     */
    public Snowman(Circular object, double reduction) {
        if (reduction > 1 || reduction <= 0) {
            reduction = DEFAULT_REDUCTION;
        }
        stack[0] = object;
        for (int i = 1; i < STACK_COUNT; i++) {
            stack[i] = new Circle(new Vertex2D(stack[i-1].getCenter().getX(),
                    stack[i-1].getCenter().getY() + (1 + reduction)*stack[i-1].getRadius()),
                    stack[i-1].getRadius()*reduction);
        }
    }

    public Circular[] getBalls() {
        return stack;
    }

}
