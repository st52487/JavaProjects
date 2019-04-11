package objekty;

public class Pozice {
    
    private TypPozice typPozice;
    private Zamestnanec zamestnanec;
    
    public Pozice(TypPozice typPozice, Zamestnanec zamestnanec){
        this.typPozice = typPozice;
        this.zamestnanec = zamestnanec;
    }

    public TypPozice getTypPozice() {
        return typPozice;
    }

    public void setZamestnanec(Zamestnanec zamestnanec) {
        this.zamestnanec = zamestnanec;
    }

    public void setTypPozice(TypPozice typPozice) {
        this.typPozice = typPozice;
    }

    public Zamestnanec getZamestnanec() {
        return zamestnanec;
    }

    @Override
    public String toString() {
        return "Pozice: " + typPozice + ", zamestnanec: " + zamestnanec;
    }
    
    
}
