package logika;

public class Vec {
    private String nazev;
    private boolean prenositelna;
    private int velikost;
    private boolean vypacitelna;

    /**
     *
     * @param nazev
     * @param prenositelna
     * @param vypacitelna
     */
    public Vec(String nazev, boolean prenositelna, boolean vypacitelna) {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
        this.vypacitelna = vypacitelna;
    }

    /**
     *
     * @param nazev
     * @param prenositelna
     * @param velikost
     */
    public Vec(String nazev, boolean prenositelna, int velikost) {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
        this.velikost = velikost;
    }


    public String getNazev() {
        return nazev;
    }

    public boolean isPrenositelna() {
        return prenositelna;
    }

    public int getVelikost() {
        return velikost;
    }
    public boolean isVypacitelna() {
        return vypacitelna;
    }

    public void setVypacitelnost(boolean vypacitelna) {
        this.vypacitelna = vypacitelna;
    }
}
