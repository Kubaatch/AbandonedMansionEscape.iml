package logika;

import uitext.Strings;

/**
 * Třída PrikazOdemkni implementuje pro hru příkaz odemkni.
 * Tato třída je součástí jednoduché textové hry.
 * Zavoláním tohoto příkazu se hra pokusí odemknout místnost, jež hráč specifikoval v parametrech.
 *
 * @author   Jakub Hřebíček
 * @version   v2.0.3 2024/05/24
 */
public class PrikazSeber implements IPrikaz {
    private static final String NAZEV = "seber";
    private final HerniPlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan instance třídy HerniPlan, obsahující místnosti, věci a všechny jejich vlastnosti
     */
    public PrikazSeber(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Provádí příkaz seber.
     * Odebere předmět z prostoru a vloží ho do kapes hráče.
     * Pokud nastane chyba, vypíše příslušnou chybovou hlášku.
     *
     * @param parametry předmět, který chce hráč sebrat
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String[] parametry) {
        if (parametry.length == 0) {
            return "Co chceš sebrat? Musíš zadat název předmětu...";
        }
        if (parametry.length > 1) {
            return Strings.CHYBA_MOC_TEXTU;
        }

        String nazevVeci = parametry[0];

        Vec pozadovanaVec = plan.getAktualniProstor().vyberVec(nazevVeci);
        if (pozadovanaVec == null) {
            return nazevVeci + " se nenachází v tomto prostoru.";
        }

        if (!pozadovanaVec.isPrenositelna()) {
            return nazevVeci + " se nedá sebrat.";
        }

        //speciální podmínka pro speciální předmět, ten lze přidat i při plné kapacitě kapes
        if (pozadovanaVec.isSpecialni() && plan.getKapsy().isMaxKapacita()) {
            pozadovanaVec.setVelikost(0);
        }

        boolean povedloSeUlozit = plan.getKapsy().vlozDoKapes(pozadovanaVec);

        if (!povedloSeUlozit) {
            return "Snažíš se nacpat předmět " + pozadovanaVec.getNazev() + " do plných kapes.";
        }

        plan.getAktualniProstor().odeberVec(pozadovanaVec);
        return "Sebral jsi " + pozadovanaVec.getNazev();
    }

    /**
     * Metoda vrátí název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @return název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
