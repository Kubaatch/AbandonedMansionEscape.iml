package logika;

import java.util.List;
import java.util.Random;

public class PrikazTeleport implements IPrikaz {
    private static final String NAZEV = "teleport";
    private HerniPlan plan;

    public PrikazTeleport(HerniPlan plan) {
        this.plan = plan;
    }

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

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
