package logika;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída kapsy vytváří úložný prostor, kam hráč může ukládat předměty.
 * Parametr kapacita určuje, kolik předmětů lze do kapes vložit.
 *
 * @author    Jakub Hřebíček
 * @version   v1.8 2024/04/04
 */
public class Kapsy {
    private final int maxKapacita;
    private int vyuzitaKapacita;
    private List<Vec> obsahKapes;

    /**
     * Konstruktor třídy
     *
     * @param kapacita maximální množství věcí v kapsách
     */
    public Kapsy(int kapacita) {
        this.maxKapacita = kapacita;
        obsahKapes = new ArrayList<>();
    }

    /**
     * metoda slouží ke vkládání věcí do kapes
     * předmět se vloží pouze pokud je věc přenositelná a v kapsách je na předmět stále místo
     *
     * @param vec  předmět ke vložení
     * @return     vrací true pokud se povedlo vložit, jinak false
     */
    public boolean vlozDoKapes(Vec vec) {

        if (vyuzitaKapacita + vec.getVelikost() <= maxKapacita && vec.isPrenositelna()) {
            obsahKapes.add(vec);
            vyuzitaKapacita += vec.getVelikost();
            return true;
        }
        return false;
    }

    /**
     * Metoda odebírá předmět z kapes.
     * Jako vstup bere název předmětu, pokud je v kapsách, odstraní ho a metoda vrátí (Vec)předmět.
     * Pokud předmět není v kapsách, metoda vrátí null.
     *
     * @param nazevVeci název věci, kterou chce hráč odebrat
     * @return odebraný předmět, null pokud neexistuje
     */
    public Vec odeberZKapes(String nazevVeci) {
        for (Vec vec : obsahKapes) {
            if (vec.getNazev().equals(nazevVeci)) {
                obsahKapes.remove(vec);
                vyuzitaKapacita -= vec.getVelikost();
                return vec;
            }
        }
        return null;
    }

    /**
     * Metoda zjišťuje, zda se v kapsách nachází předmět.
     * Jako vstupní hodnotu bere název předmětu.
     * Vrátí předmět, pokud je v kapsách, jinak null.
     *
     * @param nazevVeci název věci, kterou hledáme
     * @return (Vec)předmět, nebo null
     */
    public Vec getVec(String nazevVeci) {
        Vec vybranaVec = null;

        for (Vec vec : obsahKapes) {
            if(vec.getNazev().equals(nazevVeci)) {
                vybranaVec = vec;
            }
        }

        return vybranaVec;
    }

    /**
     * Metoda vytváří řádek vypisující všechny předměty v kapsách
     *
     * @return seznam věcí v kapsách
     */
    public String dlouhyPopis() {
        StringBuilder vypis = new StringBuilder("Obsah kapes:");
        for (Vec vec : obsahKapes) {
            vypis.append(' ').append(vec.getNazev());
        }

        return vypis.toString();
    }

    /**
     * Metoda sleduje, zda se daný předmět nachází v kapsách.
     *
     * @param nazevVeci název hledané věci
     * @return true pokud se předmět nachází v kapsách, jinak false
     */
    public boolean obsahujeVec(String nazevVeci) {
        for (Vec vec : obsahKapes) {
            if (vec.getNazev().equals(nazevVeci)) {
                return true;
            }
        }
        return false;
    }

    public int getMaxKapacita() {
        return vyuzitaKapacita;
    }
}
