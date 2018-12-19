## Třetí iterace

Cvičení zaměřené na přetěžování vlastních konstruktorů a metod.

1.  Ve třídě `Vertex2D`:
    *   Udělejte třídu `Vertex2D` neměnnou (_immutable_), tj. odstraňte settery a nastavte všechny attributy `final`.
    *   Přidejte metodu `double distance(Vertex2D vertex)`, která vezme jiný 2D bod jako vstupní parametr a vrátí jeho
        eukleidovskou vzdálenost. Vzdálenost bodů se vypočítá jako:
    ![vzorec](images/03a.png)
    *   Pokud je vstupní argument `null`, pak metoda vrátí hodnotu `-1.0` jako indikátor chyby (vzdálenost je vždy
        nezáporná).

2.  Vytvořte třídu `Circle`.
    *   Třída bude mít konstruktor se dvěma parametry (v tomto pořadí): _střed_ (center) typu `Vertex2D`
        a _poloměr_ (radius) typu `double`.
        Atributy budou neměnné.
    *   Třída bude mít _bezparametrický konstruktor_, který vytvoří jednotkovou kružnici se středem v počátku
        souřadného systému (střed `[0, 0]`, poloměr `1`).
    *   **Bezparametrický konstruktor bude volat parametrický konstruktor** a předá mu potřebné hodnoty.
    *   Pro poloměr a střed vygenerujte gettery `getRadius()` a `getCenter()`.
    *   Metoda `toString` bude vracet řetězec ve formátu:

            "Circle: center=[<x>, <y>], radius=<radius>"

        kde `<x>` a `<y>` jsou hodnoty příslušných souřadnic středu a `<radius>` je hodnota poloměru.

3.  Upravte třídu `Triangle` následujícím způsobem:
    *   Odstraňte setter, nastavte atributy jako `final`.
        Třída nemůže být neměnná, protože metoda `divide` mění vlastnosti trojúhelníka.
    *   Přidejte metodu `boolean isEquilateral()`, která vrátí `true`, jestliže je trojúhelník rovnostranný.
        Protože pracujeme s reálnými čísly, nelze jednoduše porovnávat délky stran pomocí `d1 == d2`.
        Je nutné použít test, který bude považovat dvě reálná čísla za shodná, pokud se liší jen málo:

            Math.abs(d1-d2) < 0.001

        kde `0.001` je tolerovaná absolutní odchylka a **bude definovaná jako privátní konstanta**.
    *   Vytvořte přetíženou metodu `boolean divide(int depth)`, která rozdělí trojúhelník na podtrojúhelníky.
        Výsledkem bude [_Sierpińského trojúhelník_](http://en.wikipedia.org/wiki/Sierpinski_triangle):
             ![Sierpińského trojúhelník](images/03b.png)
             *Sierpińského trojúhelníky hloubky 0 až 4.*
        *   Parametr `depth` udává hloubku dělení. Nula značí žádné dělení (jsme na konci rekurze), 1 znamená,
            že dojde k jednomu rozdělení původního trojúhelníka, atd.
        *   Jestli je `depth` nula, rekurze se ukončí vrácením `false` (trojúhelník na úrovni nula je již rozdělen).
        *   Záporná hodnota je považována za chybu, kterou metoda indikuje tím, že vrátí `false`.
        *   Metoda použije existující metodu `divide()`, a pak zavolá `divide(int depth)` na svých podtrojúhelnících
            s parametrem `depth` o jedna nižší a pak vrátí `true`.
    *   Vytvořte konstruktor se 4 parametry, čtvrtý parametr reprezentuje hloubku zanoření.
        Konstruktor zavolá předešlý konstruktor a pak rozdělí trojúhelník.

4.  Po spuštění třídy `Draw` se na obrazovce [vykreslí _Sierpińského trojúhelníky_ hloubky 4 a červená
    kružnice](https://gitlab.fi.muni.cz/pb162/pb162-course-info/wikis/draw-images).

### Hinty

- Metody pro matematické operátory jsou ve třídě `Math`.
  Např. odmocnina se vypočítá pomocí statické metody `Math.sqrt()`.
- Volání konstruktoru v konstruktoru se provádí klíčovým slovem `this`.
- Není potřeba volat `toString()`, metoda se zavolá automaticky.
- Konstanta musí být pouze jedna (`static`) a neměnná (`final`).
