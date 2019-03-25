package automobily;

import java.io.Serializable;

public abstract class Automobil extends Naves implements Serializable {

    private int pocetKol;
    private TypAutomobilu typAutomobilu;
    private BarvaAuta barva;
    

    public Automobil(int maxPocetPolozek, boolean jede,TypAutomobilu typAutomobilu, int pocetKol, 
            BarvaAuta barva) {
        super(maxPocetPolozek, jede);
        this.typAutomobilu = typAutomobilu;
        this.pocetKol = pocetKol;
        this.barva = barva;
    }

    public int getPocetKol() {
        return pocetKol;
    }

    public BarvaAuta getBarva() {
        return barva;
    }

    public void setPocetKol(int pocetKol) {
        this.pocetKol = pocetKol;
    }

    public void setBarva(BarvaAuta barva) {
        this.barva = barva;
    }

    public TypAutomobilu getTyp() {
        return typAutomobilu;
    }

    @Override
    public String toString() {
        return super.toString() + "Typ automobilu:  " + typAutomobilu.name()
                .toLowerCase() + ", Počet kol: " + pocetKol
                + ", Barva: " + barva.toString().toLowerCase() + ", ";
    }

}
