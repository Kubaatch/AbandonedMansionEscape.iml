package logika;

import java.util.Set;

public class PrikazPouzij implements IPrikaz {

    private static final String NAZEV = "použij";
    private final HerniPlan plan;

    //zápis z deníku, oddělen pro přehlednost
    private static final String TEXT_DENIKU = """
            Na poslední stránce si objevil tento text:
            
            S tíživým srdcem píši do svého deníku poslední zápis, kde oznamuji o mém rozhodnutí opustit toto honosné sídlo.
            Tento krok jsem učinil(a) z nezbytných důvodů, které nejsem schopen slovy popsat.
            Rád(a) bych vám předal(a) vědomost, že klíč k našemu domovu jsem uložil(a) pod "prkno_v_podlaze".
            Doufám, že se tato informace stane užitečnou v době mé nepřítomnosti a umožní vám bezproblémový vstup do našeho domova.

            Bůh vám žehnej,
            Hrabě Ignác Šlechtiprkno ze Šťastného Uvázaní"""; //výplod umělé inteligence

    public PrikazPouzij(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String[] parametry) {
        if (parametry.length == 0) {
            return "Co mám použít? Musíš zadat název předmětu...";
        }
        if (parametry.length > 2) {
            return "Chceš toho položit nějak moc. Můžeš najednou položit jen jednu věc.";
        }

        Set<Vec> seznamVeci = plan.getKapsy().getObsahKapes();
        String pouzivanaVec = parametry[0];

        for (Vec vec : seznamVeci) {
            if (vec.getNazev().equals(pouzivanaVec)) {
                return pouzijVec(parametry);
            }
        }

        return "Tuto věc nemáš v kapsách, nemůžeš ji tedy použít.";
    }

    private String pouzijVec(String[] parametry) {
        String vec = parametry[0];

        String vracenyText = "Použil jsi " + vec + ".\n";

        switch (vec) {
            case "řízek_v_alobalu":
                vracenyText += "Chutnalo ti, doufáš že někde najdeš další.";
                plan.getKapsy().odeberZKapes(vec);
                break;
            case "deník":
                vracenyText += TEXT_DENIKU;
                break;
            case "páčidlo":
                vracenyText += pouzijPacidlo(parametry);
                break;
            case "d":
                plan.getKapsy().odeberZKapes(vec);
                break;
            default:
                vracenyText += "Věc nejde použít, hodil si ji naštvaně proti zdi a znova sebral.";
                break;
        }

        return vracenyText;
    }

    public String pouzijPacidlo(String[] parametry) {
        String pacenaVec = parametry[1];

        if (plan.getAktualniProstor().obsahujeVec(pacenaVec)) {
            if (plan.getAktualniProstor().vyberVec(pacenaVec).isVypacitelna()) {
                switch (pacenaVec) {
                    case "prkno_v_podlaze":
                        Vec klic = new Vec("rezavý_klíč", true, 1);
                        plan.getAktualniProstor().vlozVec(klic);
                        plan.getAktualniProstor().vyberVec(pacenaVec).setVypacitelnost(false);
                        return "Vypáčil/a jsi prkno v podlaze a zjistil/a, že pod ním leží \"rezavý_klíč\".";
                    case "zamčená_skříň":
                        Vec kostlivec = new Vec("kostlivec", true, 3);
                        plan.getAktualniProstor().vlozVec(kostlivec);
                        plan.getAktualniProstor().vyberVec(pacenaVec).setVypacitelnost(false);
                        return "Vypáčil/a jsi zamčenou skříň a našel/našla kostlivce ve skříni.";
                    case "truhla":
                        Vec zlataCihla = new Vec("zlatá_cihla", true, 3);
                        plan.getAktualniProstor().vlozVec(zlataCihla);
                        plan.getAktualniProstor().vyberVec(pacenaVec).setVypacitelnost(false);
                        return "Vypáčil/a jsi starou truhlu, leží v ní \"zlatá_cihla\".";
                    case "noční_stolek":
                        Vec kniha = new Vec("knížka", true, 1);
                        plan.getAktualniProstor().vlozVec(kniha);
                        plan.getAktualniProstor().vyberVec(pacenaVec).setVypacitelnost(false);
                        return "Vypáčil/a jsi noční stolek, byla v něm \"knížka\".";
                    default:
                        return "Někde nastala chyba";
                }
            }
            return "Tahle věc nejde vypáčit.";
        }
        return "Věc, na kterou chceš páčidlo použít, se nenachází v této místnosti.";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }


}
