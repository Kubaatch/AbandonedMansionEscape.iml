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

        String nazevVeci = parametry[0];
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(nazevVeci);

        int predmetNeboProstor = -1; //0 pokud odemykám předmět, 1 pokud prostor, -1 značí chybu
        if (plan.getAktualniProstor().obsahujeVec(nazevVeci)) {
            predmetNeboProstor = 0; //odemykám předmět
        } else if (sousedniProstor != null) {
            predmetNeboProstor = 1; //odemykám prostor
        }

        switch (predmetNeboProstor) {
            case 0:
                return odemykaniVeci(nazevVeci);
            case 1:
                return odemykaniProstoru(sousedniProstor);
            case -1:
                return nazevVeci + " není ve tvém okolí, nelze tedy odemknout.";
            default:
                return "";
        }
    }

    private String odemykaniVeci(String nazevVeci) {
        Vec vec = plan.getAktualniProstor().vyberVec(nazevVeci);

        if (vec.isZamcena() == null) {
            return "Tuto věc nelze odemykat...";
        }

        if (!vec.isZamcena()) {
            return "Tato věc je odemčená, nemusíš ji odemykat";
        }

        //funguje pouze pro tuto jednu truhlu
        if (!plan.getKapsy().obsahujeVec("klíč_od_truhly")) {
            return "Chybí ti klíč, kterým bys mohl truhlu odemknout";
        }

        for (Vec vecUvnitr : vec.getSeznamVeci())
        {
            plan.getAktualniProstor().vlozVec(vecUvnitr);
        }

        vec.setZamcena(false);
        return "Odemknul jsi " + nazevVeci;
    }

    private String odemykaniProstoru(Prostor sousedniProstor) {
        if (!sousedniProstor.isZamceny()) {
            return "Prostor je odemčený, nemusíš ho odemykat.";
        }

        //funguje pouze pro jedny odemykatelné dveře
        if (!plan.getKapsy().obsahujeVec("rezavý_klíč")) {
            return "Chybí ti klíč, kterým bys mohl prostor odemknout";
        }

        sousedniProstor.setZamceny(false);
        return "Odemknul jsi " + sousedniProstor.getNazev();
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
