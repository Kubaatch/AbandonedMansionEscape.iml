package logika;

import uitext.Strings;

/**
 * Třída PrikazOdemkni implementuje pro hru příkaz odemkni.
 * Tato třída je součástí jednoduché textové hry.
 * Zavoláním tohoto příkazu se hra pokusí odemknout místnost, jež hráč specifikoval v parametrech.
 *
 * @author   Jakub Hřebíček
 * @version   v2.0.3 2024/05/24
 */
public class PrikazAbrakadabra implements IPrikaz {
    private static final String nazev = "abrakadabra";
    private HerniPlan plan;

    public PrikazAbrakadabra(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String[] parametry) {
        if (parametry.length > 0) {
            return Strings.CHYBA_MOC_TEXTU;
        }

        Prostor komnata = plan.getProstor("tajemná_komnata");
        Prostor aktualni = plan.getAktualniProstor();
        Prostor predchozi = plan.getPredchoziProstor();

        if (!aktualni.equals(komnata)) {
            plan.setPredchoziProstor(aktualni);
            plan.setAktualniProstor(komnata);
        } else {
            plan.setAktualniProstor(predchozi);
        }

        return plan.getAktualniProstor().dlouhyPopis() + '\n' + plan.getKapsy().dlouhyPopis();
    }

    @Override
    public String getNazev() {
        return nazev;
    }
}
