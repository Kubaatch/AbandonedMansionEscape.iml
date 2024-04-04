package logika;

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
    private HerniPlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan herní plán
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
            return "Napsal jsi toho nějak moc...";
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
