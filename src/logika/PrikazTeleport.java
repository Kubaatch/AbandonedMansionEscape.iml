package logika;

import java.util.Random;

/**
 * Třída PrikazTeleport implementuje pro hru příkaz teleport.
 * Tato třída je součástí jednoduché textové hry.
 * Zavoláním tohoto příkazu se hráč přesune do náhodné místnosti na mapě.
 *
 * @author   Jakub Hřebíček
 * @version  v1.8 2024/04/05
 */
public class PrikazTeleport implements IPrikaz {
    private static final String NAZEV = "teleport";
    private HerniPlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan instance třídy HerniPlan, obsahující místnosti, věci a všechny jejich vlastnosti
     */
    public PrikazTeleport(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Provádí příkaz teleport.
     * Zjistí, jestli je hráč v přístěnku a přesune ho do náhodné místnosti ve hře.
     * Pokud nastane chyba, vypíše příslušnou chybovou hlášku.
     *
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String[] parametry) {
        if (parametry.length > 0) {
            return "Napsal jsi toho nějak moc...";
        }

        if (!plan.getAktualniProstor().getNazev().equals("přístěnek")) {
            return "Teleport je kouzelný příkaz, který lze použít jen v jedné jediné místnosti.";
        }

        Random random = new Random();
        int rand = random.nextInt(plan.getSeznamProstoru().size());

        Prostor prostor = plan.getSeznamProstoru().get(rand);
        plan.setAktualniProstor(prostor);

        return "S výpadkem paměti ses probudil v místnosti " + prostor.getNazev();
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
