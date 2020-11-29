## Tenth iteration

Exercise focused on working with input and output.

Modify the `LabeledPolygon.Builder` class to implement the `PolygonReadable` interface.
Modify the `LabeledPolygon` class to implement the `PolygonWritable` interface.

1.  The `read(InputStream)` method takes an open input containing named vertices, reads the vertices, and adds them to the existing vertices of the polygon.
    For any input/output error or input data format error, the method must atomically fail and throw an `IOException`. (atomically = loading all or nothing)
    The input data format is as follows:
    *   The input is text.
    *   Each vertex is on one line.
    *   Each line is in the format _"x y vertex name"_, i.e. first the coordinates of the vertex separated by a space
        and then the name of the vertex (the name can contain spaces).
        See, for example, the file _polygon-ok.txt_.

2.  The `write(OutputStream)` method writes vertices to a given output stream.
    The output format is the same as for the previous method.

3.  The `write(File)` and `read(File)` methods will work the same as before,
    however, they will work with the file instead of the I/O stream.
    Avoid code repetition!

4.  Create a `writeJson(OutputStream os)` method that will write a map in JSON format to the output stream.
    *   Use external library [gson](https://github.com/google/gson).
        For maven, you need to add a dependency to the `pom.xml` file in the `<dependencies>` section.
    *   Read the _Gson_ class documentation.
    *   According to [documentation use the so-called _pretty print_](https://github.com/google/gson/blob/master/UserGuide.md#compact-vs-pretty-printing-for-json-output-format).
    *   An object of type _Gson_ can be reused, so you only need to create it once.

6.  Edit the `Demo` class as follows:
    *   The class creates `LabeledPolygon` from the file `polygon-ok.txt`.
    *   The polygon will also contain a vertex named `vertex x` with coordinates `[123, 456]`.
    *   Write the output to the output stream `System.out` in JSON format.
    *   To check that the output stream is still open then print `Hello World!`.

5.  Running the `Draw` class loads _polygon-ok.txt_ and [draws on the screen](https://gitlab.fi.muni.cz/pb162/pb162-course-info/wikis/draw-images).

### Hints

- Only close streams/files that you have opened.
- Use _try with resources_.
- Study the methods `Writer#flush()`, `Reader#ready()`.
- Creating a file: `new File("soubor.txt")`.
- `Demo.main` can throw `IOException`.
- Instead of `\n`, use the universal line break separator, `System.lineSeparator()`. 
- The tests create a `polygon-out.txt` file.
