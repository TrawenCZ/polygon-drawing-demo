package cz.muni.fi.pb162.project.geometry;

/**
 * Enum for defining colors.
 *
 * @author Adam Slíva
 */
public enum Color {
    RED,
    ORANGE,
    GREEN,
    BLUE,
    BLACK,
    YELLOW,
    WHITE;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
