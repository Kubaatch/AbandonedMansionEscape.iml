import logika.IHra;
import logika.Hra;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HraTest {
    private IHra hra;

    @BeforeEach
    public void setUp(){
        hra = new Hra();
    }

    @AfterEach
    public void tearDown() {
        hra = null;
    }

    @Test
    public void testHry() {
        //uvítání - zapnutí hry
        assertEquals("Vítej!\n" +
                "Probudil/a ses v královské posteli v očividně dlouho opuštěném sídle.\n" +
                "Napiš 'nápověda', pokud si nevíš rady, jak hrát dál.\n" +
                "\n" +
                "Popis místnosti 'ložnice': starobylá místnost s prázdnými skříněmi a prasklým zrcadlem.\n" +
                "sousední místnosti: chodba\n" +
                "věci v místnosti: královská_postel noční_stolek svíčka\n" +
                "Obsah kapes: řízek_v_alobalu", hra.vratUvitani());

        // 1. krok jdi chodba
        assertEquals("Popis místnosti 'chodba': dlouhá rovná chodba, která vypadá jako z hororu.\n" +
                "sousední místnosti: ložnice sklep foyer\n" +
                "věci v místnosti: obraz portrét květináč kožené_boty\n" +
                "Obsah kapes: řízek_v_alobalu", hra.zpracujPrikaz("jdi chodba"));

        // 2. krok jdi foyer
        assertEquals("Popis místnosti 'foyer': vstupní místnost s prachem pokrytými soškami.\n" +
                "sousední místnosti: dveře_ven chodba jídelna studovna\n" +
                "věci v místnosti: zdobená_váza deštník lucerna\n" +
                "Obsah kapes: řízek_v_alobalu", hra.zpracujPrikaz("jdi foyer"));

        // 3. krok jdi jídelna
        assertEquals("Popis místnosti 'jídelna': jediným kusem nábytku je zde starý mahagonový stůl.\n" +
                "sousední místnosti: kuchyň foyer\n" +
                "věci v místnosti: keramický_střep stůl\n" +
                "Obsah kapes: řízek_v_alobalu", hra.zpracujPrikaz("jdi jídelna"));

        // 4. krok jdi kuchyň
        assertEquals("Popis místnosti 'kuchyň': zatuchlá místnost prolezlá plísní.\n" +
                "sousední místnosti: sklep jídelna\n" +
                "věci v místnosti: hrnec naběračka páčidlo plesnivý_sýr plísňový_sýr\n" +
                "Obsah kapes: řízek_v_alobalu", hra.zpracujPrikaz("jdi kuchyň"));

        // 5. krok seber páčidlo
        assertEquals("Sebral jsi páčidlo", hra.zpracujPrikaz("seber páčidlo"));

        // 6. krok jdi jídelna
        assertEquals("Popis místnosti 'jídelna': jediným kusem nábytku je zde starý mahagonový stůl.\n" +
                "sousední místnosti: kuchyň foyer\n" +
                "věci v místnosti: keramický_střep stůl\n" +
                "Obsah kapes: řízek_v_alobalu páčidlo", hra.zpracujPrikaz("jdi jídelna"));

        // 7. krok jdi foyer
        assertEquals("Popis místnosti 'foyer': vstupní místnost s prachem pokrytými soškami.\n" +
                "sousední místnosti: dveře_ven chodba jídelna studovna\n" +
                "věci v místnosti: zdobená_váza deštník lucerna\n" +
                "Obsah kapes: řízek_v_alobalu páčidlo", hra.zpracujPrikaz("jdi foyer"));

        // 8. krok jdi studovna
        assertEquals("Popis místnosti 'studovna': obývací pokoj se zaprášeným nábytkem a starou šachovnicí.\n" +
                "sousední místnosti: foyer knihovna\n" +
                "věci v místnosti: křeslo šachovnice gauč truhla\n" +
                "Obsah kapes: řízek_v_alobalu páčidlo", hra.zpracujPrikaz("jdi studovna"));

        // 9. krok jdi knihovna
        assertEquals("Popis místnosti 'knihovna': menší místnost plná knih všech velikostí a barev.\n" +
                "sousední místnosti: studovna\n" +
                "věci v místnosti: modrá_kniha bible zelená_kniha kožená_kniha deník černá_kniha\n" +
                "Obsah kapes: řízek_v_alobalu páčidlo", hra.zpracujPrikaz("jdi knihovna"));

        // 10. krok seber deník
        assertEquals("Sebral jsi deník", hra.zpracujPrikaz("seber deník"));

        // 11. krok přečti deník
        assertEquals("Uvnitř stojí: \n" +
                "Na poslední stránce si objevil tento text:\n" +
                "\n" +
                "S tíživým srdcem píši do svého deníku poslední zápis, kde oznamuji o mém rozhodnutí opustit toto honosné sídlo.\n" +
                "Tento krok jsem učinil(a) z nezbytných důvodů, které nejsem schopen slovy popsat.\n" +
                "Rád(a) bych vám předal(a) vědomost, že klíč k našemu domovu jsem uložil(a) ve sklepě pod \"prkno_v_podlaze\".\n" +
                "Doufám, že se tato informace stane užitečnou v době mé nepřítomnosti a umožní vám bezproblémový vstup do našeho domova.\n" +
                "\n" +
                "Bůh vám žehnej,\n" +
                "Hrabě Ignác Šlechtiprkno ze Šťastného Uvázaní", hra.zpracujPrikaz("přečti deník"));

        // 12. krok jdi studovna
        assertEquals("Popis místnosti 'studovna': obývací pokoj se zaprášeným nábytkem a starou šachovnicí.\n" +
                "sousední místnosti: foyer knihovna\n" +
                "věci v místnosti: křeslo šachovnice gauč truhla\n" +
                "Obsah kapes: řízek_v_alobalu páčidlo deník", hra.zpracujPrikaz("jdi studovna"));

        // 13. krok jdi foyer
        assertEquals("Popis místnosti 'foyer': vstupní místnost s prachem pokrytými soškami.\n" +
                "sousední místnosti: dveře_ven chodba jídelna studovna\n" +
                "věci v místnosti: zdobená_váza deštník lucerna\n" +
                "Obsah kapes: řízek_v_alobalu páčidlo deník", hra.zpracujPrikaz("jdi foyer"));

        // 14. krok jdi chodba
        assertEquals("Popis místnosti 'chodba': dlouhá rovná chodba, která vypadá jako z hororu.\n" +
                "sousední místnosti: ložnice sklep foyer\n" +
                "věci v místnosti: obraz portrét květináč kožené_boty\n" +
                "Obsah kapes: řízek_v_alobalu páčidlo deník", hra.zpracujPrikaz("jdi chodba"));


        // 15. krok jdi sklep
        assertEquals("Popis místnosti 'sklep': tmavé podzemní prostory, kde může číhat cokoliv.\n" +
                "sousední místnosti: chodba kuchyň\n" +
                "věci v místnosti: zamčená_skříň zrezivělý_zámek lopata valcha rezavý_klíč\n" +
                "Obsah kapes: řízek_v_alobalu páčidlo deník", hra.zpracujPrikaz("jdi sklep"));

        // 16. krok vypač prkno_v_podlaze
        assertEquals("Vypáčil/a jsi prkno v podlaze a zjistil/a, že pod ním leží \"rezavý_klíč\".", hra.zpracujPrikaz("vypač prkno_v_podlaze"));

        // 17. krok seber rezavý_klíč
        assertEquals("Sebral jsi rezavý_klíč", hra.zpracujPrikaz("seber rezavý_klíč"));

        // 18. jdi chodba
        assertEquals("Popis místnosti 'chodba': dlouhá rovná chodba, která vypadá jako z hororu.\n" +
                "sousední místnosti: ložnice sklep foyer\n" +
                "věci v místnosti: obraz portrét květináč kožené_boty\n" +
                "Obsah kapes: řízek_v_alobalu páčidlo deník rezavý_klíč", hra.zpracujPrikaz("jdi chodba"));

        // 19. jdi foyer
        assertEquals("Popis místnosti 'foyer': vstupní místnost s prachem pokrytými soškami.\n" +
                "sousední místnosti: dveře_ven chodba jídelna studovna\n" +
                "věci v místnosti: zdobená_váza deštník lucerna\n" +
                "Obsah kapes: řízek_v_alobalu páčidlo deník rezavý_klíč", hra.zpracujPrikaz("jdi foyer"));

        // 20. odemkni dveře_ven
        assertEquals("Odemknul jsi dveře_ven", hra.zpracujPrikaz("odemkni dveře_ven"));

        // 21. jdi dveře_ven (vyhraje hru)
        assertEquals("", hra.zpracujPrikaz("jdi dveře_ven"));

        assertTrue(hra.konecHry());
        assertEquals("Gratuluji, vyhrál jsi hru!", hra.vratEpilog());

    }
}

