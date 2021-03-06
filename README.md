## First iteration

Iteration for getting acquainted with objects and encapsulation.

1.  Create a `Vertex2D` class representing a point in 2D space with *X* and *Y* coordinates.
    *   The class will be in the package `cz.muni.fi.pb162.project.geometry` (you must create the appropriate package).
    *   The class will have two attributes of type `double`, in which it will store the values of the coordinates *X* and *Y*.
        Their default value will be 0.
        > Never use `float` type. this type is obsolete.

        > Attributes should have accurate descriptive names because they can appear anywhere in the class. Therefore, single-letter names are not recommended. However, the letters *X* and *Y* for coordinates are very common, so we can accept these single-letter attributes in this special case.

    *   **Do not** create constructors yet. We will focus on constructors on the next iteration.
    *   Add the so-called _getters_ and _setters_ to the class.
        > Choose appropriate names that reflect the names of the attributes you have selected!

    *   Method `String getInfo()` returns a formatted coordinate description according to the following example:
        For a point at the coordinates x=2.0, y=3.0 returns **10 characters** (space included):
        `[2.0, 3.0]`.
    *   Method `double sumCoordinates()` returns the sum of the coordinates *X* and *Y*.
    *   Method `void move(Vertex2D vertex)` takes another 2D point as the input parameter and shifts the vertex by the `vertex` coordinates. For example, calling the `move` mwthod on vertex with coordinates `[2, 3]` and input parameter `[1, 1]` shifts the vertex `[2, 3]` into `[3, 4]`.
    *   Adjust the visibility of attributes and methods to meet encapsulation conditions.

2.  Edit the executable class `Demo`.
    *   Leave the class in the package `cz.muni.fi.pb162.project`.
    *   Fill in the author's name (`@author`).
    *   Remove the print of `Hello world!`.
    *   The class creates 2 vertices with coordinates `[2, 3]` and `[1, 1]`.
        > Hint: The `Vertex2D` class must be **imported** into the `Demo` class because it is located in another package.

    *   Then moves the first vertex by the coordinates of the second vertex.
    *   Then prints information about both vertices.
    *   The program prints to standard output:

        ~~~~
        [3.0, 4.0]
        [1.0, 1.0]
        ~~~~


3.  Document both classes using [_JavaDoc_](https://en.wikipedia.org/wiki/Javadoc).
    > At least the author, the purpose of public methods, their input parameters, and return value have documented.

    > The presence of mandatory javadoc items is checked by the *checkstyle* plugin. However, the content is up to you. Be consise but thorough.
        
    > For getters and setters, javadoc does not have to be written, because their purpose and use are obvious.


4.  Test your code using unit tests in the package **src/test/java**.  After successful testing, submit to a homework vault or git and have it checked by the tutor. Submitted iteration must pass *tests* and *checkstyle*!
    > Unit tests and style checking are always evaluated whenever you rebuild the project in IDE.

    > If unsure, run `mvn clean install` on the command line.
    
    > Tests are also invoked when you submit (push) your code into git. If they fail, then you get an email. Failures during the development and continuous submission are OK. Failures after the final submission are not OK ;-)
    
    > Code of unit tests is weird and messy. To implement test cases, it is oftten necessary to violate encapsulation and other principles of production code. Therefore, don't take the inspiration from this code (it is better to not look inside at all ;-)
