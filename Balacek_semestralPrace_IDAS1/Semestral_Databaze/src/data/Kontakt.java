/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;


public class Kontakt {
    private int idKontakt;
    private int telefon;
    private int mobil;
    private String email;

    public Kontakt(int idKontakt, int telefon, int mobil, String email) {
        this.idKontakt = idKontakt;
        this.telefon = telefon;
        this.mobil = mobil;
        this.email = email;
    }
    
    public Kontakt(int telefon, int mobil, String email) {
        this.telefon = telefon;
        this.mobil = mobil;
        this.email = email;
    }


    public int getIdKontakt() {
        return idKontakt;
    }

    public void setIdKontakt(int idKontakt) {
        this.idKontakt = idKontakt;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return "Telefon: " + telefon + ", mobil: " + mobil + ", email: " + email;
    }

    public int getMobil() {
        return mobil;
    }

    public void setMobil(int mobil) {
        this.mobil = mobil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
