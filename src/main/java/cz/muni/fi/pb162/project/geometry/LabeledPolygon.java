package cz.muni.fi.pb162.project.geometry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.Writer;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class for creating polygons with Labeled vertexes.
 *
 * @author Adam Slíva
 */
public final class LabeledPolygon extends SimplePolygon implements Labelable, Sortable, PolygonWritable {
    private final TreeMap<String, Vertex2D> sortedVertices;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Constructor.
     */
    private LabeledPolygon(TreeMap<String, Vertex2D> newMap) {
        super(newMap.values().toArray(new Vertex2D[0]));
        this.sortedVertices = newMap;
    }

    @Override
    public Vertex2D getVertex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index should be greater or equal to zero.");
        }
        index = index % getNumVertices();
        Vertex2D output = null;
        for (Map.Entry<String, Vertex2D> current : this.sortedVertices.entrySet()) {
            if (index == 0) {
                output = current.getValue();
                break;
            }
            index--;
        }
        return output;
    }

    @Override
    public int getNumVertices() {
        return sortedVertices.size();
    }

    @Override
    public Vertex2D getVertex(String label) {
        return this.sortedVertices.get(label);
    }

    @Override
    public Collection<String> getLabels() {
        return new ArrayList<>(this.sortedVertices.keySet());
    }

    @Override
    public Collection<String> getLabels(Vertex2D vertex) {
        Set<String> output = new HashSet<>();
        for (Map.Entry<String, Vertex2D> current : this.sortedVertices.entrySet()) {
            if (current.getValue().equals(vertex)) {
                output.add(current.getKey());
            }
        }
        return output;
    }

    @Override
    public Collection<Vertex2D> getSortedVertices() {
        return new TreeSet<>(this.sortedVertices.values());
    }

    @Override
    public Collection<Vertex2D> getSortedVertices(Comparator<Vertex2D> comparator) {
        TreeSet<Vertex2D> output = new TreeSet<>(comparator);
        output.addAll(this.sortedVertices.values());
        return output;
    }

    /**
     * Method for getting duplicit key's values.
     *
     * @return Values of duplicit keys.
     */
    public Collection<Vertex2D> duplicateVertices() {
        Set<Vertex2D> output = new HashSet<>();
        for (Map.Entry<String, Vertex2D> current : this.sortedVertices.entrySet()) {
            for (Map.Entry<String, Vertex2D> next : this.sortedVertices.entrySet()) {
                if (!current.getKey().equals(next.getKey()) && current.getValue().equals(next.getValue())) {
                    output.add(current.getValue());
                }
            }
        }
        return output;
    }

    @Override
    public void write(OutputStream os) throws IOException {
        BufferedWriter newWriter = new BufferedWriter(new OutputStreamWriter(os));
        for (Map.Entry<String, Vertex2D> current : this.sortedVertices.entrySet()) {
            newWriter.write(current.getValue().getX() + " " + current.getValue().getY() + " " + current.getKey());
        }
        newWriter.flush();
    }

    @Override
    public void write(File file) throws IOException {
        try (OutputStream os = new FileOutputStream(file)) {
            write(os);
        }
    }

    /**
     * Method for printing in GSON format.
     *
     * @param os Output to print at.
     * @throws IOException When fails.
     */
    public void writeJson(OutputStream os) throws IOException {
        Writer newWriter = new BufferedWriter(new OutputStreamWriter(os));
        newWriter.write(GSON.toJson(sortedVertices));
        newWriter.flush();
    }

    /**
     * Builder for class.
     *
     * @author Adam Slíva
     */
    public static class Builder implements Buildable, PolygonReadable {
        private final TreeMap<String, Vertex2D> sortedVertices;

        /**
         * Constructor for builder.
         */
        public Builder() {
            this.sortedVertices = new TreeMap<>();
        }

        @Override
        public LabeledPolygon build() {
            return new LabeledPolygon(sortedVertices);
        }

        /**
         * Method for adding new Labeled vertex to the class.
         *
         * @param label Label of vertex.
         * @param vertex Vertex itself.
         *
         * @return Builder itself.
         */
        public Builder addVertex(String label, Vertex2D vertex) {
            if (label == null) {
                throw new IllegalArgumentException("label of the vertex cannot be null!");
            } else if (vertex == null) {
                throw new IllegalArgumentException("vertex cannot be null!");
            }
            this.sortedVertices.remove(label);
            this.sortedVertices.put(label, vertex);
            return this;
        }

        @Override
        public Builder read(InputStream is) throws IOException {
            if (is == null) {
                throw new IOException("Input cannot be null.");
            }
            BufferedReader newReader = new BufferedReader(new InputStreamReader(is));
            Map<String, Vertex2D> newVertices = new TreeMap<>();
            while (newReader.ready()) {
                String line = newReader.readLine();
                String[] splittedLine = line.split(" ", 3);
                if (splittedLine.length != 3) {
                    throw new IOException("String not in required format. (not right amount of arguments in input)");
                }

                double xCor;
                double yCor;
                try {
                    xCor = Double.parseDouble(splittedLine[0]);
                    yCor = Double.parseDouble(splittedLine[1]);
                } catch (NumberFormatException e) {
                    throw new IOException("Coordinates not in number format.");
                }
                newVertices.put(splittedLine[2], new Vertex2D(xCor, yCor));
            }
            for (Map.Entry<String, Vertex2D> current : newVertices.entrySet()) {
                this.addVertex(current.getKey(), current.getValue());
            }
            return this;
        }

        @Override
        public Builder read(File file) throws IOException {
            try (InputStream is = new FileInputStream(file)) {
                return read(is);
            }
        }
    }
}
