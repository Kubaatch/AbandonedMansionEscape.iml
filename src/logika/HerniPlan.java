package logika;

import java.util.Set;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory, propojuje je vzájemně pomocí východů,
 *  vkládá do prostorů věci
 *  a pamatuje si výherní prostor a aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Jakub Hřebíček
 *@version    1.1 2024/03/23
 */
public class HerniPlan {
    
    private Prostor aktualniProstor;
    private Prostor vyherniProstor;

    private Set<Prostor> prostory;
    private Kapsy kapsy;
    private final int KAPACITA_KAPES = 4;


     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domecek.
     */
    public HerniPlan() {
        zalozProstoryHry();

    }

    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví ložnici.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor dvereVen = new Prostor("dveře_ven","masivní dubové dveře se zrezlou klíčovou dírkou", true);
        Prostor foyer = new Prostor("foyer", "vstupní místnost s prachem pokrytými soškami");
        Prostor jidelna = new Prostor("jídelna","jediným kusem nábytku je zde starý mahagonový stůl");
        Prostor kuchyn = new Prostor("kuchyň","zatuchlá místnost prolezlá plísní");
        Prostor sklep = new Prostor("sklep","tmavé podzemní prostory, kde může číhat cokoliv");
        Prostor chodba = new Prostor("chodba", "dlouhá rovná chodba, která vypadá jako z hororu");
        Prostor loznice = new Prostor("ložnice", "starobylá místnost s prázdnými skříněmi a prasklým zrcadlem");
        Prostor studovna = new Prostor("studovna", "obývací pokoj se zaprášeným nábytkem a starou šachovnicí");
        Prostor knihovna = new Prostor("knihovna", "menší místnost plná knih všech velikostí a barev");
        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        foyer.setVychod(dvereVen);
        foyer.setVychod(jidelna);
        foyer.setVychod(studovna);
        foyer.setVychod(chodba);
        studovna.setVychod(foyer);
        studovna.setVychod(knihovna);
        knihovna.setVychod(studovna);
        jidelna.setVychod(kuchyn);
        jidelna.setVychod(foyer);
        kuchyn.setVychod(jidelna);
        kuchyn.setVychod(sklep);
        chodba.setVychod(foyer);
        chodba.setVychod(loznice);
        chodba.setVychod(sklep);
        loznice.setVychod(chodba);
        sklep.setVychod(chodba);
        sklep.setVychod(kuchyn);

        // určení počátečního a výherního prostoru
        aktualniProstor = loznice;
        vyherniProstor = dvereVen;

        //vložení věcí do místností
        foyer.vlozVec(new Vec("zdobená_váza", false, false));
        foyer.vlozVec(new Vec("deštník", true, 2));
        foyer.vlozVec(new Vec("lucerna", true, 1));
        jidelna.vlozVec(new Vec("keramický_střep", true, 1));
        jidelna.vlozVec(new Vec("stůl", false, false));
        kuchyn.vlozVec(new Vec("hrnec", true, 2));
        kuchyn.vlozVec(new Vec("naběračka", true, 1));
        kuchyn.vlozVec(new Vec("páčidlo", true, 1));
        kuchyn.vlozVec(new Vec("plesnivý_sýr", true, 1, false));
        kuchyn.vlozVec(new Vec("plísňový_sýr", true, 1, true));
        sklep.vlozVec(new Vec("zamčená_skříň", false, true));
        sklep.vlozVec(new Vec("zrezivělý_zámek", true, 1));
        sklep.vlozVec(new Vec("lopata", true, 2));
        sklep.vlozVec(new Vec("valcha", false, false));
        sklep.vlozVec(new Vec("prkno_v_podlaze", false, true, true));
        sklep.vlozVec(new Vec("rezavý_klíč", false, 1));
        loznice.vlozVec(new Vec("královská_postel", false, false));
        loznice.vlozVec(new Vec("noční_stolek", false, true));
        loznice.vlozVec(new Vec("svíčka", true, 1));
        chodba.vlozVec(new Vec("obraz", false, false));
        chodba.vlozVec(new Vec("portrét", false, false));
        chodba.vlozVec(new Vec("květináč", true, 1));
        chodba.vlozVec(new Vec("kožené_boty", true, 2));
        studovna.vlozVec(new Vec("křeslo", false, false));
        studovna.vlozVec(new Vec("šachovnice", true, 2));
        studovna.vlozVec(new Vec("gauč", false, false));
        studovna.vlozVec(new Vec("truhla", false, true));
        knihovna.vlozVec(new Vec("modrá_kniha", true, true,  1));
        knihovna.vlozVec(new Vec("bible", true, true, 2));
        knihovna.vlozVec(new Vec("zelená_kniha", true, true, 1));
        knihovna.vlozVec(new Vec("kožená_kniha", true, true, 1));
        knihovna.vlozVec(new Vec("deník", true, true, 1));
        knihovna.vlozVec(new Vec("černá_kniha", true, true, 1));

        //vytvoření kapes
        kapsy = new Kapsy(KAPACITA_KAPES);
        Vec rizek = new Vec("řízek_v_alobalu", true, 1, true);
        kapsy.vlozDoKapes(rizek);
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
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

    public Prostor getVyherniProstor() {
        return vyherniProstor;
    }

    public Kapsy getKapsy() {
        return kapsy;
    }
}
