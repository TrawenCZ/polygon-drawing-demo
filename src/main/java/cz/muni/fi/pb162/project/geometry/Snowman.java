package cz.muni.fi.pb162.project.geometry;

/**
 * Class for creating beautiful Snowmans.
 *
 * @author Adam Slíva
 */
public class Snowman {
    public static final int STACK_COUNT = 3;
    private final RegularPolygon[] stack = new RegularPolygon[STACK_COUNT];
    private static final double DEFAULT_REDUCTION = 0.8;

    /**
     * Constructor.
     *
     * @param object Gives base of Snowman.
     * @param reduction Gives reduction between balls.
     */
    public Snowman(RegularPolygon object, double reduction) {
        if (reduction > 1 || reduction <= 0) {
            reduction = DEFAULT_REDUCTION;
        }
        stack[0] = object;
        for (int i = 1; i < STACK_COUNT; i++) {
            Vertex2D previousCenter = stack[i - 1].getCenter();
            double previousRadius = stack[i - 1].getRadius();
            stack[i] = new GeneralRegularPolygon(new Vertex2D(previousCenter.getX(),
                    previousCenter.getY() + (1 + reduction) * previousRadius),
                    object.getNumEdges(), previousRadius * reduction);
        }
    }

    public RegularPolygon[] getBalls() {
        return stack;
    }

}
