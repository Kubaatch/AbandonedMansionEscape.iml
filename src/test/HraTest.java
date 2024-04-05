import logika.IHra;
import logika.Hra;
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

    /**
     * Test - scénář, ideální cesta
     * kontroluje průběh hry a zda jde vyhrát
     * obsahuje seznam příkazů pro ideální (nejkratší) průběh hrou
     */
    @Test
    public void testScenar() {
        //uvítání - zapnutí hry
        assertEquals("""
                Vítej!
                Probudil/a ses v královské posteli v očividně dlouho opuštěném sídle.
                Nevíš proč tu jsi a chceš se co nejdříve dostat ven.
                Napiš 'nápověda', pokud si nevíš rady, jak hrát dál.
                Popis místnosti 'ložnice': starobylá místnost s prázdnými skříněmi a prasklým zrcadlem.
                sousední místnosti: chodba
                věci v místnosti: královská_postel noční_stolek svíčka
                Obsah kapes: řízek_v_alobalu""", hra.vratUvitani());

        // 1. krok jdi chodba
        assertEquals("""
                Popis místnosti 'chodba': dlouhá rovná chodba, která vypadá jako z hororu.
                sousední místnosti: přístěnek ložnice sklep foyer
                věci v místnosti: obraz portrét květináč kožené_boty
                Obsah kapes: řízek_v_alobalu""", hra.zpracujPrikaz("jdi chodba"));

        // 2. krok jdi foyer
        assertEquals("""
                Popis místnosti 'foyer': vstupní místnost s prachem pokrytými soškami.
                sousední místnosti: chodba východ(zamčeno)🔒 1.patro jídelna studovna
                věci v místnosti: zdobená_váza deštník lucerna
                Obsah kapes: řízek_v_alobalu""", hra.zpracujPrikaz("jdi foyer"));

        // 3. krok jdi jídelna
        assertEquals("""
                Popis místnosti 'jídelna': jediným kusem nábytku je zde starý mahagonový stůl.
                sousední místnosti: kuchyň foyer
                věci v místnosti: keramický_střep stůl
                Obsah kapes: řízek_v_alobalu""", hra.zpracujPrikaz("jdi jídelna"));

        // 4. krok jdi kuchyň
        assertEquals("""
                Popis místnosti 'kuchyň': zatuchlá místnost prolezlá plísní.
                sousední místnosti: jídelna
                věci v místnosti: hrnec naběračka páčidlo plesnivý_sýr plísňový_sýr
                Obsah kapes: řízek_v_alobalu""", hra.zpracujPrikaz("jdi kuchyň"));

        // 5. krok seber páčidlo
        assertEquals("Sebral jsi páčidlo", hra.zpracujPrikaz("seber páčidlo"));

        // 6. krok jdi jídelna
        assertEquals("""
                Fuj! Leknul ses netopýra, který kolem tebe proletěl. Možná by ses měl něčím uklidnit.
                Popis místnosti 'jídelna': jediným kusem nábytku je zde starý mahagonový stůl.
                sousední místnosti: kuchyň foyer
                věci v místnosti: keramický_střep stůl
                Obsah kapes: řízek_v_alobalu páčidlo""", hra.zpracujPrikaz("jdi jídelna"));

        // extra krok tancuj
        assertEquals("""
                Vesele sis zatancoval/a, máš teď mnohem lepší náladu :)""",
                hra.zpracujPrikaz("tancuj"));

        // 7. krok jdi foyer
        assertEquals("""
                Popis místnosti 'foyer': vstupní místnost s prachem pokrytými soškami.
                sousední místnosti: chodba východ(zamčeno)🔒 1.patro jídelna studovna
                věci v místnosti: zdobená_váza deštník lucerna
                Obsah kapes: řízek_v_alobalu páčidlo""", hra.zpracujPrikaz("jdi foyer"));

        // 8. krok jdi studovna
        assertEquals("""
                Popis místnosti 'studovna': obývací pokoj se zaprášeným nábytkem a starou šachovnicí.
                sousední místnosti: foyer knihovna
                věci v místnosti: křeslo šachovnice gauč truhla
                Obsah kapes: řízek_v_alobalu páčidlo""", hra.zpracujPrikaz("jdi studovna"));

        // 9. krok jdi knihovna
        assertEquals("""
                Popis místnosti 'knihovna': menší místnost plná knih všech velikostí a barev.
                sousední místnosti: studovna
                věci v místnosti: modrá_kniha bible zelená_kniha kožená_kniha deník černá_kniha
                Obsah kapes: řízek_v_alobalu páčidlo""", hra.zpracujPrikaz("jdi knihovna"));

        // 10. krok seber deník
        assertEquals("Sebral jsi deník", hra.zpracujPrikaz("seber deník"));

        // 11. krok přečti deník
        assertEquals("""
                Uvnitř stojí:\s
                Na poslední stránce si objevil tento text:

                S tíživým srdcem píši do svého deníku poslední zápis, kde oznamuji o mém rozhodnutí opustit toto honosné sídlo.
                Tento krok jsem učinil(a) z nezbytných důvodů, které nejsem schopen slovy popsat.
                Rád(a) bych vám předal(a) vědomost, že klíč k našemu domovu jsem uložil(a) ve sklepě pod "prkno_v_podlaze".
                Doufám, že se tato informace stane užitečnou v době mé nepřítomnosti a umožní vám bezproblémový vstup do našeho domova.

                Bůh vám žehnej,
                Hrabě Ignác Šlechtiprkno ze Šťastného Uvázaní""", hra.zpracujPrikaz("přečti deník"));

        // 12. krok jdi studovna
        assertEquals("""
                Popis místnosti 'studovna': obývací pokoj se zaprášeným nábytkem a starou šachovnicí.
                sousední místnosti: foyer knihovna
                věci v místnosti: křeslo šachovnice gauč truhla
                Obsah kapes: řízek_v_alobalu páčidlo deník""", hra.zpracujPrikaz("jdi studovna"));

        // 13. krok jdi foyer
        assertEquals("""
                Fuj! Leknul ses netopýra, který kolem tebe proletěl. Možná by ses měl něčím uklidnit.
                Popis místnosti 'foyer': vstupní místnost s prachem pokrytými soškami.
                sousední místnosti: chodba východ(zamčeno)🔒 1.patro jídelna studovna
                věci v místnosti: zdobená_váza deštník lucerna
                Obsah kapes: řízek_v_alobalu páčidlo deník""", hra.zpracujPrikaz("jdi foyer"));

        // extra krok tancuj
        assertEquals("""
                Vesele sis zatancoval/a, máš teď mnohem lepší náladu :)""",
                hra.zpracujPrikaz("tancuj"));

        // 14. krok jdi chodba
        assertEquals("""
                Popis místnosti 'chodba': dlouhá rovná chodba, která vypadá jako z hororu.
                sousední místnosti: přístěnek ložnice sklep foyer
                věci v místnosti: obraz portrét květináč kožené_boty
                Obsah kapes: řízek_v_alobalu páčidlo deník""", hra.zpracujPrikaz("jdi chodba"));


        // 15. krok jdi sklep
        assertEquals("""
                Popis místnosti 'sklep': tmavé podzemní prostory, kde může číhat cokoliv.
                sousední místnosti: chodba
                věci v místnosti: zamčená_skříň zrezivělý_zámek lopata valcha prkno_v_podlaze
                Obsah kapes: řízek_v_alobalu páčidlo deník""", hra.zpracujPrikaz("jdi sklep"));

        // 16. krok vypač prkno_v_podlaze
        assertEquals("Vypáčil/a jsi prkno v podlaze a zjistil/a, že pod ním leží \"rezavý_klíč\".", hra.zpracujPrikaz("vypač prkno_v_podlaze"));

        // 17. krok seber rezavý_klíč
        assertEquals("Sebral jsi rezavý_klíč", hra.zpracujPrikaz("seber rezavý_klíč"));

        // 18. jdi chodba
        assertEquals("""
                Popis místnosti 'chodba': dlouhá rovná chodba, která vypadá jako z hororu.
                sousední místnosti: přístěnek ložnice sklep foyer
                věci v místnosti: obraz portrét květináč kožené_boty
                Obsah kapes: řízek_v_alobalu páčidlo deník rezavý_klíč""", hra.zpracujPrikaz("jdi chodba"));

        // 19. jdi foyer
        assertEquals("""
                Popis místnosti 'foyer': vstupní místnost s prachem pokrytými soškami.
                sousední místnosti: chodba východ(zamčeno)🔒 1.patro jídelna studovna
                věci v místnosti: zdobená_váza deštník lucerna
                Obsah kapes: řízek_v_alobalu páčidlo deník rezavý_klíč""", hra.zpracujPrikaz("jdi foyer"));

        // 20. odemkni dveře_ven
        assertEquals("Odemknul jsi východ", hra.zpracujPrikaz("odemkni východ"));

        // 21. jdi dveře_ven (vyhraje hru)
        assertEquals("", hra.zpracujPrikaz("jdi východ"));

        // kontrola ukončení hry
        assertTrue(hra.konecHry());
        assertEquals("Gratuluji, vyhrál jsi hru!", hra.vratEpilog());

    }

    /**
     * Test - Přenositelnost, PříkazSeber
     * kontroluje různé výstupy po sebrání předmětu
     * sleduje přenositelnost a existenci
     */
    @Test
    public void testPrenositelnost() {
        assertEquals("sv se nenachází v tomto prostoru.", hra.zpracujPrikaz("seber sv"));
        //předmět neexistuje

        assertEquals("Sebral jsi svíčka", hra.zpracujPrikaz("seber svíčka"));
        //úspěch

        assertEquals("svíčka se nenachází v tomto prostoru.", hra.zpracujPrikaz("seber svíčka"));
        //předmět byl sebrán, již neexistuje

        assertEquals("noční_stolek se nedá sebrat.", hra.zpracujPrikaz("seber noční_stolek"));
        //předmět nelze sebrat
    }

    /**
     * Test - PříkazSeber, PříkazPolož
     * kontroluje možnost sebrat a položit předměty
     */
    @Test
    public void testSeberPoloz() {
        assertEquals("Sebral jsi svíčka", hra.zpracujPrikaz("seber svíčka"));

        assertEquals("Položil jsi svíčka do místnosti/prostoru ložnice", hra.zpracujPrikaz("polož svíčka"));
    }

    /**
     * Test - Kapacita, PříkazSeber
     * kontroluje různé výstupy po sebrání předmětu
     * sleduje plnost kapes: když jsou plné, nelze ukládat další předměty
     */
    @Test
    public void testKapacita() {
        //úvodní posun ve hře
        hra.zpracujPrikaz("seber svíčka");
        hra.zpracujPrikaz("jdi chodba");
        //zde kapacita 2 ze 4

        assertEquals("Sebral jsi kožené_boty", hra.zpracujPrikaz("seber kožené_boty"));
        //zde kapacita 4 ze 4

        assertEquals("Snažíš se nacpat předmět květináč do plných kapes.", hra.zpracujPrikaz("seber květináč"));
        //nedovolí hráči přidat další předmět do kapes
    }

    /**
     * Test - InsanityMeter
     * kontroluje možné výstupy dle úrovně zbláznění
     */
    @Test
    public void testInsanityMeter() {
        //posune hráče blíže ke zbláznění
        hra.getHerniPlan().getInsanityMeter().setUrovenZblazneni(4);

        assertEquals("""
                Fuj! Leknul ses netopýra, který kolem tebe proletěl. Možná by ses měl něčím uklidnit.
                Popis místnosti 'chodba': dlouhá rovná chodba, která vypadá jako z hororu.
                sousední místnosti: přístěnek ložnice sklep foyer
                věci v místnosti: obraz portrét květináč kožené_boty
                Obsah kapes: řízek_v_alobalu""", hra.zpracujPrikaz("jdi chodba"));
        assertEquals(5, hra.getHerniPlan().getInsanityMeter().getUrovenZblazneni());

        //posune hráče blíže ke zbláznění
        hra.getHerniPlan().getInsanityMeter().setUrovenZblazneni(4);
        hra.zpracujPrikaz("jdi ložnice");

        assertEquals("Na chvilku sis schrupnul, zlepšilo ti to náladu...",hra.zpracujPrikaz("spinkej"));
        assertEquals(3, hra.getHerniPlan().getInsanityMeter().getUrovenZblazneni());

        hra.zpracujPrikaz("tancuj");
        assertEquals(0, hra.getHerniPlan().getInsanityMeter().getUrovenZblazneni());

        hra.zpracujPrikaz("spinkej");
        assertEquals(0, hra.getHerniPlan().getInsanityMeter().getUrovenZblazneni());

        assertEquals("""
                Popis místnosti 'chodba': dlouhá rovná chodba, která vypadá jako z hororu.
                sousední místnosti: přístěnek ložnice sklep foyer
                věci v místnosti: obraz portrét květináč kožené_boty
                Obsah kapes: řízek_v_alobalu""", hra.zpracujPrikaz("jdi chodba"));
        assertEquals(1, hra.getHerniPlan().getInsanityMeter().getUrovenZblazneni());

        //posune hráče na pokraj zbláznění
        hra.getHerniPlan().getInsanityMeter().setUrovenZblazneni(5);

        assertEquals("", hra.zpracujPrikaz("jdi sklep"));

        assertTrue(hra.konecHry());
        assertEquals("Ticho a samota v sídle tě přivedly k šílenství, prohrál jsi tuto hru.", hra.vratEpilog());
    }

    /**
     * Test - PříkazInfo, PříkazNápověda, PříkazKonec
     * kontroluje funkčnost jednoduchých jednorázových příkazů
     * sleduje zda se vypíše (správný) informující text
     */
    @Test
    public void testPrikazy() {
        assertEquals("""
                Popis místnosti 'ložnice': starobylá místnost s prázdnými skříněmi a prasklým zrcadlem.
                sousední místnosti: chodba
                věci v místnosti: královská_postel noční_stolek svíčka
                Obsah kapes: řízek_v_alobalu""", hra.zpracujPrikaz("info"));

        assertEquals("""
                Tvým úkolem je najít klíč, který ti odemkne dveře ven z tohoto sídla.
                                
                Můžeš zadat tyto příkazy:
                polož nápověda teleport přečti spinkej tancuj sněz jdi vypač odemkni seber info konec\s""", hra.zpracujPrikaz("nápověda"));

        assertEquals("hra ukončena příkazem konec", hra.zpracujPrikaz("konec"));
        assertTrue(hra.konecHry());

        assertEquals("Dík že jste si zahráli, ahoj.", hra.vratEpilog());
    }

    /**
     * Test - PříkazSněz
     * kontroluje funkčnost příkazu sněz a všechny možné výstupy
     */
    @Test
    public void testPrikazSnez() {
        assertEquals("Co chceš sníst? Musíš napsat co chceš sníst...", hra.zpracujPrikaz("sněz"));

        assertEquals("Chceš toho sníst nějak moc. Můžeš najednou sníst jen jednu věc.", hra.zpracujPrikaz("sněz x y"));

        assertEquals("x nemáš u sebe v kapsách...", hra.zpracujPrikaz("sněz x"));

        hra.zpracujPrikaz("seber svíčka");
        assertEquals("Sníst svíčka by pro tebe nedopadlo úplně dobře.", hra.zpracujPrikaz("sněz svíčka"));

        assertEquals("Snědl jsi řízek_v_alobalu a moc ti chutnalo.", hra.zpracujPrikaz("sněz řízek_v_alobalu"));
    }

    /**
     * Test - PříkazTeleport
     * kontroluje možnost teleportace a pomocí proměnné srovná výpis místnosti s aktuální místností
     */
    @Test
    public void testTeleport() {
        hra.zpracujPrikaz("jdi chodba");
        hra.zpracujPrikaz("jdi přístěnek");

        assertEquals(hra.zpracujPrikaz("teleport"), "S výpadkem paměti ses probudil v místnosti " + hra.getHerniPlan().getAktualniProstor().getNazev());
    }

}

