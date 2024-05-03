package logika;

import java.util.ArrayList;
import java.util.List;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 *  <p>
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory, propojuje je vzájemně pomocí východů,
 *  vkládá do prostorů věci
 *  a pamatuje si výherní prostor a aktuální prostor, ve kterém se hráč právě nachází.
 *
 * @author    Jakub Hřebíček
 * @version   v2.0 2024/05/03
 */
public class HerniPlan {
    private final List<Prostor> seznamProstoru;
    private Prostor aktualniProstor;
    private Prostor vyherniProstor;
    private InsanityMeter insanityMeter;
    private Kapsy kapsy;
    private static final int KAPACITA_KAPES = 4;

     /**
     *  Konstruktor třídy, volá metodu zalozProstoryHry().
     */
    public HerniPlan() {
        seznamProstoru = new ArrayList<>();
        zalozProstoryHry();
    }

    /**
     *  Vytváří jednotlivé prostory, vkládá je do seznamu prostorů a propojuje je pomocí východů
     *  Jako výchozí aktuální prostor nastaví ložnici, jako výherní prostor nastaví dvereVen.
     *  Do prostorů vloží věci, vytvoří kapsy a inicializuje úroveň zbláznění.
     */
    private void zalozProstoryHry() {
        // vytváří se jednotlivé prostory
        Prostor dvereVen = new Prostor("východ","masivní dubové dveře se zrezlou klíčovou dírkou", true);
        Prostor foyer = new Prostor("foyer", "vstupní místnost s prachem pokrytými soškami");
        Prostor jidelna = new Prostor("jídelna","jediným kusem nábytku je zde starý mahagonový stůl");
        Prostor kuchyn = new Prostor("kuchyň","zatuchlá místnost prolezlá plísní");
        Prostor sklep = new Prostor("sklep","tmavé podzemní prostory, kde může číhat cokoliv");
        Prostor chodba = new Prostor("chodba", "dlouhá rovná chodba, která vypadá jako z hororu");
        Prostor loznice = new Prostor("ložnice", "starobylá místnost s prázdnými skříněmi a prasklým zrcadlem");
        Prostor studovna = new Prostor("studovna", "obývací pokoj se zaprášeným nábytkem a starou šachovnicí");
        Prostor knihovna = new Prostor("knihovna", "menší místnost plná knih všech velikostí a barev");
        Prostor pristenek = new Prostor("přístěnek", "malá kouzelná místnůstka, schovávající se pod schody do patra");
        Prostor prvniPatro = new Prostor("1.patro", "nábytkem zcela zablokovaná část sídla, kam se nedá dostat");

        // prostory se vkládají do seznamu prostorů
        vlozProstor(dvereVen);
        vlozProstor(foyer);
        vlozProstor(jidelna);
        vlozProstor(kuchyn);
        vlozProstor(sklep);
        vlozProstor(chodba);
        vlozProstor(loznice);
        vlozProstor(studovna);
        vlozProstor(knihovna);
        vlozProstor(pristenek);
        vlozProstor(prvniPatro);

        // vytváří se východy z prostorů (sousedící prostory)
        foyer.setVychody(List.of(new Prostor[]{dvereVen, jidelna, studovna, chodba, prvniPatro}));
        jidelna.setVychody(List.of(new Prostor[]{kuchyn, foyer}));
        kuchyn.setVychod(jidelna);
        sklep.setVychod(chodba);
        chodba.setVychody(List.of(new Prostor[]{foyer, loznice, sklep, pristenek}));
        loznice.setVychod(chodba);
        studovna.setVychody(List.of(new Prostor[]{foyer, knihovna}));
        knihovna.setVychod(studovna);
        pristenek.setVychod(chodba);
        prvniPatro.setVychod(foyer);

        // určení počátečního a výherního prostoru
        aktualniProstor = loznice;
        vyherniProstor = dvereVen;

        //vkládají se věci do místností
        foyer.vlozVec(new Vec("zdobená_váza", false));
        foyer.vlozVec(new Vec("deštník", 2));
        foyer.vlozVec(new Vec("lucerna", 1));
        jidelna.vlozVec(new Vec("keramický_střep", 1));
        jidelna.vlozVec(new Vec("stůl", false));
        kuchyn.vlozVec(new Vec("hrnec", 2));
        kuchyn.vlozVec(new Vec("naběračka", 1));
        kuchyn.vlozVec(new Vec("páčidlo", 1));
        kuchyn.vlozVec(new Vec("plesnivý_sýr", 1));
        kuchyn.vlozVec(new Vec("plísňový_sýr", 1, true));
        sklep.vlozVec(new Vec("zamčená_skříň", true));
        sklep.vlozVec(new Vec("zrezivělý_zámek", 1));
        sklep.vlozVec(new Vec("lopata", 2));
        sklep.vlozVec(new Vec("valcha", false));
        sklep.vlozVec(new Vec("prkno_v_podlaze", true));
        loznice.vlozVec(new Vec("královská_postel", false));
        loznice.vlozVec(new Vec("noční_stolek", true));
        loznice.vlozVec(new Vec("svíčka", 1));
        chodba.vlozVec(new Vec("obraz", false));
        chodba.vlozVec(new Vec("portrét", false));
        chodba.vlozVec(new Vec("květináč", 1));
        chodba.vlozVec(new Vec("kožené_boty", 2));
        studovna.vlozVec(new Vec("křeslo", false));
        studovna.vlozVec(new Vec("šachovnice", 2));
        studovna.vlozVec(new Vec("gauč", false));
        studovna.vlozVec(new Vec("truhla", true));
        knihovna.vlozVec(new Vec("modrá_kniha", true,  1));
        knihovna.vlozVec(new Vec("bible", true, 2));
        knihovna.vlozVec(new Vec("zelená_kniha", true, 1));
        knihovna.vlozVec(new Vec("kožená_kniha", true, 1));
        knihovna.vlozVec(new Vec("deník", true, 1));
        knihovna.vlozVec(new Vec("černá_kniha", true, 1));
        prvniPatro.vlozVec(new Vec("fakt_velká_hromada_nábytku", false));
        pristenek.vlozVec(new Vec("ořechy", 1, true));
        pristenek.vlozVec(new Vec("sklenice_medu", 2, true));
        pristenek.vlozVec(new Vec("kouzelnická_hůlka", 1));

        //schová prkno_v_podlaze, neobjevuje se pak ve výpisu věcí v místnosti
        sklep.vyberVec("prkno_v_podlaze").setSchovanost(true);

        //vytváří se kapsy
        kapsy = new Kapsy(KAPACITA_KAPES);
        Vec rizek = new Vec("řízek_v_alobalu", 1, true);
        kapsy.vlozDoKapes(rizek);

        // nastavení úrovně zbláznění
        insanityMeter = new InsanityMeter(0);
    }

    /**
     * metoda vkládá prostor do seznamu prostorů
     *
     * @param prostor nový prostor ke vložení do seznamu
     */
    private void vlozProstor(Prostor prostor) {
        seznamProstoru.add(prostor);
    }

    /**
     * metoda vrací odkaz na seznam všech prostorů ve hře
     *
     * @return  seznam všech prostorů
     */
    public List<Prostor> getSeznamProstoru() {
        return seznamProstoru;
    }

    /**
     * metoda vrací odkaz na jeden konkrétní prostor
     *
     * @param nazev název hledaného prostoru
     * @return odkaz na hledaný prostor, null pokud prostor neexistuje
     */
    public Prostor getProstor(String nazev) {
        for (Prostor prostor : seznamProstoru) {
            if (prostor.getNazev().equals(nazev)) {
                return prostor;
            }
        }

        return null;
    }

    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     * @return  aktuální prostor
     */
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }

    /**
     * Metoda vrací odkaz na výherní prostor.
     *
     * @return  výherní prostor
     */
    public Prostor getVyherniProstor() {
        return vyherniProstor;
    }

    /**
     * metoda vrací odkaz na kapsy
     *
     * @return kapsy
     */
    public Kapsy getKapsy() {
        return kapsy;
    }

    /**
     * metoda vrací odkaz na instanci třídy InsanityMeter
     *
     * @return insanityMeter
     */
    public InsanityMeter getInsanityMeter() {
        return insanityMeter;
    }
}
