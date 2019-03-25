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
public class StudijniObor {
    private int idStudijniObor;
    private String slozeniPlanu;
    private String nazevOboru;
    private int odhadPoctuStudentu;

    public StudijniObor(int idStudijniObor, String slozeniPlanu, String nazevOboru, int odhadPoctuStudentu) {
        this.idStudijniObor = idStudijniObor;
        this.slozeniPlanu = slozeniPlanu;
        this.nazevOboru = nazevOboru;
        this.odhadPoctuStudentu = odhadPoctuStudentu;
    }

    public int getIdStudijniObor() {
        return idStudijniObor;
    }

    public void setIdStudijniObor(int idStudijniObor) {
        this.idStudijniObor = idStudijniObor;
    }

    @Override
    public String toString() {
        return "Nazev: " + nazevOboru + ", složení plánu: " + slozeniPlanu  + ", odhad poctu studentu: " + odhadPoctuStudentu + ", id: " + idStudijniObor;
    }

    public String getSlozeniPlanu() {
        return slozeniPlanu;
    }

    public void setSlozeniPlanu(String slozeniPlanu) {
        this.slozeniPlanu = slozeniPlanu;
    }

    public String getNazevOboru() {
        return nazevOboru;
    }

    public void setNazevOboru(String nazevOboru) {
        this.nazevOboru = nazevOboru;
    }

    public int getOdhadPoctuStudentu() {
        return odhadPoctuStudentu;
    }

    public void setOdhadPoctuStudentu(int odhadPoctuStudentu) {
        this.odhadPoctuStudentu = odhadPoctuStudentu;
    }
    
    
}
