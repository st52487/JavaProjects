package automobily;

public class NakladniAutomobil extends Automobil {

    private int pocetKoni;
    private boolean nahonNaVsechny;

    public NakladniAutomobil(int maxPocetPolozek, boolean jede, int pocetKol, BarvaAuta barva, int pocetKoni,
            boolean nahonNaVsechny) {
        super(maxPocetPolozek, jede, TypAutomobilu.NAKLADNI, pocetKol, barva);
        this.pocetKoni = pocetKoni;
        this.nahonNaVsechny = nahonNaVsechny;   
    }

    public int getPocetKoni() {
        return pocetKoni;
    }

    public boolean isNahonNaVsechny() {
        return nahonNaVsechny;
    }

    public void setPocetKoni(int pocetKoni) {
        this.pocetKoni = pocetKoni;
    }

    public void setNahonNaVsechny(boolean nahonNaVsechny) {
        this.nahonNaVsechny = nahonNaVsechny;
    }

    @Override
    public String toString() {
        return super.toString() + " Pocet koni: " + pocetKoni
                + ", Nahon na vsechny: " + (nahonNaVsechny ? "Ano" : "Ne");
    }
}
