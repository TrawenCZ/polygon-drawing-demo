## Second iteration

The exercise focused on basic work with attributes, methods, arrays, and on the definition of custom constructors. 

1.  Modify the `Vertex2D` class as follows:
    *   Enable to create a vertex by directly providing two coordinates, e.g., by calling `new Vertex2D(1.2, 3.8)`.
    *   Rename the `getInfo()` method to `toString()`.
        > `toString()` is the standard method that exists in every class (we'll learn later how it is arranged).
		> Therefore, add the annotation `@Override` above the method header.

    *   Remove the `sumCoordinates` and `move` methods. They will no longer be needed.
	*   Create the `createMiddle` method, which takes one `Vertex2D` as an input parameter, computes the middle point between this given vertex and "me" (a vertex on which the method has been called), and returns the computed vertex. For example, calling the method `createMiddle` on a vertex with coordinates `[2, 3]` and input parameter `[1, 1]` returns a new vertex with coordinates `[1.5, 2]`.
        > The coordinates of the middle point are computed as _[(x<sub>1</sub>+x<sub>2</sub>)/2, (y<sub>1</sub>+y<sub>2</sub>)/2]_.
		
   	    > Do not worry to deal with vertices (objects of the `Vertex2D` class) inside the `Vertex2D` class definition. It may look weird, but it's okay.
2.  Create a `Triangle` class in the package `cz.muni.fi.pb162.project.geometry`.
    *   The triangle consists of three vertices (objects of type `Vertex2D`) stored in a single attribute of type **"array of vertices"**.
    *   The triangle's constructor takes the three vertices as three input parameters.
	*   Method `Vertex2D getVertex(int index)` returns the _index_-th vertex. If the _index_ is less than 0 or greater than 2, the method returns `null`. When the _index_ is 0, it returns the first vertex, if 1 the second, if 2 then the third.
    *   The same applies for the method `void setVertex(int index, Vertex2D vertex)`, which stores (replaces) the triangle's vertex. If the _index_ is out of range, the method will do nothing.
    *   Method `String toString()` returns the string:

        ~~~~
        "Triangle: vertices=[x0, y0] [x1, y1] [x2, y2]"
        ~~~~
        Don not duplicate code, use what we already have. In this case, use the `toString()` method from the class `Vertex2D` to implement `toString` of the triangle.
3. In this step, we want to divide the triangle into three smaller sub-triangles. Therefore, implement the following methods.

    ![divided triangle](images/02a.png)
    *Original triangle (left) and divided into sub-triangles (right).*
    *   Sub-triangles are stored in the triangle in a single *"array of triangles"*.
    *   The triangle is split by calling the `boolean divide()` method. **Three** smaller triangles (black in the picture) are stored in the corresponding attribute and the method returns `true`. If the triangle has already been split, the method will do nothing and return `false`. The vertices of smaller triangles lie in the middle of the edges of the original triangle.
	    > Don not duplicate code, use what we already have. In this case, use existing method(s) to compute points in the middle of the triangle edges.
	    
    *   The `boolean isDivided()` method determines if a triangle has already been split
        (smaller triangles were created, i.e., they are not `null`).
    *   The `Triangle getSubTriangle(int index)` method returns the `index` sub-triangle, where the `index` is the number between 0 and 2. If the `index` is outside this range, or the triangle is not already divided, the method returns `null`.

4.  Edit the `Demo` class as follows:
    *   Move the class to the package `cz.muni.fi.pb162.project.demo`.
    *   Remove the variable creation and text printing.
    *   The class newly creates a triangle with coordinates _[-100, 0] [0, 100] [100, -100]_.
    *   On standard output prints the information about the triangle. Once started, the output should look like this:

        ~~~~
        Triangle: vertices=[-100.0, 0.0] [0.0, 100.0] [100.0, -100.0]
        ~~~~
5.  Verify the correct implementation with unit tests.
    Then you run the `Draw` class in the `demo` package, you will see [a triangle with three
    sub-triangles](https://gitlab.fi.muni.cz/pb162/pb162-course-info/wikis/draw-images).

6.  Document the classes using [_JavaDoc_](https://en.wikipedia.org/wiki/Javadoc). The name must be in the format `@author FirstName LastName` including space. You can set up name generation automatically as described [here](https://gitlab.fi.muni.cz/pb162/pb162-course-info/wikis/working-with-ide). Setters, getters, overrides (methods annotated with `@Override`), and private methods don't need to be documented using javadoc. checkstyle starts automatically during translation. If you want to run it separately, you can call the command:

        mvn clean install -Dcheckstyle.fail=true

