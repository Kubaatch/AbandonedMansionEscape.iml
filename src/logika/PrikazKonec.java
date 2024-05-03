package logika;

import uitext.Strings;

/**
 * Třída PrikazKonec implementuje pro hru příkaz konec.
 * Tato třída je součástí jednoduché textové hry.
 * Zavolání tohoto příkazu okamžitě ukončí hru a vypíše epilog.
 *
 * @author   Jakub Hřebíček
 * @version   v2.0 2024/05/03
 */
public class PrikazKonec implements IPrikaz {
    private static final String NAZEV = "konec";
    private final Hra hra;

    /**
     *  Konstruktor třídy
     *  
     *  @param hra odkaz na hru, která má být příkazem konec ukončena
     */    
    public PrikazKonec(Hra hra) {
        this.hra = hra;
    }

    /**
     * V případě, že příkaz má jen jedno slovo "konec" hra končí(volá se metoda setKonecHry(true))
     * jinak pokračuje např. při zadání "konec a".
     * 
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length > 0) {
            return Strings.CHYBA_MOC_TEXTU;
        }
        else {
            hra.setKonecHry();
            hra.setEpilog(Strings.EPILOG_KONEC);
            return "";
        }
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     * @return název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
