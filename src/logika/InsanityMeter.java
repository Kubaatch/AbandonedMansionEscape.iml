package logika;

public class InsanityMeter {
    private int urovenZblazneni;

    public InsanityMeter(int urovenZblazneni) {
        this.urovenZblazneni = urovenZblazneni;
    }

    public void increaseUrovenZblazneni() {
        urovenZblazneni++;
    }

    public int getUrovenZblazneni() {
        return urovenZblazneni;
    }

    public void setUrovenZblazneni(int urovenZblazneni) {
        this.urovenZblazneni = urovenZblazneni;
    }
}
