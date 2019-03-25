/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author caval
 */
public class Predmet {
    private int idPredmet;
    private String nazev;
    private String zkratka;
    private String zpusobZakonceni;
    private String formaVyuky;
    private int kapacita;

    public Predmet(int idPredmet, String nazev, String zkratka, String zpusobZakonceni, String formaVyuky, int kapacita) {
        this.idPredmet = idPredmet;
        this.nazev = nazev;
        this.zkratka = zkratka;
        this.zpusobZakonceni = zpusobZakonceni;
        this.formaVyuky = formaVyuky;
        this.kapacita = kapacita;
    }

    public int getIdPredmet() {
        return idPredmet;
    }

    @Override
    public String toString() {
        return "Zkratka: " + zkratka  + ", nazev: " + nazev + ", zakonceni: " + zpusobZakonceni + ", forma: " + formaVyuky + ", kapacita: " + kapacita + ", id: " + idPredmet;
    }

    public void setIdPredmet(int idPredmet) {
        this.idPredmet = idPredmet;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getZkratka() {
        return zkratka;
    }

    public void setZkratka(String zkratka) {
        this.zkratka = zkratka;
    }

    public String getZpusobZakonceni() {
        return zpusobZakonceni;
    }

    public void setZpusobZakonceni(String zpusobZakonceni) {
        this.zpusobZakonceni = zpusobZakonceni;
    }

    public String getFormaVyuky() {
        return formaVyuky;
    }

    public void setFormaVyuky(String formaVyuky) {
        this.formaVyuky = formaVyuky;
    }

    public int getKapacita() {
        return kapacita;
    }

    public void setKapacita(int kapacita) {
        this.kapacita = kapacita;
    }
    
    
}
