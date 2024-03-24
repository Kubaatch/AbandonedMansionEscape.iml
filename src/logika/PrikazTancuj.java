package logika;

public class PrikazTancuj implements IPrikaz {

    private static final String NAZEV = "tancuj";

    @Override
    public String provedPrikaz(String[] parametry) {
        return "Vesele sis zatancoval/a, máš teď mnohem lepší náladu :)";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
