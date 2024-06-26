package logika;

/**
 * Třída InsanityMeter - přidává měření úrovně zbláznení hráče
 * <p>
 * Tato třída obsahuje hodnotu urovenZblazneni, která sleduje míru šílenství hráče.
 * Třída byla přidána pro ozvláštnění hry. Hráč musí najít způsob,
 * jak si zlepšit náladu aby mohl pokračovat ve hře a dohrát ji.
 * Třída obsahuje pouze konstruktor, getter, setter a metodu navyšující úroveň zbláznění o 1.
 *
 * @author    Jakub Hřebíček
 * @version   v2.0 2024/05/03
 */
public class InsanityMeter {
    private int urovenZblazneni;

    /**
     * Konstruktor třídy
     *
     * @param urovenZblazneni číselná hodnota určující míru zbláznění hráče
     */
    public InsanityMeter(int urovenZblazneni) {
        this.urovenZblazneni = urovenZblazneni;
    }

    /**
     * Metoda navyšuje úroveň zbláznění o 1,
     * využita je pouze po úspěšném provedení příkazu jdi.
     */
    public void increaseUrovenZblazneni() {
        urovenZblazneni++;
    }

    /**
     * Metoda vrací aktuální hodnotu úrovně zbláznění.
     *
     * @return urovenzblazneni - aktuální hodnota
     */
    public int getUrovenZblazneni() {
        return urovenZblazneni;
    }

    /**
     * Metoda nastavuje úroveň zbláznění na vstupní hodnotu.
     *
     * @param urovenZblazneni vstupní hodnota, na níž se nastaví úroveň zbláznění
     */
    public void setUrovenZblazneni(int urovenZblazneni) {
        this.urovenZblazneni = urovenZblazneni;
    }
}
