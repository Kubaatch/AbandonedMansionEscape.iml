import logika.IHra;
import logika.Hra;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Třída HraTest slouží pro zjednodušené testování funkčnosti hry.
 * Obsahuje několik testů, každý má vlastní konkrétní využití.
 *
 * @author    Jakub Hřebíček
 * @version   v1.8 2024/04/05
 */
public class HraTest {
    private IHra hra;

    /**
     * metoda, která se spustí před každým testem v této třídě
     * zakládá novou hru, ve které testy probíhají
     */
    @BeforeEach
    public void setUp(){
        hra = new Hra();
    }

    /**
     * metoda, která se spustí po každém testu v této třídě
     * ukončí hru vytvořenou v metodě setUp()
     */
    @AfterEach
    public void tearDown() {
        hra = null;
    }

    /**
     * Test - scénář, ideální cesta
     * Test - PříkazJdi, PříkazOdemkni, PříkazVypač
     * obsahuje seznam příkazů pro ideální (nejkratší) průběh hrou
     * kontroluje průběh hry a zda jde vyhrát
     * kontroluje správný průběh některých příkazů
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
        assertEquals("Vypáčil/a jsi prkno_v_podlaze a našel/našla předmět rezavý_klíč", hra.zpracujPrikaz("vypač prkno_v_podlaze"));

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
     * Test - PříkazSeber, přenositelnost
     * kontroluje různé výstupy po sebrání předmětu
     */
    @Test
    public void testSeber() {
        //chybný počet parametrů
        assertEquals("Co chceš sebrat? Musíš zadat název předmětu...", hra.zpracujPrikaz("seber"));
        assertEquals("Chceš toho sebrat nějak moc. Můžeš najednou sebrat jen jednu věc.", hra.zpracujPrikaz("seber x y"));

        //předmět neexistuje
        assertEquals("sv se nenachází v tomto prostoru.", hra.zpracujPrikaz("seber sv"));

        //úspěch
        assertEquals("Sebral jsi svíčka", hra.zpracujPrikaz("seber svíčka"));

        //předmět byl sebrán, již neexistuje
        assertEquals("svíčka se nenachází v tomto prostoru.", hra.zpracujPrikaz("seber svíčka"));

        //předmět nelze sebrat
        assertEquals("noční_stolek se nedá sebrat.", hra.zpracujPrikaz("seber noční_stolek"));
    }

    /**
     * Test - PříkazPolož
     * kontroluje různé výstupy po položení předmětu
     */
    @Test
    public void testPoloz() {
        //chybný počet parametrů
        assertEquals("Co chceš položit? Musíš zadat název předmětu...", hra.zpracujPrikaz("polož"));
        assertEquals("Chceš toho položit nějak moc. Můžeš najednou položit jen jednu věc.", hra.zpracujPrikaz("polož x y"));

        //předmět neexistuje
        assertEquals("xy nemáš v kapsách.", hra.zpracujPrikaz("polož xy"));

        //úspěch
        hra.zpracujPrikaz("seber svíčka");
        assertEquals("Položil jsi svíčka do místnosti/prostoru ložnice", hra.zpracujPrikaz("polož svíčka"));

        //předmět byl sebrán, již neexistuje
        assertEquals("svíčka nemáš v kapsách.", hra.zpracujPrikaz("polož svíčka"));
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
        assertEquals(2, hra.getHerniPlan().getKapsy().getKapacita());

        assertEquals("Sebral jsi kožené_boty", hra.zpracujPrikaz("seber kožené_boty"));
        //zde kapacita 4 ze 4
        assertEquals(4, hra.getHerniPlan().getKapsy().getKapacita());

        assertEquals("Snažíš se nacpat předmět květináč do plných kapes.", hra.zpracujPrikaz("seber květináč"));
        assertEquals(4, hra.getHerniPlan().getKapsy().getKapacita());
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

        //upozornění na blížící se zešílení
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

        //spánek sníží úroveň zbláznění
        assertEquals("Na chvilku sis schrupnul, zlepšilo ti to náladu...",hra.zpracujPrikaz("spinkej"));
        assertEquals(3, hra.getHerniPlan().getInsanityMeter().getUrovenZblazneni());

        //tanec vynuluje úroveň zbláznění
        hra.zpracujPrikaz("tancuj");
        assertEquals(0, hra.getHerniPlan().getInsanityMeter().getUrovenZblazneni());

        //úroveň zbláznění nemůže být nižší než 0
        hra.zpracujPrikaz("spinkej");
        assertEquals(0, hra.getHerniPlan().getInsanityMeter().getUrovenZblazneni());

        //navýšení úrovně zbláznění o 1
        assertEquals("""
                Popis místnosti 'chodba': dlouhá rovná chodba, která vypadá jako z hororu.
                sousední místnosti: přístěnek ložnice sklep foyer
                věci v místnosti: obraz portrét květináč kožené_boty
                Obsah kapes: řízek_v_alobalu""", hra.zpracujPrikaz("jdi chodba"));
        assertEquals(1, hra.getHerniPlan().getInsanityMeter().getUrovenZblazneni());

        //posune hráče na pokraj zbláznění
        hra.getHerniPlan().getInsanityMeter().setUrovenZblazneni(5);

        //hráč prohrál hru, zešílel
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
        //příkaz info
        //chybný počet parametrů
        assertEquals("Napsal jsi toho nějak moc...", hra.zpracujPrikaz("info x"));

        //správný výpis příkazu
        assertEquals("""
                Popis místnosti 'ložnice': starobylá místnost s prázdnými skříněmi a prasklým zrcadlem.
                sousední místnosti: chodba
                věci v místnosti: královská_postel noční_stolek svíčka
                Obsah kapes: řízek_v_alobalu""", hra.zpracujPrikaz("info"));

        //příkaz nápověda
        //chybný počet parametrů
        assertEquals("Napsal jsi toho nějak moc. Stačí napsat jen 'nápověda'", hra.zpracujPrikaz("nápověda x"));

        //správný výpis příkazu
        assertEquals("""
                Tvým úkolem je najít klíč, který ti odemkne dveře ven z tohoto sídla.
                                
                Můžeš zadat tyto příkazy:
                polož nápověda teleport přečti spinkej tancuj sněz jdi vypač odemkni seber info konec\s""", hra.zpracujPrikaz("nápověda"));

        //příkaz konec
        //chybný počet parametrů
        assertEquals("Napsal jsi toho nějak moc. Stačí napsat jen 'konec'", hra.zpracujPrikaz("konec x"));

        //správný výpis příkazu
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
        //chybný počet parametrů
        assertEquals("Co chceš sníst? Musíš napsat co chceš sníst...", hra.zpracujPrikaz("sněz"));
        assertEquals("Chceš toho sníst nějak moc. Můžeš najednou sníst jen jednu věc.", hra.zpracujPrikaz("sněz x y"));

        //hráč nemá předmět u sebe v kapsách
        assertEquals("x nemáš u sebe v kapsách...", hra.zpracujPrikaz("sněz x"));

        //předmět nelze sníst
        hra.zpracujPrikaz("seber svíčka");
        assertEquals("Sníst svíčka by pro tebe nedopadlo úplně dobře.", hra.zpracujPrikaz("sněz svíčka"));

        //správný výpis příkazu
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

        //chybný počet parametrů
        assertEquals("Napsal jsi toho nějak moc...", hra.zpracujPrikaz("teleport x"));

        //správný výpis příkazu
        assertEquals(hra.zpracujPrikaz("teleport"), "S výpadkem paměti ses probudil v místnosti " + hra.getHerniPlan().getAktualniProstor().getNazev());
    }

    /**
     * Test - PříkazSpinkej
     * kontroluje funkčnost příkazu spinkej a všechny možné výstupy
     */
    @Test
    public void testSpinkej() {
        //chybný počet parametrů
        assertEquals("Napsal jsi toho nějak moc...", hra.zpracujPrikaz("spinkej x"));

        //chybná místnost (ne ložnice)
        hra.zpracujPrikaz("jdi chodba");
        assertEquals("Tady se nemůžeš vyspat. Dost možná tu straší...\uD83D\uDC7B", hra.zpracujPrikaz("spinkej"));

        //správný výpis příkazu
        hra.zpracujPrikaz("jdi ložnice");
        assertEquals("Na chvilku sis schrupnul, zlepšilo ti to náladu...", hra.zpracujPrikaz("spinkej"));
    }

    /**
     * Test - PříkazTancuj
     * kontroluje funkčnost příkazu tancuj a všechny možné výstupy
     */
    @Test
    public void testTancuj() {
        //chybný počet parametrů
        assertEquals("Napsal jsi toho nějak moc...", hra.zpracujPrikaz("tancuj x"));

        assertEquals("Vesele sis zatancoval/a, máš teď mnohem lepší náladu :)", hra.zpracujPrikaz("tancuj "));
    }
}

