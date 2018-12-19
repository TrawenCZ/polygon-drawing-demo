## Čtvrtá iterace

Cvičení zaměřené na statické metody, implementaci a použití rozhraní.

1.  Vytvořte třídu `SimpleMath` v balíku `cz.muni.fi.pb162.project.utils`.
    *   Třída bude implementovat pouze _statické_ metody.
    *   Statická metoda `double minX(Triangle triangle)` vrátí nejmenší X-ovou souřadnici.
    *   Statická metoda `double minY(Triangle triangle)` vrátí nejmenší Y-ovou souřadnici.
    *   Obdobně pro metody `maxX` a `maxY`.
    *   Trojúhelník neobsahuje `null` prvky.

2.  Upravte třídy `Triangle` a `Circle` tak, aby implementovaly rozhraní `Measurable`.
    *   Výška/šířka trojúhelníku se vypočítá jako rozdíl maximální a minimální x-ové (u šířky) respektive y-ové
        (u výšky) souřadnice vrcholů:

        ![šířka objektů](images/04a.png)
    *   Využijte statické metody ze třídy `SimpleMath`.

3.  V balíku `utils` vytvořte třídu `Gauger` ("měřidlo") se dvěma statickými přetíženými metodami `printMeasurement`:
    *   První metoda vezme libovolný měřitelný objekt (tj. libovolný objekt implementující rozhraní `Measurable`) a
        *   na standardní výstup vypíše _"Width: \<w\>"_, kde \<w\> je hodnota šířky,
        *   na další řádek vypíše _"Height: \<h\>"_, kde \<h\> je hodnota výšky.
    *   Druhá metoda vezme trojúhelník (objekt typu `Triangle`) a
        *   na standardní výstup vypíše informace o objektu, viz metoda `toString()`,
        *   na další řádek vypíše _"Width: \<w\>"_, kde \<w\> je opět hodnota šířky,
        *   na další řádek vypíše _"Height: \<h\>"_, kde \<h\> je opět hodnota výšky.
    *   Vyhněte se opakování kódu tím, že druhá varianta metody bude volat tu první. Pozor ale, ať nevolá sebe sama.
        Došlo by k zacyklení (`StackOverflowException`).

4.  Třída `Circle` bude implementovat rozhraní `Circumcircle` (opsaná kružnice).
    Opsanou kružnicí je kružnice sama, proto netřeba implementovat žádné nové metody.
    Přidejte jenom anotaci `@Override`.

5.  V balíku `geometry` vytvořte třídu `Square`, která reprezentuje čtverec otočený o 45°:
    *   První konstruktor bude obsahovat souřadnice středu opsané kružnice a průměr opsané kružnice.
    *   Druhý konstruktor bude obsahovat objekt typu `Circumcircle` (souřadnice středu a poloměr).
    *   Třída bude taky implementovat rozhraní `Circumcircle`:
    *   Metoda `Vertex2D getVertex(int index)` vrátí vrchol na daném indexu v pořadí: levý, dolní, pravý, horní.
        Jestli je index mimo rozsah, vrátí metoda `null`.
    *   Nezapomeňte na metodu `toString()`:

            "Square: vertices=[ax, ay] [bx, by] [cx, cy] [dx, dy]"

        přičemž zpráva obsahuje jen 3 mezery mezi vrcholy.

6.  V balíku `geometry` vytvořte třídu `Snowman`:
    *   Sněhulák se skládá ze **čtyř** opsaných kružnic.
        Počet kružnic ale půjde lehce změnit v době překladu.
    *   Konstruktor bude jako svůj první parametr brát parametr typu `Circumcircle` (spodní kružnice), a jako druhý
        parametr zmenšovací faktor (poloměru kružnice) pro kružnice nad ní (reálné číslo o rozsahu `(0..1>`).
        V případě, že vstupní parametr nebude z požadovaného rozsahu, použije se neveřejná pojmenovaná konstanta `0.8`.
    *   První kružnice je první argument konstruktoru, druhá bude položená na první s poloměrem zmenšeným o zmenšovací
        faktor, třetí i čtvrtá kružnice bude vytvořena stejným způsobem.
    *   Celý sněhulák vznikne v konstruktoru, nebojte se ale kód rozdělit do menších privátních metod.
    *   Metoda `Circumcircle[] getBalls()` vrátí pole kružnic, od nejspodnější po nejvyšší (nejmenší).

7. Demo vytvoří čtverec se středem `[0, 0]`, průměrem kružnice `100` a vypíše o něm informace na standardní výstup.

8. Draw vykreslí [sněhuláka, jehož spodní kružnice má v sobě vepsaný zelený
   čtverec](https://gitlab.fi.muni.cz/pb162/pb162-course-info/wikis/draw-images).

### Hinty

- Minimální/maximální hodnota musí být nainicializovaná na první prvek, nebo na konstanty
  `Double.POSITIVE_INFINITY`/`Double.NEGATIVE_INFINITY`.
- Při implementaci metod rozhraní používejte anotaci `@Override`.
- Při volání `printMeasurement` je nutno přetypovat objekt na rozhraní. Dojde k tzv. "ořezání" metod.
- Konstruktor `Square` bere konkrétní implementace rozhraní `Circumcircle`, představte si tam např. `Circle`.
- V `Square` v metodě `toString` se dá použít `Stringbuilder` a pak metoda `String#trim()`.
- `Snowman` bude obsahovat konstantu udávající kružnic.
