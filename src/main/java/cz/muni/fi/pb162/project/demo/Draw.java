package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.geometry.Snowman;
import cz.muni.fi.pb162.project.geometry.Square;
import cz.muni.fi.pb162.project.geometry.Vertex2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.AbstractMap;
import cz.muni.fi.pb162.project.geometry.Circular;

/**
 * Class drawing 2D objects.
 *
 * @author Radek Oslejsek, Marek Sabo
 */
@SuppressWarnings("SameParameterValue")
public final class Draw extends JFrame {

    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 600;
    private static final int HALF_WIDTH = PANEL_WIDTH / 2;
    private static final int HALF_HEIGHT = PANEL_HEIGHT / 2;

    private static final Color CIRCLE_COLOR = Color.RED;
    private static final Color SQUARE_COLOR = Color.GREEN;

    private static final Square SQUARE = new Square(new Vertex2D(0,-160), 160);
    private static final Snowman SNOWMAN = new Snowman(SQUARE, 0.8);

    private Graphics graphics;

    /**
     * Draws 2D objects.
     *
     * @param args command line arguments, will be ignored
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Draw::new);
    }

    private Draw() {
        setBounds(350, 250, PANEL_WIDTH, PANEL_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Draw");
        JPanel panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                paintScene(g);
            }
        };
        panel.setBackground(Color.WHITE);
        add(panel);
        setVisible(true);
    }

    private void paintScene(Graphics g) {
        graphics = g;

        paintCross();
        paintSquare(SQUARE);
        paintSnowman(SNOWMAN);
    }

    private void paintCross() {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.drawLine(0, HALF_HEIGHT, PANEL_WIDTH, HALF_HEIGHT);
        graphics.drawLine(HALF_WIDTH, 0, HALF_WIDTH, PANEL_HEIGHT);
    }

    private AbstractMap.SimpleEntry<Integer, Integer> createLinePoints(Square square, int index) {
        int a1 = PANEL_WIDTH - ((int) Math.rint(HALF_WIDTH - square.getVertex(index).getX()));
        int a2 = (int) Math.rint(HALF_HEIGHT - square.getVertex(index).getY());
        return new AbstractMap.SimpleEntry<>(a1, a2);
    }

    private void paintSquare(Square square) {

        if (square == null) return;

        graphics.setColor(SQUARE_COLOR);
        Polygon polygon = new Polygon();

        for (int i = 0; i < 4; i++) {
            AbstractMap.SimpleEntry<Integer, Integer> pair = createLinePoints(square, i);
            polygon.addPoint(pair.getKey(), pair.getValue());
        }

        graphics.drawPolygon(polygon);
    }

    private void paintSnowman(Snowman snowman) {
        for(Circular c : snowman.getBalls()) {
            paintCircumcircle(c);
        }
    }

    private void paintCircumcircle(Circular c) {
        int radius = (int) Math.rint(c.getRadius());
        int x = PANEL_WIDTH - ((int) Math.rint(HALF_WIDTH - c.getCenter().getX()) + radius);
        int y = (int) Math.rint(HALF_HEIGHT - c.getCenter().getY()) - radius;
        int diameter = (int) Math.rint(c.getRadius() * 2.0);

        graphics.setColor(CIRCLE_COLOR);
        graphics.drawOval(x, y, diameter, diameter);
    }
}