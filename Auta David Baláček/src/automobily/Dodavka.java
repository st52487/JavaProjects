package automobily;

public class Dodavka extends Automobil {

    private int hmotnost;
    private String typMotoru;

    public Dodavka(int maxPocetPolozek, boolean jede, int pocetKol, BarvaAuta barva, int hmotnost,
            String typMotoru) {
        super(maxPocetPolozek, jede, TypAutomobilu.DODAVKA, pocetKol, barva);
        this.hmotnost = hmotnost;
        this.typMotoru = typMotoru;
    }
    
    public String getTypMotoru() {
        return typMotoru;
    }

    public int getHmotnost() {
        return hmotnost;
    }

    public void setHmotnost(int hmotnost) {
        this.hmotnost = hmotnost;
    }

    public void setTypMotoru(String typMotoru) {
        this.typMotoru = typMotoru;
    }

    @Override
    public String toString() {
        return super.toString() + " Hmotnost: " + hmotnost + ", Typ motoru: "
                + typMotoru;
    }

}
