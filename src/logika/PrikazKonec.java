package logika;

/**
 * Třída PrikazKonec implementuje pro hru příkaz konec.
 * Tato třída je součástí jednoduché textové hry.
 * Zavolání tohoto příkazu okamžitě ukončí hru a vypíše epilog.
 *
 * @author   Jakub Hřebíček
 * @version  v1.8 2024/04/04
 */
public class PrikazKonec implements IPrikaz {
    private static final String NAZEV = "konec";
    private Hra hra;

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
            return "Napsal jsi toho nějak moc. Stačí napsat jen 'konec'";
        }
        else {
            hra.setKonecHry();
            return "hra ukončena příkazem konec";
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
