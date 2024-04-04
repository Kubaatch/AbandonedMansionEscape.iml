package logika;

/**
 * Třída PrikazPoloz implementuje pro hru příkaz polož.
 * Tato třída je součástí jednoduché textové hry.
 * Zavoláním tohoto příkazu hráč položí předmět, jež specifikuje v parametrech.
 * Pokud hráč nesprávně zadá příkaz, třída vrátí příslušnou chybovou hlášku.
 *
 * @author   Jakub Hřebíček
 * @version  v1.8 2024/04/04
 */
public class PrikazPoloz implements IPrikaz {
    private static final String NAZEV = "polož";
    private final HerniPlan herniPlan;

    /**
     * Konstruktor třídy
     *
     * @param plan instance třídy HerniPlan, obsahující místnosti, věci a všechny jejich vlastnosti
     */
    public PrikazPoloz(HerniPlan plan) {
        this.herniPlan = plan;
    }

    /**
     * Provádí příkaz polož.
     * Odebere předmět specifikovaný v parametrech a vloží ho do aktuálního prostoru.
     * Pokud nastane chyba, vypíše příslušnou chybovou hlášku.
     *
     * @param parametry předmět, jež chce hráč položit
     * @return zpráva, kterou vypíše hra hráči
     */
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
        return "Položil jsi " + nazevVeci + " do místnosti/prostoru " + herniPlan.getAktualniProstor().getNazev();

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
