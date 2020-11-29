## Second iteration

The exercise focused on basic work with attributes, methods, and on the definition of custom constructors.

1.  Modify the `Vertex2D` class as follows:
    *   The class will have a defined constructor with two parameters `x` and `y`.
    *   Rename the `getInfo()` method to `toString()`.
        This is the standard method that exists in every class, therefore add the annotation `@Override` above the method header.
        You will learn the details later.
    *   Remove the `sumCoordinates` and `move` methods. They will no longer be needed.
    *   Create the `Vertex2D createMiddle(Vertex2D otherVertex)` method, which creates and returns a point in the middle,
        i.e. `[2, 3].createMiddle([1, 1])` creates a point `[1.5, 2]`.
        Point has coordinates _[(x<sub>1</sub>+x<sub>2</sub>)/2, (y<sub>1</sub>+y<sub>2</sub>)/2]_.
2.  Create a `Triangle` class in the package `cz.muni.fi.pb162.project.geometry`.
    *   The triangle consists of three vertices of type `Vertex2D` and will have one attribute of type **array of vertices**.
    *   The constructor will have **3 parameters of type `Vertex2D`**.
    *   Method `Vertex2D getVertex(int index)` returns the _index_-th vertex.
        If the _index_ is less than 0 or greater than 2, the method returns `null`.
        When the _index_ is 0, it returns the first vertex, if 1 the second, if 2 then the third.
    *   The same applies for the method `void setVertex(int index, Vertex2D vertex)`. 
	    If the _index_ is out of range, the method will do nothing.
    *   Method `String toString()` returns the string:

        ~~~~
        "Triangle: vertices=[x0, y0] [x1, y1] [x2, y2]"
        ~~~~
        Use the `toString()` method from the class `Vertex2D`.
3. We want to divide the triangle into three smaller triangles. Therefore, implement the following methods.

    ![divided triangle](images/02a.png)
    *Original triangle (left) and divided into sub-triangles (right).*
    *   The `Triangle` class will contain an attribute of type `Triangle[]`.
        When split using the `divide()` method, three smaller triangles are stored in the array
        (black in the picture).
    *   The `boolean isDivided()` method determines if a triangle has already been split
        (smaller triangles were created, ie they are not `null`).
    *   The `Triangle getSubTriangle(int index)` method returns the `index` sub-triangle, where the `index` is the number between
         0 and 2.
        If the `index` is outside this range, or if the triangle is not already divided, the method returns `null`.
    *   The `boolean divide()` method splits a triangle, ie creates three smaller triangles, stores them in attributes,
         and returns `true`.
        If the triangle has already been split, the method will do nothing and return `false`.
         The vertices of smaller triangles are always half the length of the sides of the original triangle (see method `createMiddle`).

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

6.  Document the classes using [_JavaDoc_](https://en.wikipedia.org/wiki/Javadoc).
    The name must be in the format `@author FirstName LastName` including space. Set up name generation automatically as described
    [here](https://gitlab.fi.muni.cz/pb162/pb162-course-info/wikis/working-with-ide).
    Setters, getters, overrides (`@Override`), and private methods don't need to be documented using javadoc.
    Checkstyle starts automatically during translation. If you want to run it separately, you can call the command:

        mvn clean install -Dcheckstyle.fail=true

### Hints

- Method `createMiddle` is called over an existing object, so-called `this`.
- An array of vertices is created with `Vertex2D[] vertices = new Vertex2D[3];` and accessing the element with: `vertices[0]`.
- Use getters instead of direct access.
- Create a private helper method `boolean isInRange(int index)` to determine if the index is 0, 1 or 2.
- The `getVertex(index)` method does not need the `else` keyword, because `return` will make an immediate return from the method.
- The `toString()` method is a standard method that is called automatically whenever an object needs to be converted to text.
  It is not necessary to call it explicitly, when printing the object using `System.out.println` it will be called automatically.
- Implement the `divide` method at the end (leave the content of the method empty), and rather try the `Demo` class first.
