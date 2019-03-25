
package data;


public class Vyucujici {
    
    private int idVyucujici;
    private String prijmeni;
    private String jmeno;
    private String titulZa;
    private String titulPred;
    private Kontakt kontakt;
    private int idKontakt;
    private int idPracoviste;
    

    public Vyucujici(int idVyucujici, String prijmeni, String jmeno, String titulZa, String titulPred, Kontakt kontakt) {
        this.idVyucujici = idVyucujici;
        this.prijmeni = prijmeni;
        this.jmeno = jmeno;
        this.titulZa = titulZa;
        this.titulPred = titulPred;
        this.kontakt = kontakt;
    }
    public Vyucujici(int idVyucujici, String prijmeni, String jmeno, String titulZa, String titulPred, int idKontakt) {
        this.idVyucujici = idVyucujici;
        this.prijmeni = prijmeni;
        this.jmeno = jmeno;
        this.titulZa = titulZa;
        this.titulPred = titulPred;
        this.idKontakt = idKontakt;
    }

    public int getIdPracoviste() {
        return idPracoviste;
    }

    public void setIdPracoviste(int idPracoviste) {
        this.idPracoviste = idPracoviste;
    }

    public int getIdKontakt() {
        return idKontakt;
    }

    public void setIdKontakt(int idKontakt) {
        this.idKontakt = idKontakt;
    }

    @Override
    public String toString() {
        return "Prijmeni: " + prijmeni + ", jmeno: " + jmeno + ", titulZa: " + titulZa + ", titulPred: " + titulPred  + ", idKontakt: " + idKontakt +
                ", id: " + idVyucujici;
    }
    

    public int getIdVyucujici() {
        return idVyucujici;
    }

    public void setIdVyucujici(int idVyucujici) {
        this.idVyucujici = idVyucujici;
    }

    public Kontakt getKontakt() {
        return kontakt;
    }

    public void setKontakt(Kontakt kontakt) {
        this.kontakt = kontakt;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getTitulZa() {
        return titulZa;
    }

    public void setTitulZa(String titulZa) {
        this.titulZa = titulZa;
    }

    public String getTitulPred() {
        return titulPred;
    }

    public void setTitulPred(String titulPred) {
        this.titulPred = titulPred;
    }
    
    
}
