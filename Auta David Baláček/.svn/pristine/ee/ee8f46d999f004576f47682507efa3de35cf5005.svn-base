package automobily;

public class OsobniAutomobil extends Automobil{
    
    private String znackaAuta;
    private boolean funkcni;

    public OsobniAutomobil(int maxPocetPolozek, boolean jede, int pocetKol, BarvaAuta barva, String znackaAuta,
            boolean funkcni) {
        super(maxPocetPolozek, jede, TypAutomobilu.OSOBNI, pocetKol, barva);
        this.znackaAuta = znackaAuta;
        this.funkcni= funkcni;
    }

    public String getZnackaAuta() {
        return znackaAuta;
    }

    public boolean isFunkcni() {
        return funkcni;
    }

    public void setZnackaAuta(String znackaAuta) {
        this.znackaAuta = znackaAuta;
    }

    public void setFunkcni(boolean funkcni) {
        this.funkcni = funkcni;
    }
    
    @Override
    public String toString() {
        return super.toString() + " Znacka auta: " + znackaAuta + ", Funkční: " 
                + (funkcni ? "Ano": "Ne");
    }
}
