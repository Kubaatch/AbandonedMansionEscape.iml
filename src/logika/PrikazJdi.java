package logika;

/**
 * Třída PrikazJdi implementuje pro hru příkaz jdí.
 * Tato třída je součástí jednoduché textové hry.
 * Zavolání tohoto příkazu přesune hráče do sousední místnosti, pokud je příkaz úspěšný.
 * Pokud ne, vrátí příslušnou chybovou hlášku.
 *
 * @author   Jakub Hřebíček
 * @version  v1.8 2024/04/04
 */
public class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private Hra hra;

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
     * @param parametry - přijímá parametr jméno prostoru (východu), do kterého se má jít.
     * @return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        // pokud chybí druhé slovo (sousední prostor), tak ....
        if (parametry.length == 0) {
            return "Kam chceš jít? Musíš zadat jméno východu";
        }
        if (parametry.length > 1) {
            return "Napsal jsi toho nějak moc...";
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
            hra.setEpilog("Ticho a samota v sídle tě přivedly k šílenství, prohrál jsi tuto hru.");
            hra.setKonecHry();
            return "";
        }

        String upozorneni = "";
        if (plan.getInsanityMeter().getUrovenZblazneni() == 4) {
            upozorneni = "Fuj! Leknul ses netopýra, který kolem tebe proletěl. Možná by ses měl něčím uklidnit.\n";
        }

        plan.setAktualniProstor(sousedniProstor);
        if (plan.getAktualniProstor().equals(plan.getVyherniProstor())) {
            hra.setEpilog("Gratuluji, vyhrál jsi hru!");
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
