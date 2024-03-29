package logika;

public class PrikazPoloz implements IPrikaz {
    private static final String NAZEV = "polož";
    private final HerniPlan herniPlan;

    public PrikazPoloz(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    @Override
    public String provedPrikaz(String[] parametry) {
        if (parametry.length == 0) {
            return "Co chceš položit? Musíš zadat název předmětu...";
        }
        if (parametry.length > 1) {
            return "Chceš toho položit nějak moc. Můžeš najednou položit jen jednu věc.";
        }

        String nazevVeci = parametry[0];

        Vec pozadovanaVec = herniPlan.getKapsy().odeberZKapes(nazevVeci);

        if (pozadovanaVec == null) {
            return nazevVeci + " nemáš v kapsách.";
        }

        herniPlan.getAktualniProstor().vlozVec(pozadovanaVec);
        return nazevVeci + " jsi položil do místnosti/prostoru";

    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}