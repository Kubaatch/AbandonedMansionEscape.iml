package logika;

import uitext.Strings;

/**
 *  Třída Hra - třída představující logiku adventury.
 * <p>
 *  Toto je hlavní třída logiky aplikace. Tato třída vytváří instanci třídy HerniPlan, která inicializuje prostory hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 * @author    Jakub Hřebíček
 * @version   v1.8 2024/04/04
 */

public class Hra implements IHra {
    private final SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private final HerniPlan herniPlan;
    private boolean konecHry = false;
    private String epilog;

    /**
     *  Vytváří herní plán a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazInfo(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazJdi(this, herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazPoloz(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazOdemkni(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPrecti(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazSnez(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazSpanek(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazTancuj(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazTeleport(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazVypac(herniPlan));
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return Strings.UVOD +
                herniPlan.getAktualniProstor().dlouhyPopis() + '\n' +
                herniPlan.getKapsy().dlouhyPopis();
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return epilog;
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
           	parametry[i] = slova[i+1];
        }
        String textKVypsani;
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
        }
        else {
            textKVypsani = "Nevím co tím myslíš? Tento příkaz neznám... ";
        }
        return textKVypsani;
    }
    
    
    /**
     * Nastaví, že je konec hry (hodnota konecHry = true).
     * Metodu využívá třída PříkazKonec (vynucené ukončení) a PříkazJdi (výhra/prohra ve hře)
     */
    void setKonecHry() {
        this.konecHry = true;
    }
    
    /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *
     *  @return     odkaz na herní plán
     */
    public HerniPlan getHerniPlan(){
        return herniPlan;
     }

    /**
     * Metoda slouží pro změnu epilogu, což je využito při různých koncích hry.
     *
     * @param epilog vstupní String který je nastaven jako nový epilog
     */
    public void setEpilog(String epilog) {
        this.epilog = epilog;
    }
}

