package logika;

public class PrikazSnez implements IPrikaz {
    private static final String NAZEV = "sněz";
    private final HerniPlan plan;

    public PrikazSnez(HerniPlan herniPlan) {
        this.plan = herniPlan;
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }

    @Override
    public String provedPrikaz(String[] parametry) {
        if (parametry.length == 0) {
            return "Co chceš sníst? Musíš napsat co chceš sníst...";
        }
        if (parametry.length > 1) {
            return "Chceš toho sníst nějak moc. Můžeš najednou sníst jen jednu věc.";
        }

        String jidlo = parametry[0];

        if (!plan.getKapsy().obsahujeVec(jidlo)) {
            return jidlo + " nemáš u sebe v kapsách...";
        }

        if (!plan.getKapsy().vyberVec(jidlo).isJedla()) {
            return "Sníst " + jidlo + " by pro tebe nedopadlo úplně dobře.";
        }

        plan.getKapsy().odeberZKapes(jidlo);
        return "Snědl jsi " + jidlo + " a moc ti chutnalo.";
    }

}
