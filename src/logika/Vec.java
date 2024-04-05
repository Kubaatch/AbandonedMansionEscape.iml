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
     * Jeden z konstruktorů třídy, tento využívá parametry název a velikost
     *
     * @param nazev název věci
     * @param velikost velikost věci, je využita při kontrole kapacity kapes
     */
    public Vec(String nazev, int velikost) {
        this.nazev = nazev;
        prenositelna = true;
        this.velikost = velikost;
    }

    /**
     * Jeden z konstruktorů třídy, tento využívá parametry název a vypáčitelná
     *
     * @param nazev název věci
     * @param vypacitelna true, pokud věc lze vypáčit, jinak false
     */
    public Vec(String nazev, boolean vypacitelna) {
        this.nazev = nazev;
        prenositelna = false;
        this.vypacitelna = vypacitelna;
    }

    /**
     * Jeden z konstruktorů třídy, tento využívá parametry název, velikost a jedlá
     *
     * @param nazev název věci
     * @param velikost velikost věci, je využita při kontrole kapacity kapes
     * @param jedla true, pokud věc lze sníst, jinak false
     */
    public Vec(String nazev, int velikost, boolean jedla) {
        this.nazev = nazev;
        prenositelna = true;
        this.velikost = velikost;
        this.jedla = jedla;
    }

    /**
     * Jeden z konstruktorů třídy, tento využívá parametry název, čitelná a velikost
     *
     * @param nazev název věci
     * @param citelna true, pokud věc lze přečíst, jinak false
     * @param velikost velikost věci, je využita při kontrole kapacity kapes
     */
    public Vec(String nazev, boolean citelna, int velikost) {
        this.nazev = nazev;
        prenositelna = true;
        this.citelna = citelna;
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
