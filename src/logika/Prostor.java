package logika;

import java.util.*;

/**
 * Třída Prostor popisuje jednotlivé prostory (místnosti) hry.
 * Tato třída je součástí jednoduché textové hry.
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 * Prostor může být zamčený, pokud se tak nastaví v boolean zamceny.
 *
 * @author    Jakub Hřebíček
 * @version   v2.0 2024/05/03
 */
public class Prostor {

    private final String nazev;
    private final String popis;
    private boolean zamceny;
    private final Set<Prostor> vychody;   // obsahuje sousední místnosti
    private List<Vec> seznamVeci;

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "sklep", "foyer"
     *
     * @param nazev název prostoru, jednoznačný identifikátor, jedno slovo nebo víceslovný název bez mezer.
     * @param popis Popis prostoru.
     * @param zamceny pokud je hodnota true, nelze do něj vstoupit
     */
    public Prostor(String nazev, String popis, boolean zamceny) {
        this.nazev = nazev;
        this.popis = popis;
        this.zamceny = zamceny;
        vychody = new HashSet<>();
        seznamVeci = new ArrayList<>();
    }

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "sklep", "foyer"
     *
     * @param nazev název prostoru, jednoznačný identifikátor, jedno slovo nebo víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        zamceny = false;
        vychody = new HashSet<>();
        seznamVeci = new ArrayList<>();
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedí s aktuálním prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Definuje východy z prostoru, narozdíl od metody setVychod
     * přijímá list prostorů a ne pouze jeden prostor
     *
     * @param prostory prostory sousedící s aktuálním prostorem
     */
    public void setVychody(List<Prostor> prostory) {
        vychody.addAll(prostory);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     * <p>
     * Bližší popis metody equals je u třídy Object.
     *
     * @param obj object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
      @Override
    public boolean equals(Object obj) {
        // porovnáváme zda se nejedná obj dva odkazy na stejnou instanci
        if (this == obj) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(obj instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor
        Prostor druhy = (Prostor) obj;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů.
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně:
     * Popis místnosti 'chodba': dlouhá rovná chodba, která vypadá jako z hororu.
     * sousední místnosti: sklep foyer ložnice
     * věci v místnosti: květináč obraz portrét
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Popis místnosti '" + nazev + "': " + popis + ".\n"
                + popisVychodu() + '\n' + popisVeci();
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "sousední místnosti: chodba".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        StringBuilder vracenyText = new StringBuilder("sousední místnosti:");
        for (Prostor sousedni : vychody) {
            vracenyText.append(' ').append(sousedni.getNazev());
            if (sousedni.isZamceny()) {
                vracenyText.append("(zamčeno)\uD83D\uDD12");
            }
        }
        return vracenyText.toString();
    }

    /**
     * Vrací textový řetězec, který popisuje věci v místnosti, například:
     * "věci v místnosti: květináč obraz portrét".
     *
     * @return Seznam věcí v místnosti
     */
    private String popisVeci() {
        StringBuilder vracenyText = new StringBuilder("věci v místnosti:");
        for (Vec vec : seznamVeci) {
            if (vec.isSchovana()) {
                continue;
            }
            vracenyText.append(" ").append(vec.getNazev());
        }

        return vracenyText.toString();
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                   .toList();
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.getFirst();
        }
    }

    /**
     * Vloží věc do prostoru, tedy do Listu(Vec) jménem seznamVeci
     *
     * @param vec věc vkládaná do prostoru
     */
    public void vlozVec(Vec vec) {
        seznamVeci.add(vec);
    }

    /**
     * Kontroluje, zda prostor obsahuje věc a vrátí příslušnou boolean hodnotu
     *
     * @param nazevVeci věc, o které zjišťujeme, zda je v prostoru
     * @return true pokud se věc nachází v prostoru, jinak false
     */
    public boolean obsahujeVec(String nazevVeci) {
        for (Vec vec : seznamVeci) {
            if (vec.getNazev().equals(nazevVeci)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Odebere věc z prostoru.
     *
     * @param vybranaVec věc k odebrání z prostoru
     */
    public void odeberVec(Vec vybranaVec) {
        seznamVeci.remove(vybranaVec);
    }

    /**
     * metoda slouží pro vybrání věci z mistnosti
     * kontroluje jestli je věc v místnosti a vrátí ji pokud ano
     *
     * @param nazevVeci věc k vybrání
     * @return null pokud věc není v mistnosti, jinak vrátí věc
     */
    public Vec vyberVec(String nazevVeci) {
        Vec vybranaVec = null;

        for (Vec vec : seznamVeci) {
            if(vec.getNazev().equals(nazevVeci)) {
                vybranaVec = vec;
            }
        }

        return vybranaVec;
    }

    /**
     * Metoda vrací boolean hodnotu, zda je prostor zamčený nebo ne.
     *
     * @return  true pokud je prostor zamčený, jinak false
     */
    public boolean isZamceny() {
        return zamceny;
    }

    /**
     * Metoda nastaví zamčenost prostoru
     *
     * @param zamceny true/false hodnota, zda je prostor zamčený nebo odemčený
     */
    public void setZamceny(boolean zamceny) {
        this.zamceny = zamceny;
    }
}
