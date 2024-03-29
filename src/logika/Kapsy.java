package logika;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Kapsy {
    private final int kapacita;
    private int vyuzitaKapacita;
    private List<Vec> obsahKapes;

    /**
     *
     * @param kapacita
     */
    public Kapsy(int kapacita) {
        this.kapacita = kapacita;
        obsahKapes = new ArrayList<Vec>();
    }

    public boolean vlozDoKapes(Vec vec) {

        if (vyuzitaKapacita + vec.getVelikost() <= kapacita && vec.isPrenositelna()) {
            obsahKapes.add(vec);
            vyuzitaKapacita += vec.getVelikost();
            return true;
        }
        return false;
    }

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

    public Vec vyberVec(String nazevVeci) {
        Vec vybranaVec = null;

        for (Vec vec : obsahKapes) {
            if(vec.getNazev().equals(nazevVeci)) {
                vybranaVec = vec;
            }
        }

        return vybranaVec;
    }

    public String dlouhyPopis() {
        String vypis = "Obsah kapes:";
        for (Vec vec : obsahKapes) {
            vypis += " " + vec.getNazev();
        }

        return vypis;
    }

    public List<Vec> getObsahKapes() {
        return obsahKapes;
    }

    public boolean obsahujeVec(String nazevVeci) {
        for (Vec vec : obsahKapes) {
            if (vec.getNazev().equals(nazevVeci)) {
                return true;
            }
        }
        return false;
    }
}
