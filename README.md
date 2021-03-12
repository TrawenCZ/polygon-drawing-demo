## Third iteration

The exercise focused on overloading custom constructors and methods.

1.  In the class `Vertex2D`:
    * Make the `Vertex2D` class  _immutable_, i.e., remove the setters and set all attributes as `final`.
    *   Add the `distance` method, which takes another 2D point as an input parameter and returns its
        Euclidean distance as `double`. The distance of the points is calculated as:
    ![formula](images/03a.png)
    *   If the input argument is `null`, then the method returns` -1.0` as an error indicator (distance is always non-negative).
	
	    >> Methods for mathematical operators are in the `Math` class.
	    >> For instance, the square root is calculated using the static method `Math.sqrt()`.

2.  Create a class `Circle` in the package `cz.muni.fi.pb162.project.geometry`.
    *   The class will have a constructor with two parameters (in this order): _center_ of type `Vertex2D` and _radius_ of type `double`.
        Attributes will be immutable.
    *   The class will have a _parametric-free constructor_ that will create a unit circle with the center at the beginning of the coordinate system (i.e., center `[0.0, 0.0]` and radius `1.0`).
    *   **The parametric-free constructor will call the parametric constructor** and will pass the needed values to it.
    *   For radius and center, create the getters `getRadius()` and `getCenter()`.
    *   The `toString` method will return a string in the format:

            "Circle: center=[<x>, <y>], radius=<radius>"

        where `<x>` and `<y>` are the values of the respective center coordinates and `<radius>` is the radius value.

3.  Edit the `Triangle` class as follows:
    *   Remove the setter, set the attributes to `final`.
        The class cannot be immutable because the `divide` method changes the properties of a triangle (the state of a triangle object).
    *   Add a `boolean isEquilateral()` method that returns `true` if the triangle is equilateral. Because we work with real numbers computed by math expressions, it is not practical to compare side lengths with `d1 == d2`. Even e very small difference makes the lengths different. Therefore, it is more suitable to use a test that considers two real numbers to be identical if they differ only slightly:

            Math.abs(d1-d2) < 0.001

        where `0.001` is the tolerated absolute deviation and **will be defined as the private constant**.
    *   Create an overloaded `void divide(int depth)` method that divides a triangle into sub-triangles. The result will be [_Sierpiński triangle_](http://en.wikipedia.org/wiki/Sierpinski_triangle):
             ![Sierpiński triangle](images/03b.png)
             *Sierpiński triangles of depth 0 to 4.*
        *   The `depth` parameter specifies the depth of the division. Zero means no division (we are at the end of recursion), 1 means that there will be one division of the original triangle, etc.
        *   If the `depth` is less than or equal to zero, the division will not occur and the method will end.
		*   Otherwise, the method splits the triangle using the `divide ()` method and recursively tries to split the resulting sub-triangles until reaching the requested depth.
    *   Create a constructor with 4 parameters, the fourth parameter represents the division depth. The constructor calls the previous constructor and then splits the triangle.

4.  After starting the `Draw` class, [_Sierpiński triangles_ of depth 4 and a red circle will be drawn on the screen](https://gitlab.fi.muni.cz/pb162/pb162-course-info/wikis/draw-images).

