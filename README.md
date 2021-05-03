## Eighth iteration

Exercise focused on working with exceptions and nested collections.

1.  Create exceptions in the package `cz.muni.fi.pb162.project.exception`:
    *   `TransparentColorException` is a _checked_ exception when drawing in the same color on the same background, e.g., white pencil on white paper.
    *   `EmptyDrawableException` is a _checked_ exception when there is nothing painted on the drawing object.
    *   `MissingVerticesException` represents the _**unchecked**_ exception when there are not enough vertices in the collection.

    All exceptions will have at least two constructors that:
    *   allows you to set **string** with an error message,
    *   allows you to set **string** and the **cause** of the exception: the exception that was the immediate cause of this exception.

2. Modify the `SimplePolygon` constructor to throw a `MissingVerticesException` when the array contains less than three vertices. 
   > Method headers that throw checked exceptions must contain `throws`.

3. Edit `Paper` as follows:
    *   The `eraseAll()` method called on clean (empty) paper throws an `EmptyDrawableException`.
    *   The `drawPolygon` method will throw a `TransparentColorException` when drawing in white.
        The exception will contain a text description with the name of the color.
    *   `Paper` will implement the `PolygonFactory` interface.
    *   The `Polygon tryToCreatePolygon(List<Vertex2D>)` method will try to create a `CollectionPolygon` from a list of vertices.
        *   If the input argument is `null`, the method throws a `NullPointerException`.
        *   The method copies the input collection (it will not modify the original collection).
        *   If an `IllegalArgumentException` error occurs while creating a polygon, the method absorbs the exception, removes all `null` vertices from the collection, and tries again. Method lets the exception `MissingVerticesException` pass through.
	        > Note: The requested behavior violates the principle of using exceptions. It would be more logical and easier to first check and remove null vertices. This would also avoid unnecessarily throwing an exception by the CollectionPolygon class constructor. The required behavior is only to practice working with exceptions.

    *   The `void tryToDrawPolygons(List<List<Vertex2D>>)` method takes a list of lists of vertices
         (i.e., a list of polygons saved so far as a collection of vertices).
        *   The method tries to create a polygon (`tryToCreatePolygon`) from each collection and then draw it (`drawPolygon`).
        *   If a `TransparentColorException` occurs while drawing a polygon, other polygons will be drawn in black.
        *   If a `MissingVerticesException` or `NullPointerException` occurs during creation, the exception method catches and tries to create and paint the next polygon. If **no polygon** could be painted, `EmptyDrawableException` is thrown with the cause of the **last** error.
    *   Add the `Collection<Polygon> getPolygonsWithColor(Color color)` method, which returns all polygons with `color` color. You can use lambda streams, specifically `filter`, `map`, `collect`.

3. Running the `Draw` class [draws a black and white house](https://gitlab.fi.muni.cz/pb162/pb162-course-info/wikis/draw-images).

