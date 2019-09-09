## První iterace

Iterace pro seznámení se s objekty a zapouzdřením.

1.  Vytvořte třídu `Vertex2D` představující bod ve 2D prostoru se souřadnicemi *X* a *Y*.
    *   Třída bude v balíku `cz.muni.fi.pb162.project.geometry` (příslušný balík musíte vytvořit).
    *   Třída bude mít dva atributy typu `double`, ve kterých si bude uchovávat hodnoty souřadnic *X* a *Y*.
        Jejich implicitní hodnota bude 0.
    *   Konstruktory zatím nevytvářejte.
    *   Přidejte do třídy tzv. _gettery_ a _settery_, konkrétně metody:
        *   `double getX()` a `double getY()` pro získání hodnot atributů,
        *   `void setX(double newX)` a `void setY(double newY)` pro nastavení hodnot atributů.
    *   Metoda `String getInfo()` vrátí formátovaný popis souřadnic podle následujícího příkladu:
        Pro bod na souřadnicích x=2.0, y=3.0 vrátí **10 znaků** (včetně mezery):
        `[2.0, 3.0]`.
    *   Metoda `double sumCoordinates()` vrátí součet souřadnic *X* a *Y*.
    *   Metoda `void move(Vertex2D vertex)` vezme jiný 2D bod jako vstupní parametr a posune vrchol o `vertex` souřadnice.
        Například, zavolání `[2, 3].move([1, 1])` posune vrchol `[2, 3]` na souřadnice `[3, 4]`.
    *   Nastavte viditelnost atributů a metod tak, aby splňovaly podmínky zapouzdření.
    *   Doplňte nezbytný javadoc. U getterů a setterů se javadoc psát nemusí, protože jejich účel i použití jsou zřejmé.

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

3.  Zdokumentujte obě třídy pomocí [_JavaDoc_](https://en.wikipedia.org/wiki/Javadoc).
    V této chvíli nám ještě nejde o absolutní správnost dokumentace, ale je nutné, aby prošel *checkstyle*.
    U dokumentace tříd proto vyplňte autora (`@author`) se svým celým jménem. Dokumentaci metod si nechte vygenerovat a buďto ji vyplňte, 
    nebo vyplnění nechte na příště.

4.  Otestujte třídy pomocí testovacích tříd v balíčku **src/test/java**.
    Po úspěšném otestování odevzdejte do odevzdávárny nebo na git a nechte zkontrolovat cvičícím.
    Odevzdaná iterace musí projít *testy* i *checkstyle*!

### Hinty

- Defaultní hodnota atributů typu `double` je `0.0`.
- Gettery a settery mají jasná pravidla pro jejich pojmenování s ohledem na názvy příslušných atributů. 
  A protože názvy getterů a setterů jsou dány zadáním, je třeba od nich odvodit i názvy příslušných atributů.
- Pro zapouzdření obvykle platí: atributy jsou privátní, metody jsou veřejné.
- Třídu `Vertex2D` je nutno **naimportovat** do třídy `Demo`, protože se nachází v jiném balíku.
- Ujistěte se, že spouštíte **všechny** testy.
