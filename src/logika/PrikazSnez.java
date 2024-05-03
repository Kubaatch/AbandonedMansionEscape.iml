package logika;

import uitext.Strings;

/**
 * Třída PrikazSnez implementuje pro hru příkaz sněz.
 * Tato třída je součástí jednoduché textové hry.
 * Zavoláním tohoto příkazu se hráč pokusí sníst předmět, jež specifikoval v parametrech.
 *
 * @author   Jakub Hřebíček
 * @version  v1.8 2024/04/05
 */
public class PrikazSnez implements IPrikaz {
    private static final String NAZEV = "sněz";
    private final HerniPlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan instance třídy HerniPlan, obsahující místnosti, věci a všechny jejich vlastnosti
     */
    public PrikazSnez(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Provádí příkaz sněz.
     * Odebere předmět z kapes hráče a sní ho.
     * Pokud nastane chyba, vypíše příslušnou chybovou hlášku.
     *
     * @param parametry předmět, který chce hráč sníst
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String[] parametry) {
        if (parametry.length == 0) {
            return "Co chceš sníst? Musíš napsat co chceš sníst...";
        }
        if (parametry.length > 1) {
            return Strings.CHYBA_MOC_TEXTU;
        }

        String jidlo = parametry[0];

        if (!plan.getKapsy().obsahujeVec(jidlo)) {
            return jidlo + " nemáš u sebe v kapsách...";
        }

        if (!plan.getKapsy().getVec(jidlo).isJedla()) {
            return "Sníst " + jidlo + " by pro tebe nedopadlo úplně dobře.";
        }

        plan.getKapsy().odeberZKapes(jidlo);
        return "Snědl jsi " + jidlo + " a moc ti chutnalo.";
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
