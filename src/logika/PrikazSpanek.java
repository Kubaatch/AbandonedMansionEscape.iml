package logika;

import java.util.Random;

public class PrikazSpanek implements IPrikaz {
    private static final String NAZEV = "spinkej";
    private HerniPlan plan;

    public PrikazSpanek(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String[] parametry) {
        if (!plan.getAktualniProstor().getNazev().equals("ložnice")) {
            return "Tady se nemůžeš vyspat. Dost možná tu straší...\uD83D\uDC7B";
        }

        int aktualni = plan.getSanityMeter().getUrovenZblazneni();

        plan.getSanityMeter().setUrovenZblazneni(aktualni-2);

        if (plan.getSanityMeter().getUrovenZblazneni() < 0) {plan.getSanityMeter().setUrovenZblazneni(0);}

        return "Na chvilku sis schrupnul, zlepšilo ti to náladu...\uD83D\uDC7B";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
