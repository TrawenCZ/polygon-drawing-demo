## Seventh iteration

Exercise focused on working with collections.

1.  Create a `CollectionPolygon` class that extends the `SimplePolygon` class, which will be similar to the `ArrayPolygon` class.
    The only difference is that the vertices of the n-gon will not be stored in the array, but in a suitable collection.

	*   Create a constructor that will take an **array** of vertices as the input parameter
(similar to the `ArrayPolygon` class). However, it will store the vertices in a suitable collection.

        > When choosing between a list and a set, keep in mind that the topology of the n-gon is determined by the order of the vertices and that it is allowed to have more vertices with the same coordinates (for a simple n-gon, the edges must not cross, but they can touch).

		> Variables should be of an interface type, i.e., `List` instead of `ArrayList`, `Set` instead of `HashSet`.

    *   As before, we want to prevent the creation of a polygon with no vertex. Therefore, the constructor checks if the input field is not empty (empty, null, or filled with null objects). If empty, the constructor throws an `IllegalArgumentException` exception with a description.
    
    *   Define equality methods. Two polygons are the same if all vertex indices are the same, i.e., they have the same coordinates of vertices with the same order.
    
		> Collections from the Java API have defined equality. And they have it defined sensibly. Take advantage of it.
		
		> For the sake of simplicity, we omit the situation when the polygons are geometrically the same, but they differ in the order of the vertices. In this case, they are considered different.
		
    *   Add the `CollectionPolygon withoutLeftmostVertices()` method to the class,  which returns a new polygon without the leftmost vertices (there can be more, see, e.g., rectangle).
        The original polygon remains unchanged. If the new polygon no longer contains any vertices after removing the leftmost vertices, the method returns `null`.
	    > This method is used to practice working with collections. Look for no other meaning for it.
		
2.  Note that the constructors of the `ArrayPolygon` and `CollectionPolygon` classes are very similar. 
    Both check the correctness of the input field. They differ only in the storage of vertices.

	*   In the common superclass `SimplePolygon`, create a constructor and move the common code to it.
	    Therefore, the `SimplePolygon` constructor checks the correctness of the input field. In case of an error, it throws an exception and thus the whole process of creating a polygon ends. If everything is OK, control is passed to the subclass constructor. Subclass constructors, on the other hand, will call the `SimplePolygon` constructor and then store the vertices in an array or collection, respectively.  If the `SimplePolygon` constructor fails with an exception, the subclass constructor also fails automatically. So, you don't have to deal with it in the subclasses. We will practice working with exceptions in more detail next time.

        > The abstract class `SimplePolygon` can have a constructor, but it cannot be instantiated directly.
        
		> There is a static `Arrays.asList` method for converting an array to a list. 
		
		> Both the `List.of` and `Arrays.asList` methods return an unmodifiable collection. A new collection must be created for modification.
		
    *   In the `CollectionPolygon` class, create a second constructor that will take a *list* of vertices as a parameter.  Again, it must be checked that we have at least one vertex, so this constructor will also call the superclass constructor created in the previous point.
    
		> To convert a collection to an array, there is a `toArray` method that takes a new array as an argument.

3. Create a `ColoredPolygon` class that takes any existing polygon and adds a new property: color.

    *   The constructor takes a polygon of type `Polygon` and a color of type `Color`.
    *   The class contains getters for the given attributes `getPolygon` and `getColor`.
    *   Two colored polygons are the same if they contain (logically) the same polygon and color.

4.  Create a `Paper` class that implements the `Drawable` interface. This class simulates paper on which colored polygons can be drawn. It simulates it by not drawing anything directly, but saves the polygons to be rendered together with the color in the collection as `ColoredPolygon` objects.
	
	> Note: The `ColoredPolygon` class is an intermediate step to the so-called design pattern *Decorator*. It adds new properties (here a color) to an existing object by inserting an intermediate object (`ColoredPolygon`) between the client code (`Paper`) and the original object (any `Polygon`). But for it to be a real decorator, the `ColoredPolygon` class would also have to implement the `Polygon` interface.
	
    When we draw the same polygon (with the same color) on paper twice, it is saved only once. Polygons are drawn on the paper in color and each polygon has a single color for simplicity. The default color is black.
	
    *   The first constructor will be without parameters.
    
    *   The next constructor will take a parameter of type `Drawable` and will copy the collection of drawn polygons.
    
    *   `changeColor(color)` changes the color in which the following polygons will be drawn (with which color they will be saved).  The default color for a new instance of paper is black. 
    
    *   `drawPolygon` "draws" (ie saves) a polygon on paper with the color set in the previous method.
	    If such a polygon (of the same color and shape) already exists on the paper (in the collection), it is ignored. If the white color is set, the polygon will not be drawn (saved) because it would not be visible on white paper anyway.

    *   `erasePolygon(polygon)` removes the polygon from the paper (removes it from the collection).

    *   `eraseAll()` removes all polygons from the paper.

    *   `getAllDrawnPolygons()` returns all colored polygons.

		> Preserve encapsulation. Don't allow the external code to modify list returned by the getter! The getter has to return the collection as **unmodifiable**. This is a general rule, not just for the `getAllDrawnPolygons()` method. To "switch" the collection into the modifiable version use static `Collections.unmodifiableXXX` methods. We do not have to return an unmodifiable collection ONLY IF we create and return a copy of the original collection in the method.
		
    *   `uniqueVerticesAmount()` returns the number of vertices on the paper without duplication.
    
    *   See the `Drawable` class javadoc for more information.

5. Starting the `Draw` class [draws a colored house](https://gitlab.fi.muni.cz/pb162/pb162-course-info/wikis/draw-images).

### Target UML class diagram:

![UML class diagram](images/07-class-diagram.jpg)
