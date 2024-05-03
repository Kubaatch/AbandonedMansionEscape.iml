package logika;

import uitext.Strings;

/**
 * Třída PrikazInfo implementuje pro hru příkaz info.
 * Tato třída je součástí jednoduché textové hry.
 * Zavolání tohoto příkazu vypíše informace o dané místnosti.
 * Slouží pro zjištění aktuálního stavu, jelikož hra tyto informace vypisuje jen u třídy PříkazJdi.
 *
 * @author   Jakub Hřebíček
 * @version  v1.8 2024/04/04
 */
public class PrikazInfo implements IPrikaz {
    private static final String NAZEV = "info";
    private final HerniPlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan instance třídy HerniPlan, obsahující místnosti, věci a všechny jejich vlastnosti
     */
    public PrikazInfo(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * příkaz vypíše info o aktuální místnosti, stejná textace jako při přechodu do jiné místnosti
     *
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return dlouhý popis místnosti
     */
    @Override
    public String provedPrikaz(String[] parametry) {
        if (parametry.length > 0) {
            return Strings.CHYBA_MOC_TEXTU;
        }

        return plan.getAktualniProstor().dlouhyPopis() + '\n' + plan.getKapsy().dlouhyPopis();
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
