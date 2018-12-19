package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.geometry.LabeledPolygon;
import cz.muni.fi.pb162.project.geometry.Polygon;
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

        LabeledPolygon polygon = new LabeledPolygon.Builder()
                .addVertex("A", new Vertex2D(-100, -100))
                .addVertex("D", new Vertex2D(100, 100))
                .addVertex("F", new Vertex2D(-100, 100))
                .addVertex("C", new Vertex2D(100, -100))
                .addVertex("B", new Vertex2D(0, 0))
                .addVertex("E", new Vertex2D(0, 0))
                .build();

        paintCross();
        paintPolygon(polygon);
    }

    private void paintCross() {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.drawLine(0, HALF_HEIGHT, PANEL_WIDTH, HALF_HEIGHT);
        graphics.drawLine(HALF_WIDTH, 0, HALF_WIDTH, PANEL_HEIGHT);
    }

    private void paintPolygon(Polygon polygon) {

        int[] yVertex = new int[polygon.getNumVertices() + 1];
        int[] xVertex = new int[polygon.getNumVertices() + 1];

        for (int i = 0; i <= polygon.getNumVertices(); i++) {
            xVertex[i] = PANEL_WIDTH - ((int) Math.rint(HALF_WIDTH -
                    polygon.getVertex(i % polygon.getNumVertices()).getX()));
            yVertex[i] = (int) Math.rint(HALF_HEIGHT - polygon.getVertex(i % polygon.getNumVertices()).getY());
        }

        graphics.setColor(Color.BLUE);
        graphics.drawPolygon(xVertex, yVertex, polygon.getNumVertices() + 1);

        if (polygon instanceof LabeledPolygon) {
            LabeledPolygon labeledPolygon = (LabeledPolygon) polygon;

            graphics.setColor(Color.RED);
            int j = 0;
            int labelDistance = 15;
            for (String label : labeledPolygon.getLabels()) {
                int x = (xVertex[j] < HALF_WIDTH) ? xVertex[j] + labelDistance : xVertex[j] + labelDistance;
                int y = yVertex[j];
                graphics.drawString(label, x, y);
                j++;
                labelDistance *= -1;
            }
        }

    }

}
