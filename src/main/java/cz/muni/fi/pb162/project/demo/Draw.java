package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.geometry.Colored;
import cz.muni.fi.pb162.project.geometry.GeneralRegularPolygon;
import cz.muni.fi.pb162.project.geometry.RegularOctagon;
import cz.muni.fi.pb162.project.geometry.RegularPolygon;
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
    private static final Color POLYGON_COLOR = Color.BLACK;

    private static final Square SQUARE_BALL = new Square(new Vertex2D(-200, -120), 240);
    private static final RegularPolygon POLYGON_BALL = new RegularOctagon(new Vertex2D(200, -120), 120);
    private static final Snowman SNOWMAN_1 = new Snowman(SQUARE_BALL, 0.6);
    private static final Snowman SNOWMAN_2 = new Snowman(POLYGON_BALL, 0.7);

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
        paintSnowman(SNOWMAN_1);
        paintSnowman(SNOWMAN_2);
    }

    private void paintCross() {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.drawLine(0, HALF_HEIGHT, PANEL_WIDTH, HALF_HEIGHT);
        graphics.drawLine(HALF_WIDTH, 0, HALF_WIDTH, PANEL_HEIGHT);
    }

    private AbstractMap.SimpleEntry<Integer, Integer> createLinePoints(RegularPolygon polygon, int index) {
        int a1 = PANEL_WIDTH - ((int) Math.rint(HALF_WIDTH - polygon.getVertex(index).getX()));
        int a2 = (int) Math.rint(HALF_HEIGHT - polygon.getVertex(index).getY());
        return new AbstractMap.SimpleEntry<>(a1, a2);
    }

    private void paintSnowman(Snowman snowman) {
        for (RegularPolygon regularPolygon : snowman.getBalls()) {
            paintRegularPolygon(regularPolygon);
            paintCircumcircle(regularPolygon);
        }
    }

    private void paintRegularPolygon(RegularPolygon regularPolygon) {

        setGraphicsPolygonColor(regularPolygon);
        Polygon polygon = new Polygon();

        for (int i = 0; i < regularPolygon.getNumEdges(); i++) {
            AbstractMap.SimpleEntry<Integer, Integer> pair = createLinePoints(regularPolygon, i);
            polygon.addPoint(pair.getKey(), pair.getValue());
        }

        graphics.drawPolygon(polygon);
    }

    private void setGraphicsPolygonColor(RegularPolygon regularPolygon) {
        if (regularPolygon instanceof Colored) {
            graphics.setColor(convertColor(((Colored) regularPolygon).getColor()));
        } else {
            graphics.setColor(POLYGON_COLOR);
        }
    }

    /**
     * Converts enum Color to real color enum.
     *
     * @param color enum specific type
     * @return converted color type
     */
    private Color convertColor(cz.muni.fi.pb162.project.geometry.Color color) {
        switch (color) {
            case WHITE:
                return Color.WHITE;
            case YELLOW:
                return Color.YELLOW;
            case ORANGE:
                return Color.ORANGE;
            case RED:
                return Color.RED;
            case BLUE:
                return Color.BLUE;
            case GREEN:
                return Color.GREEN;
            case BLACK:
                return Color.BLACK;
            default:
                return POLYGON_COLOR;
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