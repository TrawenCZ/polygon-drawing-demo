## Desátá iterace

Cvičení zaměřené na práci se vstupem a výstupem.

Upravte třídu `LabeledPolygon.Builder` tak, aby implementovala rozhraní `PolygonReadable`.
Upravte třídu `LabeledPolygon` tak, aby implementovala rozhraní `PolygonWritable`.

1.  Metoda `read(InputStream)` vezme otevřený vstup obsahující pojmenované vrcholy,
    vrcholy načte a přidá je k existujícím vrcholům polygonu.
    Při jakékoliv chybě vstupu/výstupu nebo chybě formátu vstupních dat musí metoda atomicky selhat
    a vyhodit `IOException`. (atomicky = načítám všechno nebo nic)
    Formát vstupních dat je následující:
    *   Vstup je textový.
    *   Co vrchol, to jeden řádek.
    *   Každý řádek je ve formátu _"x y nazev vrcholu"_, tj. nejprve souřadnice vrcholu oddělené mezerou
        a poté název vrcholu (název může obsahovat mezery).
        Viz např. soubor _polygon-ok.txt_.

2.  Metoda `write(OutputStream)` zapíše vrcholy do daného výstupního proudu.
    Výstupní formát je stejný jako pro předchozí metodu.

3.  Metody `write(File)` a `read(File)` budou fungovat stejně jako předchozí,
    budou ale pracovat se souborem namísto vstupně-výstupního proudu.
    Vyhněte se opakování kódu!

4.  Vytvořte metodu `writeJson(OutputStream os)`, která bude do výstupního proudu zapisovat mapu ve formátu JSON.
    *   Využijte externí knižnici [gson](https://github.com/google/gson).
        Pro maven je potřeba přidat závislost do souboru `pom.xml` v části `<dependencies>`.
    *   Prečtěte si dokumentaci třídy _Gson_.
    *   Podle [dokumentace použijte tzv. _pretty print_](https://github.com/google/gson/blob/master/UserGuide.md#compact-vs-pretty-printing-for-json-output-format).
    *   Objekt typu _Gson_ může být znovu použitý, proto jej stačí vytvořit pouze jednou.

6.  Upravte třídu `Demo` následujícím způsobem:
    *   Třída vytvoří `LabeledPolygon` ze souboru `polygon-ok.txt`.
    *   Polygon bude také obsahovat vrchol s názvem `vrchol x` a souřednicemi `[123, 456]`.
    *   Výstup vypište do výstupního proudu `System.out` v JSON formátu.
    *   Pro kontrolu, že výstupní proud je stále otevřený pak vypište `Hello World!`.

5.  Spuštěním třídy `Draw` se načte _polygon-ok.txt_ a [vykreslí se na obrazovce
    ](https://gitlab.fi.muni.cz/pb162/pb162-course-info/wikis/draw-images).

### Hinty

- Zavírejte pouze proudy/soubory, které jste otevřeli.
- Používejte _try with resources_.
- Nastudujte si metody `Writer#flush()`, `Reader#ready()`.
- Vytvoření souboru: `new File("soubor.txt")`.
- `Demo.main` může vyhodit `IOException`.
- Namísto `\n` používejte univerzální oddělovač konců řádků, `System.lineSeparator()`. 
- Testy vytvářejí soubor `polygon-out.txt`.
