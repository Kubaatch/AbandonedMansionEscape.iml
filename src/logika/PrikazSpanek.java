package logika;

import uitext.Strings;

/**
 * Třída PrikazSpanek implementuje pro hru příkaz spinkej.
 * Tato třída je součástí jednoduché textové hry.
 * Zavoláním tohoto příkazu se hráč pokusí vyspat, s cílem zlepšení nálady (snížení hodnoty insanityMeter o 2).
 *
 * @author   Jakub Hřebíček
 * @version  v1.8 2024/04/05
 */
public class PrikazSpanek implements IPrikaz {
    private static final String NAZEV = "spinkej";
    private final HerniPlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan instance třídy HerniPlan, obsahující místnosti, věci a všechny jejich vlastnosti
     */
    public PrikazSpanek(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Provádí příkaz spinkej.
     * Zjistí jestli je hráč v ložnici a nechá ho se vyspat.
     * Pokud nastane chyba, vypíše příslušnou chybovou hlášku.
     *
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String[] parametry) {
        if (parametry.length > 0) {
            return Strings.CHYBA_MOC_TEXTU;
        }

        if (!plan.getAktualniProstor().getNazev().equals("ložnice")) {
            return "Tady se nemůžeš vyspat. Dost možná tu straší...\uD83D\uDC7B";
        }

        int aktualni = plan.getInsanityMeter().getUrovenZblazneni();

        plan.getInsanityMeter().setUrovenZblazneni(aktualni-2);

        if (plan.getInsanityMeter().getUrovenZblazneni() < 0) {plan.getInsanityMeter().setUrovenZblazneni(0);}

        return "Na chvilku sis schrupnul, zlepšilo ti to náladu...";
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
