## Osmá iterace

Cvičení zaměřené na práci s výjimkami a vnořenými kolekcemi.

1.  Vytvořte výjimky v balíku `cz.muni.fi.pb162.project.exception`:
    *   `TransparentColorException` je _hlídaná_ výjimka při kreslení stejnou barvou na stejném podkladu,
        např. bílou tužkou na bílý papír.
    *   `EmptyDrawableException` je _hlídaná_ výjimka, kdy na kreslícím objektu není nic namalovaného.
    *   `MissingVerticesException` reprezentuje _**nehlídanou**_ výjimku, kdy v kolekci není dostatek vrcholů.

    Všechny výjimky budou mít alespoň dva konstruktory, které:
    *   umožní nastavit **řetězec** s chybovou hláškou,
    *   bude mít **řetězec** i **příčinu** (cause) výjimky: výjimku, která byla bezprostřední příčinou této výjimky.

2. Upravte konstruktor `SimplePolygon` tak, aby vyhazoval `MissingVerticesException` když pole obsahuje méně než 3 vrcholy. 

3. Upravte `Paper` následujícím způsobem:
    *   Metoda `eraseAll()` volána na čistém (prázdném) papíře vyhodí `EmptyDrawableException`.
    *   Metoda `drawPolygon(polygon)` bude vyhazovat `TransparentColorException` při kreslení bílou barvou.
        Výjimka bude obsahovat textový popis s názvem dané barvy.
    *   `Paper` bude implementovat rozhraní `PolygonFactory`.
    *   Metoda `Polygon tryToCreatePolygon(List<Vertex2D>)` se pokusí vytvořit polygon z kolekce vrcholů.
        *   Pokud je vstupní argument `null`, metoda vyhodí `NullPointerException`.
        *   Metoda si vstupní kolekci nakopíruje (nebude modifikovat původní kolekci).
        *   Pokud při vytváření polygonu nastane chyba `IllegalArgumentException`,
            metoda výjimku pohltí, odstraní z kolekce všechny vrcholy `null` a zkusí to znovu.
            Metoda propouští výjimku `MissingVerticesException`.
			*   _Pozn.: Požadované chování porušuje princip používání výjimek. Logičtější a jednodušší by bylo nejprve zkontrolovat a odebrat null vrcholy. Tím by se také předešlo zbytečnému vyhazování výjimky konstruktorem třídy CollectionPolygon. Požadované chování je pouze z důvodu procvičení práce s výjimkami._
    *   Metoda `void tryToDrawPolygons(List<List<Vertex2D>>)` bere seznam seznamů vrcholů
        (tj. seznam polygonů uložených zatím jako kolekce vrcholů).
        *   Metoda se pokusí z každé kolekce vytvořit polygon (`tryToCreatePolygon`)
            a následně ho nakreslit (`drawPolygon`).
        *   Pokud při kreslení polygonu nastane výjimka `TransparentColorException`,
            další polygony budou kresleny černou barvou.
        *   Pokud během vytváření nastane výjimka `MissingVerticesException` nebo `NullPointerException`,
            metoda výjimky zachytí a pokusí se vytvořit a namalovat další polygon.
            Pokud se nepodařilo namalovat **žádný polygon**, vyhodí se `EmptyDrawableException`
            s příčinou **poslední** chyby.
    *   Přidejte metodu `Collection<Polygon> getPolygonsWithColor(Color color)`,
        která vrátí všechny polygony s `color` barvou.
        Můžete využít lambda streamy, konkrétně `filter`, `map`, `collect`.

3. Spuštění třídy `Draw`
[vykreslí černobílý domeček](https://gitlab.fi.muni.cz/pb162/pb162-course-info/wikis/draw-images).

### Hinty

- Hlavičky metod, které vyhazují hlídané výjimky musí obsahovat `throws`.
- Pro metodu `tryToDrawPolygons` je vhodné použít iterátor; má metodu `hasNext`.
 
