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
public class Kategorie {
    private int idKategorie;
    private String druhKategorie;

    public Kategorie(int idKategorie, String druhKategorie) {
        this.idKategorie = idKategorie;
        this.druhKategorie = druhKategorie;
    }

    public int getIdKategorie() {
        return idKategorie;
    }

    public void setIdKategorie(int idKategorie) {
        this.idKategorie = idKategorie;
    }

    public String getDruhKategorie() {
        return druhKategorie;
    }

    public void setDruhKategorie(String druhKategorie) {
        this.druhKategorie = druhKategorie;
    }

    @Override
    public String toString() {
        return "Kategorie: " + druhKategorie;
    }
    
    
}
