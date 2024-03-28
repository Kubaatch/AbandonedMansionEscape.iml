package logika;

public class PrikazSeber implements IPrikaz {

    private static final String NAZEV = "seber";
    private final HerniPlan herniPlan;

    public PrikazSeber(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    @Override
    public String provedPrikaz(String[] parametry) {
        if (parametry.length == 0) {
            return "Co chceš sebrat? Musíš zadat název předmětu...";
        }
        if (parametry.length > 1) {
            return "Chceš toho sebrat nějak moc. Můžeš najednou sebrat jen jednu věc.";
        }

        String nazevVeci = parametry[0];
        if (!herniPlan.getAktualniProstor().obsahujeVec(nazevVeci)) {
            return nazevVeci + " se nenachází v tomto prostoru.";
        }

        Vec pozadovanaVec = herniPlan.getAktualniProstor().vyberVec(nazevVeci);
        if (pozadovanaVec == null) {
            return nazevVeci + " se nedá sebrat.";
        }

        boolean povedloSeUlozit = herniPlan.getKapsy().vlozDoKapes(pozadovanaVec);
        if (!povedloSeUlozit) {
            herniPlan.getAktualniProstor().vlozVec(pozadovanaVec);
            return "Snažíš se nacpat předmět " + pozadovanaVec.getNazev() + " do plných kapes.";
        }

        return "Sebral jsi " + pozadovanaVec.getNazev();
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }


}
