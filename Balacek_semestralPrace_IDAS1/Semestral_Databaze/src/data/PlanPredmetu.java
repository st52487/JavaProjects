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
public class PlanPredmetu {

    private final String nazevPredmetu;
    private final String zkratkaPredmetu;
    private final String jmenoVyucujiciho;
    private final String prijmeniVyucujiciho;
    private final int kapacita;
    
    
     public PlanPredmetu(String nazevPredmetu, String zkratkaPredmetu, String jmenoVyucujiciho, String prijmeniVyucujiciho, int kapacita){
        this.nazevPredmetu = nazevPredmetu;
        this.zkratkaPredmetu = zkratkaPredmetu;
        this.jmenoVyucujiciho = jmenoVyucujiciho;
        this.prijmeniVyucujiciho = prijmeniVyucujiciho;
        this.kapacita = kapacita;
    }

    @Override
    public String toString() {
        return "Nazev: " + nazevPredmetu + ", kapacita: " + kapacita + ", zkratka: " + zkratkaPredmetu + ", vyučujici: " + jmenoVyucujiciho + ", vyučujici: " + prijmeniVyucujiciho;
    }
     
     

    public String getNazevPredmetu() {
        return nazevPredmetu;
    }

    public String getZkratkaPredmetu() {
        return zkratkaPredmetu;
    }

    public String getJmenoVyucujiciho() {
        return jmenoVyucujiciho;
    }

    public String getPrijmeniVyucujiciho() {
        return prijmeniVyucujiciho;
    }

    public int getKapacita() {
        return kapacita;
    }
     
     
}
