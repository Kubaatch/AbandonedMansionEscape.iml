package logika;

public class PrikazPrecti implements IPrikaz {

    private static final String NAZEV = "přečti";
    private HerniPlan plan;
    private static final String UVOD_CTENI = "Uvnitř stojí: \n";

    @Override
    public String getNazev() {
        return NAZEV;
    }

    public PrikazPrecti(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String[] parametry) {
        if (parametry.length == 0) {
            return "Co mám přečíst? Musíš zadat název předmětu...";
        }
        if (parametry.length > 1) {
            return "Chceš toho přečíst nějak moc. Můžeš najednou přečíst jen jednu věc.";
        }

        String nazevVeci = parametry[0];

        if (!plan.getKapsy().obsahujeVec(nazevVeci)) {
            return nazevVeci + " nemáš u sebe v kapsách...";
        }

        if (!plan.getKapsy().vyberVec(nazevVeci).isCitelna()) {
            return nazevVeci + " nejde přečíst.";
        }

        String vracenyText = UVOD_CTENI;

        switch (nazevVeci) {
            case "bible":
                vracenyText += TEXT_BIBLE;
                break;
            case "deník":
                plan.getProstor("sklep").vyberVec("prkno_v_podlaze").setSchovanost(false);
                vracenyText += TEXT_DENIKU;
                break;
            case "modrá_kniha":
                vracenyText = TEXT_MODRA_KNIHA;
                break;
            case "zelená_kniha":
                vracenyText += TEXT_ZELENA_KNIHA;
                break;
            case "kožená_kniha":
                vracenyText += TEXT_KOZENA_KNIHA;
                break;
            case "černá_kniha":
                vracenyText += TEXT_CERNA_KNIHA;
                break;
            default:
                vracenyText = "Někde se stala chyba.";
        }

        return vracenyText;
    }

    private static final String TEXT_DENIKU = """
            Na poslední stránce si objevil tento text:
            
            S tíživým srdcem píši do svého deníku poslední zápis, kde oznamuji o mém rozhodnutí opustit toto honosné sídlo.
            Tento krok jsem učinil(a) z nezbytných důvodů, které nejsem schopen slovy popsat.
            Rád(a) bych vám předal(a) vědomost, že klíč k našemu domovu jsem uložil(a) ve sklepě pod "prkno_v_podlaze".
            Doufám, že se tato informace stane užitečnou v době mé nepřítomnosti a umožní vám bezproblémový vstup do našeho domova.

            Bůh vám žehnej,
            Hrabě Ignác Šlechtiprkno ze Šťastného Uvázaní"""; //výplod umělé inteligence

    private static final String TEXT_BIBLE = """
                Genesis 1
            Na počátku stvořil Bůh nebe a zemi.
            Země byla pustá a prázdná a nad propastnou tůní byla tma. Ale nad vodami vznášel se duch Boží.
            I řekl Bůh: „Budiž světlo!“ A bylo světlo.
            Viděl, že světlo je dobré, a oddělil světlo od tmy.
            Světlo nazval Bůh dnem a tmu nazval nocí.
            """;

    private static final String TEXT_MODRA_KNIHA = """
            Písmo v této knize už je zcela vybledlé, čitelné je pouze věnování:
            "Mojí milované Gertrudě a naší dceři Hermíně, bez níž by tato kniha byla vydaná o dva roky dříve."
            """;

    private static final String TEXT_ZELENA_KNIHA = """
            V roce 1932 se Amerika zmítala v otřesech velké krize a život se zdál být trpký a obtížný, jako ještě nikdy předtím.
            Příběh se odehrává ve věznici Cold Mountain, kam jsou transportováni odsouzenci na smrt.
            John Coffey je člověk, který si trest smrti zaslouží více než kdokoliv jiný. Tento mohutný obr byl odsouzen za smrt dvou malých dívek.
            Řekli byste možná, že cesta k poslední odplatě bude krátká a milosrdná – ale sami poznáte, jak se můžete mýlit.
            Doprovodíme společně Johna Coffeye na jeho poslední cestě, podobné zlému snu....
            """;

    //text účetní knihy byl vygenerován umělou inteligencí (chat.openai.com/chat)
    private static final String TEXT_KOZENA_KNIHA = """
            Datum: 28. března 1924
                        
            Účetní záznam
                        
            Název společnosti: ABC Corporation
                        
            Vlastník: John Smith
                        
            Příjem za fiskální rok: 1 200 000 USD
                        
            Daňové povinnosti:
            Předpokládaná daňová sazba: 20%
            Očekávaná daňová povinnost: 240 000 USD
            Skutečně zaplacená daň: 0 USD
            """;

    //text černé knihy byl vygenerován umělou inteligencí (chat.openai.com/chat)
    private static final String TEXT_CERNA_KNIHA = """
            Kapitán: "Tady kapitán, máme problém s hydraulikou. Snažíme se stabilizovat letadlo, ale točí se nám do stran."   
            První důstojník: "Zkusím restartovat systémy. Ale vypadá to, že jsme ztratili kontrolu nad výškou. Přepínám na ruční řízení."
            Palubní inženýr: "Máme varování o požáru v motoru číslo dva! Ztrácíme výkon."
            Kapitán: "Připravte se na nouzové přistání. Kontaktujte letovou kontrolu a žádejte o přistávací dráhu s nejnižším provozem."
            Palubní inženýr: "Motor číslo dva vypadá na kompletní ztrátu výkonu. Začínáme ztrácet výšku rychleji."
            Kapitán: "Zavolejte posádce a instruujte cestující, aby přijali nouzové postavení a připravili se na přistání. Přeji nám všem štěstí."
            """;
}
