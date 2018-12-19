## První iterace

Iterace pro seznámění se s objekty a zapouzdřením.

1.  Vytvořte třídu `Vertex2D` představující bod ve 2D prostoru se souřadnicemi x a y.
    *   Třída bude v balíku `cz.muni.fi.pb162.project.geometry` (příslušný balík musíte vytvořit).
    *   Třída bude mít dva atributy typu `double`, ve kterých si bude uchovávat hodnoty souřadnic `x` a `y`.
        Jejich implicitní hodnota bude 0.
    *   Konstruktory zatím neřešte.
    *   Přidejte do třídy tzv. _gettery_ a _settery_, konkrétně metody:
        *   `double getX()` a `double getY()` pro získání hodnot atributů,
        *   `void setX(double newX)` a `void setY(double newY)` pro nastavení hodnot atributů.
    *   Metoda `String getInfo()` vrátí pro bod na souřadnicích x=2.0, y=3.0 vrátí **10 znaků** (včetně mezery):
        `[2.0, 3.0]`.
    *   Metoda `double sumCoordinates()` vrátí součet souřadnic x a y.
    *   Metoda `void move(Vertex2D vertex)` vezme jiný 2D bod jako vstupní parametr a posune vrchol o `vertex` souřadnice.
        Například, zavolání `[2, 3].move([1, 1])` posune vrchol `[2, 3]` na souřadnice `[3, 4]`.
    *   Nastavte viditelnost atributů a metod tak, aby splňovaly podmínky zapouzdření.

2.  Upravte spustitelnou třídu `Demo`.
    *   Třídu nechte v balíku `cz.muni.fi.pb162.project`.
    *   Vyplňte jméno autora (`@author`).
    *   Smažte výpis `Hello world!`.
    *   Třída vytvoří 2 vrcholy se souřadnicemi `[2, 3]` a `[1, 1]`.
    *   První vrchol posuňte o souřadnice druhého vrcholu.
    *   Pak vypíše informace o obou vrcholech.
    *   Program vypíše na standardní výstup:

        ~~~~
        [3.0, 4.0]
        [1.0, 1.0]
        ~~~~

4.  Otestujte třídy pomocí testovacích tříd v balíčku **src/test/java**.
    Po úspěšném otestování odevzdejte do odevzdávárny nebo na git a nechte zkontrolovat cvičícím.

### Hinty

- Defaultní hodnota typu `double` je 0.
- Gettery a settery mají pravidla pro jejich pojmenování, tj. atributy se musí jmenovat `x` a `y`.
- Pro zapouzdření obvykle platí: atributy jsou privátní, metody jsou veřejné.
- Třídu `Vertex2D` je nutno **naimportovat** do třídy `Demo`, protože se nachází v jiném balíku.
- Ujistěte se, že spouštíte **všechny** testy.