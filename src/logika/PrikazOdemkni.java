package logika;

public class PrikazOdemkni implements IPrikaz {

    private static final String NAZEV = "odemkni";
    private final HerniPlan plan;

    public PrikazOdemkni(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String[] parametry) {
        if (!plan.getKapsy().obsahujeVec("rezavý_klíč")) {
            return "Chybí ti klíč, kterým bys mohl dveře odemknout";
        }

        if (parametry.length == 0) {
            return "Co mám odemknout? Musíš zadat název předmětu...";
        }
        if (parametry.length > 1) {
            return "Chceš toho odemknout nějak moc. Můžeš najednou odemknout jen jednu věc.";
        }

        String nazevProstoru = parametry[0];
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(nazevProstoru);

        if (sousedniProstor == null) {
            return "Tento prostor nesousedí s tvým aktuálním prostorem.";
        }

        if (!sousedniProstor.isZamceny()) {
            return "Prostor je odemčený, nemusíš ho odemykat.";
        }

        sousedniProstor.setZamceny(false);
        return "Odemknul jsi " + nazevProstoru;
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }


}
