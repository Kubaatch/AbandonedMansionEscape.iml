package logika;

import uitext.Strings;

/**
 * Třída PrikazNapoved implementuje pro hru příkaz nápověd.
 * Tato třída je součástí jednoduché textové hry.
 * Zavolání tohoto příkazu vypíše cíl hry a seznam použitelných příkazů.
 *
 * @author   Jakub Hřebíček
 * @version  v1.8 2024/04/04
 */
public class PrikazNapoveda implements IPrikaz {
    private static final String NAZEV = "nápověda";
    private final SeznamPrikazu platnePrikazy;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param platnePrikazy seznam příkazů, které je možné ve hře použít
    */    
    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }
    
    /**
     *  Vrací základní nápovědu po zadání příkazu "nápověda".
     *  Vypíše způsob, jak lze vyhrát hru a seznam dostupných příkazů.
     *  
     *  @return nápověda ke hře
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length > 0) {
            return Strings.CHYBA_MOC_TEXTU;
        }

        return Strings.NAPOVEDA + platnePrikazy.vratNazvyPrikazu();
    }
    
     /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return název příkazu
     */
    @Override
      public String getNazev() {
        return NAZEV;
     }

}
