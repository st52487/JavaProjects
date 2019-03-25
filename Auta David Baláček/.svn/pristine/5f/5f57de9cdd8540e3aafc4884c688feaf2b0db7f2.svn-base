
package automobily;


public class Tahac extends Automobil{
   
    private String identifikace;
    private boolean jeBenzin;
    public Tahac(int maxPocetPolozek, boolean jede, int pocetKol, BarvaAuta barva, String identifikace,
            boolean jeBenzin) {
        super(maxPocetPolozek, jede, TypAutomobilu.TAHAC, pocetKol, barva);
        this.identifikace = identifikace;
        this.jeBenzin = jeBenzin;
    }

    public String getIdentifikace() {
        return identifikace;
    }

    public void setIdentifikace(String identifikace) {
        this.identifikace = identifikace;
    }

   
    public void setJeBenzin(boolean jeBenzin) {
        this.jeBenzin = jeBenzin;
    }

   

    public boolean isJeBenzin() {
        return jeBenzin;
    }
    
    @Override
    public String toString() {
        return super.toString() + " Identifikace: " + identifikace + ", Je benzin: "
                + (jeBenzin ? "Ano": "Ne");
    }
}
