package logika;

import uitext.Strings;

/**
 * Třída PrikazOdemkni implementuje pro hru příkaz odemkni.
 * Tato třída je součástí jednoduché textové hry.
 * Zavoláním tohoto příkazu se hra pokusí odemknout místnost, jež hráč specifikoval v parametrech.
 *
 * @author   Jakub Hřebíček
 * @version   v2.0 2024/05/03
 */
public class PrikazOdemkni implements IPrikaz {
    private static final String NAZEV = "odemkni";
    private final HerniPlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan instance třídy HerniPlan, obsahující místnosti, věci a všechny jejich vlastnosti
     */
    public PrikazOdemkni(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Provádí příkaz odemkni.
     * Zkouší odemknout zamčený prostor
     * kontroluje zda hráč má klíč a zda místnost je sousední a je zamčená.
     *
     * @param parametry prostor, který chce hráč odemknout
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String[] parametry) {
        if (parametry.length == 0) {
            return "Co mám odemknout? Musíš zadat název předmětu/místnosti...";
        }
        if (parametry.length > 1) {
            return Strings.CHYBA_MOC_TEXTU;
        }

        if (!plan.getKapsy().obsahujeVec("rezavý_klíč")) {
            return "Chybí ti klíč, kterým bys mohl dveře odemknout";
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
