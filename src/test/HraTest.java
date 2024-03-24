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
                "Obsah košíku: řízek_v_alobalu", hra.vratUvitani());

        // 1. krok jdi chodba
        assertEquals("Popis místnosti 'chodba': dlouhá rovná chodba, která vypadá jako z hororu.\n" +
                "sousední místnosti: ložnice sklep foyer\n" +
                "věci v místnosti: obraz-autoportrét\n" +
                "Obsah košíku: řízek_v_alobalu", hra.zpracujPrikaz("jdi chodba"));

        // 2. krok jdi foyer
        assertEquals("Popis místnosti 'foyer': vstupní místnost s prachem pokrytými soškami.\n" +
                "sousední místnosti: dveře_ven chodba jídelna studovna\n" +
                "věci v místnosti: zdobená_váza deštník lucerna\n" +
                "Obsah košíku: řízek_v_alobalu", hra.zpracujPrikaz("jdi foyer"));

        // 3. krok jdi jídelna
        assertEquals("Popis místnosti 'jídelna': jediným kusem nábytku je zde starý mahagonový stůl.\n" +
                "sousední místnosti: kuchyň foyer\n" +
                "věci v místnosti: keramický_střep stůl\n" +
                "Obsah košíku: řízek_v_alobalu", hra.zpracujPrikaz("jdi jídelna"));

        // 4. krok jdi kuchyň
        assertEquals("Popis místnosti 'kuchyň': zatuchlá místnost prolezlá plísní.\n" +
                "sousední místnosti: sklep jídelna\n" +
                "věci v místnosti: hrnec naběračka páčidlo\n" +
                "Obsah košíku: řízek_v_alobalu", hra.zpracujPrikaz("jdi kuchyň"));

        // 5. krok seber páčidlo
        assertEquals("Sebral jsi páčidlo", hra.zpracujPrikaz("seber páčidlo"));

        // 6. krok jdi jídelna
        assertEquals("Popis místnosti 'jídelna': jediným kusem nábytku je zde starý mahagonový stůl.\n" +
                "sousední místnosti: kuchyň foyer\n" +
                "věci v místnosti: keramický_střep stůl\n" +
                "Obsah košíku: řízek_v_alobalu páčidlo", hra.zpracujPrikaz("jdi jídelna"));

        // 7. krok jdi foyer
        assertEquals("Popis místnosti 'foyer': vstupní místnost s prachem pokrytými soškami.\n" +
                "sousední místnosti: dveře_ven chodba jídelna studovna\n" +
                "věci v místnosti: zdobená_váza deštník lucerna\n" +
                "Obsah košíku: řízek_v_alobalu páčidlo", hra.zpracujPrikaz("jdi foyer"));

        // 8. krok jdi studovna
        assertEquals("Popis místnosti 'studovna': obývací pokoj se zaprášeným nábytkem a starou šachovnicí.\n" +
                "sousední místnosti: foyer knihovna\n" +
                "věci v místnosti:\n" +
                "Obsah košíku: řízek_v_alobalu páčidlo", hra.zpracujPrikaz("jdi studovna"));

        // 9. krok jdi knihovna
        assertEquals("Popis místnosti 'knihovna': menší místnost plná knih všech velikostí a barev.\n" +
                "sousední místnosti: studovna\n" +
                "věci v místnosti:\n" +
                "Obsah košíku: řízek_v_alobalu páčidlo", hra.zpracujPrikaz("jdi knihovna"));

        // 10. krok seber deník
        assertEquals("", hra.zpracujPrikaz("seber deník"));

        assertEquals("", hra.zpracujPrikaz("jdi studovna"));

        assertEquals("", hra.zpracujPrikaz("jdi foyer"));

        assertEquals("", hra.zpracujPrikaz("jdi chodba"));

        assertEquals("", hra.zpracujPrikaz("jdi sklep"));

        assertEquals("", hra.zpracujPrikaz("seber prkno_v_podlaze"));

        assertEquals("", hra.zpracujPrikaz("jdi chodba"));

        assertEquals("", hra.zpracujPrikaz("jdi foyer"));

        assertEquals("", hra.zpracujPrikaz("jdi dveře_ven"));

        assertTrue(hra.konecHry());
        //assertEquals("Gratuluji, vyhrál jsi hru!", hra.konecHry());

    }
}

