package logika;
import java.util.HashSet;
import java.util.Set;

public class Kapsy {
    private final int kapacita;
    private int vyuzitaKapacita;
    private Set<Vec> obsahKapes;

    /**
     * DOPLN POPIS
     *
     * @param kapacita
     */
    public Kapsy(int kapacita) {
        this.kapacita = kapacita;
        obsahKapes = new HashSet<>();
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

    public String dlouhyPopis() {
        String vypis = "Obsah kapes:";
        for (Vec vec : obsahKapes) {
            vypis += " " + vec.getNazev();
        }

        return vypis;
    }

    public Set<Vec> getObsahKapes() {
        return obsahKapes;
    }
}
