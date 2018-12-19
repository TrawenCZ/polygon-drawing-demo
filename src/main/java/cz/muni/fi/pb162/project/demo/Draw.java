package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.exception.EmptyDrawableException;
import cz.muni.fi.pb162.project.geometry.ColoredPolygon;
import cz.muni.fi.pb162.project.geometry.Paper;
import cz.muni.fi.pb162.project.geometry.Polygon;
import cz.muni.fi.pb162.project.geometry.Vertex2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;

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

    private static final List<Vertex2D> WALLS = LList.of(
                            new Vertex2D(-150, -150),
                            new Vertex2D(150, -150),
                            new Vertex2D(150, 150),
                            new Vertex2D(-150, 150)
                    );

    private static final int A = 45;
    private static final int B = 100;

    private static final List<Vertex2D> WINDOW_LEFT = LList.of(
                    new Vertex2D(-A, A),
                    new Vertex2D(-B, A),
                    new Vertex2D(-B, B),
                    new Vertex2D(-A, B)
            );

    private static final List<Vertex2D> WINDOW_RIGHT = LList.of(
                    new Vertex2D(A, A),
                    new Vertex2D(B, A),
                    new Vertex2D(B, B),
                    new Vertex2D(A, B)
            );

    private static final List<Vertex2D> DOOR = LList.of(
                    new Vertex2D(-30, -150),
                    new Vertex2D(30, -150),
                    new Vertex2D(30, -20),
                    new Vertex2D(-30, -20)
            );

    private static final List<Vertex2D> ROOF = LList.of(
                    new Vertex2D(-150, 150),
                    new Vertex2D(150, 150),
                    new Vertex2D(0, 250)
            );

    private static final List<List<Vertex2D>> COLLECTION = LList.of(
                    WALLS,
                    WINDOW_LEFT,
                    WINDOW_RIGHT,
                    Arrays.asList(new Vertex2D(0, 0), null, null),
                    DOOR,
                    ROOF);

    private Graphics graphics;

    private static class LList {
        @SafeVarargs
        static<E> List<E> of(E... elements) {
            return Arrays.asList(elements);
        }
    }

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
        Paper paper = new Paper();

        try {
            paper.tryToDrawPolygons(COLLECTION);
        } catch (EmptyDrawableException e) {
            e.printStackTrace();
        }

        paintCross();
        paintPaper(paper);
    }

    private void paintCross() {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.drawLine(0, HALF_HEIGHT, PANEL_WIDTH, HALF_HEIGHT);
        graphics.drawLine(HALF_WIDTH, 0, HALF_WIDTH, PANEL_HEIGHT);
    }

    private void paintPaper(Paper paper) {
        for (ColoredPolygon cp : paper.getAllDrawnPolygons()) {
            paintColoredPolygon(cp);
        }
    }

    private void paintColoredPolygon(ColoredPolygon coloredPolygon) {
        paintPolygon(coloredPolygon.getPolygon(), convertColor(coloredPolygon.getColor()));
    }

    private void paintPolygon(Polygon polygon, Color color) {
        int arraySize = polygon.getNumVertices() + 1;
        int[] yVertex = new int[arraySize];
        int[] xVertex = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            Vertex2D vertex = polygon.getVertex(i);
            xVertex[i] = PANEL_WIDTH - ((int) Math.rint(HALF_WIDTH - vertex.getX()));
            yVertex[i] = (int) Math.rint(HALF_HEIGHT - vertex.getY());
        }

        graphics.setColor(color);
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
