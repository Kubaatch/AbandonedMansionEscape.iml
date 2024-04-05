package logika;

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

    private HerniPlan plan;

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
            return "Chceš toho vypáčit nějak moc. Můžeš najednou vypáčit jen jednu věc.";
        }

        String nazevVeci = parametry[0];

        if (!plan.getAktualniProstor().obsahujeVec(nazevVeci)) {
            return nazevVeci + " se nenachází v tomto prostoru.";
        }

        if (!plan.getAktualniProstor().vyberVec(nazevVeci).isVypacitelna()) {
            return nazevVeci + " se nedá vypáčit.";
        }

        plan.getAktualniProstor().vyberVec(nazevVeci).setVypacitelnost(false);
        switch (nazevVeci) {
            case "prkno_v_podlaze":
                Vec klic = new Vec("rezavý_klíč", 1);
                plan.getAktualniProstor().vlozVec(klic);
                return "Vypáčil/a jsi prkno v podlaze a zjistil/a, že pod ním leží \"rezavý_klíč\".";
            case "zamčená_skříň":
                Vec kostlivec = new Vec("kostlivec", 3);
                plan.getAktualniProstor().vlozVec(kostlivec);
                return "Vypáčil/a jsi zamčenou skříň a našel/našla kostlivce ve skříni.";
            case "truhla":
                Vec zlataCihla = new Vec("zlatá_cihla", 3);
                plan.getAktualniProstor().vlozVec(zlataCihla);
                return "Vypáčil/a jsi starou truhlu, leží v ní \"zlatá_cihla\".";
            case "noční_stolek":
                Vec kniha = new Vec("knížka", 1);
                plan.getAktualniProstor().vlozVec(kniha);
                return "Vypáčil/a jsi noční stolek, byla v něm \"knížka\".";
            default:
                return "Někde se stala chyba";
        }
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
