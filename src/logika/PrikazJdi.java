package logika;

import uitext.Strings;

/**
 * Třída PrikazJdi implementuje pro hru příkaz jdí.
 * Tato třída je součástí jednoduché textové hry.
 * Zavolání tohoto příkazu přesune hráče do sousední místnosti, pokud je příkaz úspěšný.
 * Pokud ne, vrátí příslušnou chybovou hlášku.
 *
 * @author   Jakub Hřebíček
 * @version   v2.0 2024/05/03
 */
public class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private final HerniPlan plan;
    private final Hra hra;

    /**
    *  Konstruktor třídy
    *  
    *  @param hra instance třídy Hra, ve které se bude ve hře "chodit"
     * @param plan instance třídy HerniPlan, obsahující prostory a východy ve hře
    */    
    public PrikazJdi(Hra hra, HerniPlan plan) {
        this.hra = hra;
        this.plan = plan;
    }

    /**
     * Provádí příkaz "jdi". Zkouší se jít do zadaného prostoru. Pokud prostor
     * existuje, vstoupí se do nového prostoru. Pokud zadaná sousední místnost
     * (východ) není, vypíše se chybové hlášení.
     *
     * @param parametry prostor, kam chce hráč jít
     * @return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        // pokud chybí druhé slovo (sousední prostor), tak ....
        if (parametry.length == 0) {
            return "Kam chceš jít? Musíš zadat jméno východu";
        }
        if (parametry.length > 1) {
            return Strings.CHYBA_MOC_TEXTU;
        }

        String smer = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);

        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!";
        }
        if (sousedniProstor.isZamceny())
        {
            return "Prostor je zamčený, musíš ho nejdříve odemknout.";
        }


        if (plan.getInsanityMeter().getUrovenZblazneni() >= 5) {
            hra.setEpilog(Strings.EPILOG_FAIL);
            hra.setKonecHry();
            return "";
        }

        String upozorneni = "";
        if (plan.getInsanityMeter().getUrovenZblazneni() == 4) {
            upozorneni = Strings.UPOZORNENI_ZBLAZNENI;
        }

        plan.setAktualniProstor(sousedniProstor);
        if (plan.getAktualniProstor().equals(plan.getVyherniProstor())) {
            hra.setEpilog(Strings.EPILOG_WIN);
            hra.setKonecHry();
            return "";
        }

        plan.getInsanityMeter().increaseUrovenZblazneni();
        return upozorneni + sousedniProstor.dlouhyPopis() + '\n' + plan.getKapsy().dlouhyPopis();
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
