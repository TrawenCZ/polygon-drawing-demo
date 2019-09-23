## Druhá iterace

Cvičení zaměřené na základní práci s atributy, metodami a na definici vlastních konstruktorů.

1.  Upravte třídu `Vertex2D` následujícím způsobem:
    *   Třída bude mít definovaný konstruktor o dvou parametrech `x` a `y`.
    *   Metodu `getInfo()` přejmenujte na `toString()`.
        Je to standardní metoda, která existuje v každé třídě, proto nad hlavičku metody přidejte anotaci `@Override`.
        Podrobnosti se dozvíte později.
    *   Odstraňte metody `sumCoordinates` i `move`. Již nebudou potřeba.
    *   Vytvořte metodu `Vertex2D createMiddle(Vertex2D otherVertex)`, která vytvoří a vrátí bod uprostřed,
        tj. `[2, 3].createMiddle([1, 1])` vytvoří bod `[1.5, 2]`.
        Bod má souřadnice _[(x<sub>1</sub>+x<sub>2</sub>)/2, (y<sub>1</sub>+y<sub>2</sub>)/2]_.
2.  Vytvořte třídu `Triangle` v balíku `cz.muni.fi.pb162.project.geometry`.
    *   Trojúhelník se skládá ze tří vrcholů typu `Vertex2D` a bude mít jeden atribut typu **pole vrcholů**.
    *   Konstruktor bude mít **3 parametry typu `Vertex2D`**.
    *   Metoda `Vertex2D getVertex(int index)` vrátí _index_-tý vrchol.
        Jestli je _index_ menší než 0 nebo větší než 2, vrátí metoda hodnotu `null`.
        Když je _index_ 0, vrátí první vrchol, jestli 1 tak druhý, jestli 2 pak třetí.
    *   To stejné platí pro metodu `void setVertex(int index, Vertex2D vertex)`. 
	    Pokud je _index_ mimo rozsah, metoda nic neudělá.
    *   Metoda `String toString()` vrátí řetězec:

        ~~~~
        "Triangle: vertices=[ax, ay] [bx, by] [cx, cy]"
        ~~~~
        Využijte metodu `toString()` ze třídy `Vertex2D`.
3. Trojúhelník chceme dělit na tři menší trojúhelníky. Implementujte proto následujcí metody.

    ![rozdělený trojúhelník](images/02a.png)
    *Původní trojúhelník (vlevo) a rozdělený na podtrojúhelníky (vpravo).*
    *   Třída `Triangle` bude obsahovat atribut typu `Triangle[]`.
        Jakmile dojde k rozdělení pomocí metody `divide()`, uloží se do pole tři menší trojúhelníky
        (na obrázku černé).
    *   Metoda `boolean isDivided()` zjistí, jestli již došlo k rozdělení trojúhelníka
        (menší trojúhelníky byly vytvořeny, tj. nejsou `null`).
    *   Metoda `Triangle getSubTriangle(int index)` vrátí `index`-tý podtrojúhelník, kde `index` je číslo mezi
        0 a 2.
        Pokud je `index` mimo tento rozsah, nebo pokud trojúhelník není dosud rozdělen, vrátí metoda `null`.
    *   Metoda `boolean divide()` rozdělí trojúhelník, tj. vytvoří tři menší trojúhelníky, uloží je do atributů
        a vrátí `true`.
        Pokud již trojúhelník byl rozdělen, metoda nic neprovede a vrátí `false`.
        Vrcholy menších trojúhelníků jsou vždy v polovině délky stran původního trojúhelníka (viz metoda `createMiddle`).

4.  Upravte třídu `Demo` následujícím způsobem:
    *   Třídu přesuňte do balíku `cz.muni.fi.pb162.project.demo`.
    *   Zrušte vytváření proměnných i výpis textu.
    *   Třída nově vytvoří trojúhelník se souřadnicemi _[-100, 0] [0, 100] [100, -100]_.
    *   Na std. výstup vypíše informace o trojúhelníku. Po spuštění by výstup měl vypadat takto:

        ~~~~
        Triangle: vertices=[-100.0, 0.0] [0.0, 100.0] [100.0, -100.0]
        ~~~~
5.  Správnost implementace si ověřte jednotkovými testy.
    Pak spustíte třídu `Draw` v balíčku `demo`, zobrazí se vám [trojúhelník se třemi
    podtrojúhelníky](https://gitlab.fi.muni.cz/pb162/pb162-course-info/wikis/draw-images).

6.  Zdokumentujte třídy pomocí [_JavaDoc_](https://en.wikipedia.org/wiki/Javadoc).
    Jméno musí být ve formátu `@author Jmeno Prijmeni` včetně mezery. Nastavte si generování jména automaticky jak je popsáno
    [zde](https://gitlab.fi.muni.cz/pb162/pb162-course-info/wikis/working-with-ide).
    Settery, gettery, křekryté metody (`@Override`) a privátní metody nemusíte pomocí javadoc dokumentovat.
    Checkstyle se spouští automaticky při překladu. Pokud ho chcete spustit samostatně, můžete zavolat příkaz:

        mvn clean install -Dcheckstyle.fail=true

### Hinty

- Metoda `createMiddle` je volána nad existujícím objektem, tzv. `this`.
- Pole vrcholů se vytvoří `Vertex2D[] vertices = new Vertex2D[3];` a práce s prvkem: `vertices[0]`.
- Používejte gettery namísto přímého přístupu.
- Vytvořte pomocnou privátní metodu `boolean isInRange(int index)` na zjištění jestli je index 0, 1 nebo 2.
- Při metodě `getVertex(index)` není potřeba klíčové slovo `else`, protože `return` udělá okamžitý návrat z metody.
- Metoda `toString()` je standardní metoda, která se automaticky volá kdykoliv je třeba převést objekt na text.
  Není tedy nutno ji volat explicitně, při výpisu objektu pomocí `System.out.println` se zavolá automaticky.
- Metodu `divide` si implementujte nakonec (obsah metody nechte prázdný), raději nejprve vyzkoušejte třídu `Demo`.
