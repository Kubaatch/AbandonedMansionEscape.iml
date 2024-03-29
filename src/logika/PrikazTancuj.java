package logika;

public class PrikazTancuj implements IPrikaz {

    private static final String NAZEV = "tancuj";

    private HerniPlan plan;

    public PrikazTancuj(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String[] parametry) {
        plan.getSanityMeter().setUrovenZblazneni(0);
        return "Vesele sis zatancoval/a, máš teď mnohem lepší náladu :)";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
