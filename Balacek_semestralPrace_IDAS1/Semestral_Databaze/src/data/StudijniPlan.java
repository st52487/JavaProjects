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
public class StudijniPlan {
    private int id_studijniPlan;
    private String druhKategorie;
    private String nazevPredmetu;
    private String zkratkaPredmetu;
    private String nazevOboru;
    private int odhadPoctuStudentu;
    private int kapacita;


    public StudijniPlan(int id_studijniPlan, String druhKategorie, 
            String nazevPredmetu, String zkratkaPredmetu, String nazevOboru,
            int odhadPoctuStudentu, int kapacita) {
        this.id_studijniPlan = id_studijniPlan;
        this.druhKategorie = druhKategorie;
        this.nazevPredmetu = nazevPredmetu;
        this.zkratkaPredmetu = zkratkaPredmetu;
        this.nazevOboru = nazevOboru;
        this.odhadPoctuStudentu = odhadPoctuStudentu;
        this.kapacita = kapacita;
    }

    public int getId_studijniPlan() {
        return id_studijniPlan;
    }

    public String getDruhKategorie() {
        return druhKategorie;
    }

    public String getNazevPredmetu() {
        return nazevPredmetu;
    }

    public String getZkratkaPredmetu() {
        return zkratkaPredmetu;
    }

    public String getNazevOboru() {
        return nazevOboru;
    }

    public int getOdhadPoctuStudentu() {
        return odhadPoctuStudentu;
    }

    public int getKapacita() {
        return kapacita;
    }

    @Override
    public String toString() {
        return "Predmet: " + nazevPredmetu + "druh: " + druhKategorie + ", zkratka predmetu: " + zkratkaPredmetu + ", obor: " + nazevOboru + ", odhadPoctuStudentu: " + odhadPoctuStudentu + ", kapacita=" + kapacita + ", id: " + id_studijniPlan;
    }
}
