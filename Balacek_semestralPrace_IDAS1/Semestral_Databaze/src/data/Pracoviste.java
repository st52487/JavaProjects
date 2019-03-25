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
public class Pracoviste {
    private int idPracoviste;
    private String zkratka;
    private String nazev;
    private String fakulta;
    private String plnyNazevFakulta;

    @Override
    public String toString() {
        return "Nazev: "+ nazev + ", fakulta: " + fakulta + ", plnyNazevFakulta: " + plnyNazevFakulta + ",zkratka: " + zkratka + ", id: " + idPracoviste;
    }

    public Pracoviste(int idPracoviste, String zkratka, String nazev, String fakulta, String plnyNazevFakulta) {
        this.idPracoviste = idPracoviste;
        this.zkratka = zkratka;
        this.nazev = nazev;
        this.fakulta = fakulta;
        this.plnyNazevFakulta = plnyNazevFakulta;
    }
    
    public Pracoviste(int idPracoviste, String nazev, String fakulta, String plnyNazevFakulta) {
        this.idPracoviste = idPracoviste;
        this.nazev = nazev;
        this.fakulta = fakulta;
        this.plnyNazevFakulta = plnyNazevFakulta;
    }

    public int getIdPracoviste() {
        return idPracoviste;
    }

    public void setIdPracoviste(int idPracoviste) {
        this.idPracoviste = idPracoviste;
    }

    public String getZkratka() {
        return zkratka;
    }

    public void setZkratka(String zkratka) {
        this.zkratka = zkratka;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getFakulta() {
        return fakulta;
    }

    public void setFakulta(String fakulta) {
        this.fakulta = fakulta;
    }

    public String getPlnyNazevFakulta() {
        return plnyNazevFakulta;
    }

    public void setPlnyNazevFakulta(String plnyNazevFakulta) {
        this.plnyNazevFakulta = plnyNazevFakulta;
    }
    
    
}
