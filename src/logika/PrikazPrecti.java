package logika;

import uitext.Strings;

/**
 * Třída PrikazPrecti implementuje pro hru příkaz přečti.
 * Tato třída je součástí jednoduché textové hry.
 * Zavolání tohoto příkazu vypíše text
 *
 * @author   Jakub Hřebíček
 * @version  v1.8 2024/04/04
 */
public class PrikazPrecti implements IPrikaz {

    private static final String NAZEV = "přečti";
    private final HerniPlan plan;
    private static final String UVOD_CTENI = "Uvnitř stojí: \n";

    /**
     * Metoda vrátí název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @return název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

    /**
     * Konstruktor třídy
     *
     * @param plan instance třídy HerniPlan, obsahující místnosti, věci a všechny jejich vlastnosti
     */
    public PrikazPrecti(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Provádí příkaz odemkni.
     * Zkouší odemknout zamčený prostor
     * kontroluje zda hráč má klíč a zda místnost je sousední a je zamčená.
     *
     * @param parametry předmět, který chce hráč přečíst
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String[] parametry) {
        if (parametry.length == 0) {
            return "Co mám přečíst? Musíš zadat název předmětu...";
        }
        if (parametry.length > 1) {
            return Strings.CHYBA_MOC_TEXTU;
        }

        String nazevVeci = parametry[0];

        if (!plan.getKapsy().obsahujeVec(nazevVeci)) {
            return nazevVeci + " nemáš u sebe v kapsách...";
        }

        if (!plan.getKapsy().getVec(nazevVeci).isCitelna()) {
            return nazevVeci + " nejde přečíst.";
        }

        String vracenyText = UVOD_CTENI;

        switch (nazevVeci) {
            case "bible":
                vracenyText += Strings.TEXT_BIBLE;
                break;
            case "deník":
                plan.getProstor("sklep").vyberVec("prkno_v_podlaze").setSchovanost(false);
                vracenyText += Strings.TEXT_DENIKU;
                break;
            case "modrá_kniha":
                vracenyText = Strings.TEXT_MODRA_KNIHA;
                break;
            case "zelená_kniha":
                vracenyText += Strings.TEXT_ZELENA_KNIHA;
                break;
            case "kožená_kniha":
                vracenyText += Strings.TEXT_KOZENA_KNIHA;
                break;
            case "černá_kniha":
                vracenyText += Strings.TEXT_CERNA_KNIHA;
                break;
            default:
                vracenyText = "Někde se stala chyba.";
        }

        return vracenyText;
    }
}
