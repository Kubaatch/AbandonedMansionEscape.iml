package logika;

public class PrikazSpanek implements IPrikaz {
    private static final String NAZEV = "spinkej";
    @Override
    public String provedPrikaz(String[] parametry) {
        return "Jsi student vysoké školy, nemůžeš se vyspat. Zkouškové se blíží...\uD83D\uDC7B";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
