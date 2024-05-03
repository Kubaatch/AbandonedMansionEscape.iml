package logika;

import uitext.Strings;

/**
 * Třída PrikazVypac implementuje pro hru příkaz vypač.
 * Tato třída je součástí jednoduché textové hry.
 * Zavoláním tohoto příkazu se hráč pokusí vypáčit předmět, jež specifikoval v parametrech.
 *
 * @author   Jakub Hřebíček
 * @version  v1.8 2024/04/05
 */
public class PrikazVypac implements IPrikaz {

    private static final String NAZEV = "vypač";

    private final HerniPlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan instance třídy HerniPlan, obsahující místnosti, věci a všechny jejich vlastnosti
     */
    public PrikazVypac(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Provádí příkaz vypač.
     * Zkontroluje, zda má hráč u sebe páčidlo a vypáčí příslušnou věc
     * Pokud nastane chyba, vypíše příslušnou chybovou hlášku.
     *
     * @param parametry předmět, který chce hráč vypáčit
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String[] parametry) {
        if (!plan.getKapsy().obsahujeVec("páčidlo")) {
            return "Nemůžeš nic páčit, když nemáš \"páčidlo\"";
        }

        if (parametry.length == 0) {
            return "Co chceš vypáčit? Musíš zadat název předmětu...";
        }
        if (parametry.length > 1) {
            return Strings.CHYBA_MOC_TEXTU;
        }

        String nazevVeci = parametry[0];

        if (!plan.getAktualniProstor().obsahujeVec(nazevVeci)) {
            return nazevVeci + " se nenachází v tomto prostoru.";
        }

        if (!plan.getAktualniProstor().vyberVec(nazevVeci).isVypacitelna()) {
            return nazevVeci + " se nedá vypáčit.";
        }

        plan.getAktualniProstor().vyberVec(nazevVeci).setVypacitelnost(false);
        Vec vlozenavec;
        switch (nazevVeci) {
            case "prkno_v_podlaze":
                vlozenavec = new Vec("rezavý_klíč", 1);
                break;
            case "zamčená_skříň":
                vlozenavec = new Vec("kostlivec_ve_skříni", 3);
                break;
            case "truhla":
                vlozenavec = new Vec("zlatá_cihla", 3);
                break;
            case "noční_stolek":
                vlozenavec = new Vec("knížka", 1);
                break;
            default:
                return "Někde se stala chyba";
        }
        plan.getAktualniProstor().vlozVec(vlozenavec);

        return "Vypáčil/a jsi " + nazevVeci + " a našel/našla předmět " + vlozenavec.getNazev();
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
