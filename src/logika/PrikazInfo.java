package logika;

public class PrikazInfo implements IPrikaz {
    private static final String NAZEV = "info";
    private HerniPlan plan;

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

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
