package logika;

import java.util.ArrayList;
import java.util.List;

/**
 * Třída Vec popisuje jednotlivé předměty ve hře.
 * Tato třída je součástí jednoduché textové hry.
 * "Vec" reprezentuje jednu věc ve scénáři hry, může mít různé vlastnosti,
 * např.: velikost, vypáčitelnost, čitelnost apod.
 *
 * @author    Jakub Hřebíček
 * @version   v2.0 2024/05/03
 */
public class Vec {
    private final String nazev;
    private final boolean prenositelna;
    private int velikost;
    private boolean vypacitelna;
    private boolean jedla;
    private boolean citelna;
    private boolean schovana;
    private boolean specialni;
    private Boolean zamcena;
    private List<Vec> seznamVeci;

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

    /**
     * Jeden z konstruktorů třídy, tento využívá parametry název, vypáčitelná a zamčená
     *
     * @param nazev název věci
     * @param vypacitelna true, pokud věc lze vypáčit, jinak false
     * @param zamcena true, pokud je věc zamčená, jinak false
     */
    public Vec(String nazev, boolean vypacitelna, boolean zamcena) {
        this.nazev = nazev;
        prenositelna = false;
        this.vypacitelna = vypacitelna;
        this.zamcena = zamcena;
        seznamVeci = new ArrayList<>();
    }

    /**
     * Jeden z konstruktorů třídy, tento využívá parametry název, vypáčitelná a zamčená
     *
     * @param nazev název věci
     * @param specialni true pokud má výjimku z kapacity batohu
     * @param velikost velikost věci, je využita při kontrole kapacity kapes
     */
    public Vec(boolean specialni, String nazev, int velikost) {
        this.specialni = specialni;
        this.nazev = nazev;
        this.velikost = velikost;
        prenositelna = true;
    }

    public boolean isSpecialni() {
        return specialni;
    }

    /**
     * metoda vrací seznam věcí ve věci
     *
     * @return list věcí
     */
    public List<Vec> getSeznamVeci() {
        return seznamVeci;
    }

    /**
     * metoda vrací odkaz na název věci
     *
     * @return název
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * metoda vrací odkaz na přenositelnost věci
     *
     * @return true pokud lze sebrat, jinak false
     */
    public boolean isPrenositelna() {
        return prenositelna;
    }

    /**
     * metoda vrací odkaz na velikost věci
     * slouží pro sledování kapacity
     *
     * @return číselná hodnota - velikost věci
     */
    public int getVelikost() {
        return velikost;
    }

    /**
     * metoda nastavuje velikost věci
     * slouží pro určení velikosti věci, když se během hry mění
     *
     * @param velikost int hodnota určující velikost věci
     */
    public void setVelikost(int velikost) {
        this.velikost = velikost;
    }

    /**
     * metoda vrací odkaz na vypáčitelnost
     *
     * @return true pokud lze vypáčit, jinak false
     */
    public boolean isVypacitelna() {
        return vypacitelna;
    }

    /**
     * metoda nastavuje vypáčitelnost věci
     * slouží jako toggle po vypáčení, aby věc nebylo možno vypáčit dvakrát
     *
     * @param vypacitelna boolean hodnota určující vypáčitelnost věci
     */
    public void setVypacitelnost(boolean vypacitelna) {
        this.vypacitelna = vypacitelna;
    }

    /**
     * metoda vrací odkaz na jedlost
     *
     * @return true pokud je věc jedlá, jinak false
     */
    public boolean isJedla() {
        return jedla;
    }

    /**
     * metoda vrací odkaz na čitelnost
     *
     * @return kapsy
     */
    public boolean isCitelna() {
        return citelna;
    }

    /**
     * metoda vrací odkaz na schovanost
     *
     * @return true pokud je věc schovaná (=nezobrazuje se v seznamu věcí), jinak false
     */
    public boolean isSchovana() {
        return schovana;
    }

    /**
     * metoda nastavuje schovanost věci
     * slouží pro odhalení prkna_v_podlaze po přečtení deníku
     *
     * @param schovana boolean hodnota určující schovanost věci
     */
    public void setSchovanost(boolean schovana) {
        this.schovana = schovana;
    }

    /**
     * metoda vrací odkaz na zamčenost
     *
     * @return true pokud je věc zamčená (=nelze ji otevřít), jinak false
     */
    public Boolean isZamcena() {
        return zamcena;
    }

    /**
     * metoda nastavuje zamčenost věci
     * slouží jako toggle pro určení, zda je truhla (apod.) zamčená nebo ne
     *
     * @param zamcena boolean hodnota určující zamčenost věci
     */
    public void setZamcena(boolean zamcena) {
        this.zamcena = zamcena;
    }

    public void vlozVec(Vec vec) {
        seznamVeci.add(vec);
    }
}

