package cz.muni.fi.pb162.project.geometry;

/**
 * Class for creating beautiful Snowmans.
 *
 * @author Adam Slíva
 */
public class Snowman {
    private int stackCount = 4;
    private final Circular[] stack = new Circular[stackCount];
    private static final double DEFAULT = 0.8;

    /**
     * Constructor.
     *
     * @param object Gives base of Snowman.
     * @param reduction Gives reduction between balls.
     */
    public Snowman(Circular object, double reduction) {
        if (reduction > 1 || reduction <= 0) {
            reduction = DEFAULT;
        }
        stack[0] = object;
        for (int i = 1; i < stackCount; i++) {
            stack[i] = new Circle(new Vertex2D(stack[i-1].getCenter().getX(),
                    stack[i-1].getCenter().getY() + (1 + reduction)*stack[i-1].getRadius()),
                    stack[i-1].getRadius()*reduction);
        }
    }

    public Circular[] getBalls() {
        return stack;
    }

    public void setStackCount(int setter) {
        this.stackCount = setter;
    }

}
