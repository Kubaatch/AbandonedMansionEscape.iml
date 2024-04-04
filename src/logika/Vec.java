package logika;

public class Vec {
    private String nazev;
    private boolean prenositelna;
    private int velikost;
    private boolean vypacitelna;
    private boolean jedla;
    private boolean citelna;
    private boolean schovana;

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
     * @param jedla
     */
    public Vec(String nazev, boolean prenositelna, int velikost, boolean jedla) {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
        this.velikost = velikost;
        this.jedla = jedla;
    }

    /**
     *
     * @param nazev
     * @param prenositelna
     * @param citelna
     * @param velikost
     */
    public Vec(String nazev, boolean prenositelna, boolean citelna, int velikost) {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
        this.citelna = citelna;
        this.velikost = velikost;
    }

    public String getNazev() {
        return nazev;
    }

    public boolean isPrenositelna() {
        return prenositelna;
    }

    public void setPrenositelna(boolean prenositelna) {
        this.prenositelna = prenositelna;
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

    public boolean isJedla() {
        return jedla;
    }

    public boolean isCitelna() {
        return citelna;
    }

    public boolean isSchovana() {
        return schovana;
    }

    public void setSchovanost(boolean schovana) {
        this.schovana = schovana;
    }
}
