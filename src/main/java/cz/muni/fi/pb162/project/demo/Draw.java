package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.geometry.ArrayPolygon;
import cz.muni.fi.pb162.project.geometry.Polygon;
import cz.muni.fi.pb162.project.geometry.Triangle;
import cz.muni.fi.pb162.project.geometry.Vertex2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Class drawing 2D objects.
 *
 * @author Radek Oslejsek, Marek Sabo
 */
public final class Draw extends JFrame {

    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 600;
    private static final int HALF_WIDTH = PANEL_WIDTH / 2;
    private static final int HALF_HEIGHT = PANEL_HEIGHT / 2;

    private static final Color POLYGON_COLOR = Color.MAGENTA;

    private static final Polygon TRIANGLE =
            new Triangle(
                    new Vertex2D(-200, -200),
                    new Vertex2D(0, 200),
                    new Vertex2D(200, -200)
            );

    private static final Polygon POLYGON = new ArrayPolygon(
            new Vertex2D[]{
                    new Vertex2D(-100, -100),
                    new Vertex2D(-40, 10),
                    new Vertex2D(50, 20),
                    new Vertex2D(10, -20),
                    new Vertex2D(60, -40)
            });

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
        paintPolygon(TRIANGLE);
        paintPolygon(POLYGON);

    }

    private void paintCross() {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.drawLine(0, HALF_HEIGHT, PANEL_WIDTH, HALF_HEIGHT);
        graphics.drawLine(HALF_WIDTH, 0, HALF_WIDTH, PANEL_HEIGHT);
    }

    private void paintPolygon(Polygon polygon) {
        int arraySize = polygon.getNumVertices() + 1;
        int[] yVertex = new int[arraySize];
        int[] xVertex = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            Vertex2D vertex = polygon.getVertex(i);
            xVertex[i] = PANEL_WIDTH - ((int) Math.rint(HALF_WIDTH - vertex.getX()));
            yVertex[i] = (int) Math.rint(HALF_HEIGHT - vertex.getY());
        }

        graphics.setColor(POLYGON_COLOR);
        graphics.drawPolygon(xVertex, yVertex, arraySize);
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
}