package logika;

import uitext.Strings;

/**
 * Třída PrikazTancuj implementuje pro hru příkaz tancuj.
 * Tato třída je součástí jednoduché textové hry.
 * Zavoláním tohoto příkazu si hráč zatancuje a zlepší se mu nálada (reset hodnoty insanityMeter na 0).
 *
 * @author   Jakub Hřebíček
 * @version  v1.8 2024/04/05
 */
public class PrikazTancuj implements IPrikaz {
    private static final String NAZEV = "tancuj";
    private final HerniPlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan instance třídy HerniPlan, obsahující místnosti, věci a všechny jejich vlastnosti
     */
    public PrikazTancuj(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Provádí příkaz tancuj.
     * Nastaví hodnotu InsanityMeter na 0, tedy zlepší hráči náladu
     * Pokud nastane chyba, vypíše příslušnou chybovou hlášku.
     *
     * @param parametry předmět, který chce hráč sníst
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String[] parametry) {
        if (parametry.length > 0) {
            return Strings.CHYBA_MOC_TEXTU;
        }

        plan.getInsanityMeter().setUrovenZblazneni(0);
        return "Vesele sis zatancoval/a, máš teď mnohem lepší náladu :)";
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
