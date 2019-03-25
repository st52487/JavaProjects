/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enumy;

/**
 *
 * @author caval
 */
public enum DruhKategorie {
    A("A"), B("B"), C("C");
    
    private String kategorie;

    private DruhKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    @Override
    public String toString() {
        return kategorie;
    }
    
    
}
