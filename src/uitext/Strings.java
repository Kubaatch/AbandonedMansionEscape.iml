package uitext;

/**
 * Třída Strings vytváří oddělený prostor pro inicializaci různých textů.
 * Nachází se zde Stringy, které by v kódu byly nepřehledné
 * a těžko dohledatelné, nebo se v kódu nacházejí vícekrát
 * <p>
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Jakub Hřebíček
 * @version   v2.0 2024/05/03
 */
public class Strings {
    public static final String UVOD = """
        Vítej!
        Probudil/a ses v královské posteli v očividně dlouho opuštěném sídle.
        Nevíš proč tu jsi a chceš se co nejdříve dostat ven.
        Napiš 'nápověda', pokud si nevíš rady, jak hrát dál.
        """;

    public static final String EPILOG_FAIL =
            "Ticho a samota v sídle tě přivedly k šílenství, prohrál jsi tuto hru.";

    public static final String EPILOG_KONEC =
            "Hra byla ukončena příkazem konec.\nDíky že jste si zahráli, ahoj :)";

    public static final String EPILOG_WIN =
            "Gratuluji, vyhrál jsi hru!";

    public static final String NAPOVEDA = """
            Tvým úkolem je najít klíč, který ti odemkne dveře ven z tohoto sídla.
        
            Můžeš zadat tyto příkazy:
            """;

    public static final String UPOZORNENI_ZBLAZNENI =
            "Fuj! Leknul ses netopýra, který kolem tebe proletěl. Možná by ses měl něčím uklidnit.\n";

    public static final String CHYBA_MOC_TEXTU =
            "Napsal jsi toho nějak moc...";

    //text deníku byl vygenerován umělou inteligencí (chat.openai.com/chat)
    public static final String TEXT_DENIKU = """
            Na poslední stránce si objevil tento text:
            
            S tíživým srdcem píši do svého deníku poslední zápis, kde oznamuji o mém rozhodnutí opustit toto honosné sídlo.
            Tento krok jsem učinil(a) z nezbytných důvodů, které nejsem schopen slovy popsat.
            Rád(a) bych vám předal(a) vědomost, že klíč k našemu domovu jsem uložil(a) ve sklepě pod "prkno_v_podlaze".
            Doufám, že se tato informace stane užitečnou v době mé nepřítomnosti a umožní vám bezproblémový vstup do našeho domova.

            Bůh vám žehnej,
            Hrabě Ignác Šlechtiprkno ze Šťastného Uvázaní""";

    public static final String TEXT_BIBLE = """
                Genesis 1
            Na počátku stvořil Bůh nebe a zemi.
            Země byla pustá a prázdná a nad propastnou tůní byla tma. Ale nad vodami vznášel se duch Boží.
            I řekl Bůh: „Budiž světlo!“ A bylo světlo.
            Viděl, že světlo je dobré, a oddělil světlo od tmy.
            Světlo nazval Bůh dnem a tmu nazval nocí.
            """;

    public static final String TEXT_MODRA_KNIHA = """
            Písmo v této knize už je zcela vybledlé, čitelné je pouze věnování:
            "Mojí milované Gertrudě a naší dceři Hermíně, bez níž by tato kniha byla vydaná o dva roky dříve."
            """;

    // text zelené knihy byl zkopírován ze stránky csfd.cz (csfd.cz/film/2292-zelena-mile/prehled/)
    public static final String TEXT_ZELENA_KNIHA = """
            V roce 1932 se Amerika zmítala v otřesech velké krize a život se zdál být trpký a obtížný, jako ještě nikdy předtím.
            Příběh se odehrává ve věznici Cold Mountain, kam jsou transportováni odsouzenci na smrt.
            John Coffey je člověk, který si trest smrti zaslouží více než kdokoliv jiný. Tento mohutný obr byl odsouzen za smrt dvou malých dívek.
            Řekli byste možná, že cesta k poslední odplatě bude krátká a milosrdná – ale sami poznáte, jak se můžete mýlit.
            Doprovodíme společně Johna Coffeye na jeho poslední cestě, podobné zlému snu....
            """;

    //text účetní knihy byl vygenerován umělou inteligencí (chat.openai.com/chat)
    public static final String TEXT_KOZENA_KNIHA = """
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
    public static final String TEXT_CERNA_KNIHA = """
            Kapitán: "Tady kapitán, máme problém s hydraulikou. Snažíme se stabilizovat letadlo, ale točí se nám do stran."
            První důstojník: "Zkusím restartovat systémy. Ale vypadá to, že jsme ztratili kontrolu nad výškou. Přepínám na ruční řízení."
            Palubní inženýr: "Máme varování o požáru v motoru číslo dva! Ztrácíme výkon."
            Kapitán: "Připravte se na nouzové přistání. Kontaktujte letovou kontrolu a žádejte o přistávací dráhu s nejnižším provozem."
            Palubní inženýr: "Motor číslo dva vypadá na kompletní ztrátu výkonu. Začínáme ztrácet výšku rychleji."
            Kapitán: "Zavolejte posádce a instruujte cestující, aby přijali nouzové postavení a připravili se na přistání. Přeji nám všem štěstí."
            """;
}
