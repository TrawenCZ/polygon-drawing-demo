## First iteration

Iteration for getting acquainted with objects and encapsulation.

1.  Create a `Vertex2D` class representing a point in 2D space with *X* and *Y* coordinates.
    *   The class will be in the package `cz.muni.fi.pb162.project.geometry` (you must create the appropriate package).
    *   The class will have two attributes of type `double`, in which it will store the values of the coordinates *X* and *Y*.
        Their default value will be 0.
    *   Do not create constructors yet.
    *   Add the so-called _getters_ and _setters_ to the class, specifically methods:
        *   `double getX()` and `double getY()` for obtaining the attribute values,
        *   `void setX(double newX)` and `void setY(double newY)` for setting the attribute values.
    *   Method `String getInfo()` returns a formatted coordinate description according to the following example:
        For a point at the coordinates x=2.0, y=3.0 returns **10 characters** (space included):
        `[2.0, 3.0]`.
    *   Method `double sumCoordinates()` returns the sum of the coordinates *X* and *Y*.
    *   Method `void move(Vertex2D vertex)` takes another 2D point as the input parameter and shifts the vertex by the `vertex` coordinates.
        For example, calling `[2, 3].move([1, 1])` shifts vertex `[2, 3]` to the coordinates `[3, 4]`.
    *   Adjust the visibility of attributes and methods to meet encapsulation conditions.
    *   Complete the necessary javadoc. For getters and setters, javadoc does not have to be written, because their purpose and use are obvious.

2.  Edit the executable class `Demo`.
    *   Leave the class in the package `cz.muni.fi.pb162.project`.
    *   Fill in the author's name (`@author`).
    *   Remove the print of `Hello world!`.
    *   The class will create 2 vertices with coordinates `[2, 3]` and `[1, 1]`.
    *   Move the first vertex by the coordinates of the second vertex.
    *   Then print information about both vertices.
    *   The program prints to standard output:

        ~~~~
        [3.0, 4.0]
        [1.0, 1.0]
        ~~~~

3.  Document both classes using [_JavaDoc_](https://en.wikipedia.org/wiki/Javadoc).
    At the moment, we don't need the absolute correctness of the documentation, but it is necessary to pass *checkstyle*.
    Therefore, for class documentation, fill in the author (`@author`) with your full name. Have the method documentation generated and either fill it out, 
    or fill it next time.

4.  Test the classes using the test classes in the package **src/test/java**.
    After successful testing, submit to a homework vault or git and have it checked by the tutor.
   Submitted iteration must pass *tests* and *checkstyle*!

### Hints

- The default value of attributes with the `double` type is `0.0`.
- Getters and setters have clear naming rules using the names of the respective attributes. 
  Because the names of getters and setters are given by the assignment, it is necessary to derive the names of the respective attributes from them.
- The following usually applies to encapsulation: attributes are private, methods are public.
- The `Vertex2D` class must be **imported** into the `Demo` class because it is in another package.
- Be sure to run **all** tests.
