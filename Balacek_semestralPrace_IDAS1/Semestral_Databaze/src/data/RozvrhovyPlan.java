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
public class RozvrhovyPlan {

    private int idRozvrhovyPlan;
    private int rozsahHodin;
    private String nazevPredmetu;
    private String zkratkaPredmet;
    private int kapacitaPredmetu;
    private String formaVyukyPredmetu;
    private String druhVyukyPredmetu;
    private int idZpusobVyuky;

    public RozvrhovyPlan(int idRozvrhovyPlan, int rozsahHodin) {
        this.idRozvrhovyPlan = idRozvrhovyPlan;
        this.rozsahHodin = rozsahHodin;
    }

    @Override
    public String toString() {
        return "Předmět: " + nazevPredmetu + ", druh: " + druhVyukyPredmetu + ", zkratka: " + zkratkaPredmet + ", kapacita: " + kapacitaPredmetu + ", forma: " + formaVyukyPredmetu
                + ", hodiny: " + rozsahHodin + ", id: " + idRozvrhovyPlan;
    }

    public int getIdZpusobVyuky() {
        return idZpusobVyuky;
    }

    public void setIdZpusobVyuky(int idZpusobVyuky) {
        this.idZpusobVyuky = idZpusobVyuky;
    }

    public RozvrhovyPlan(int idRozvrhovyPlan, String nazevPredmetu, String zkratkaPredmetu, int kapacitaPredmetu,
            String formaVyukyPredmetu, String druhVyukyPredmetu, int rozsahHodin, int idZpusobvyuky) {
        this.idRozvrhovyPlan = idRozvrhovyPlan;
        this.nazevPredmetu = nazevPredmetu;
        this.zkratkaPredmet = zkratkaPredmetu;
        this.kapacitaPredmetu = kapacitaPredmetu;
        this.formaVyukyPredmetu = formaVyukyPredmetu;
        this.druhVyukyPredmetu = druhVyukyPredmetu;
        this.idZpusobVyuky = idZpusobvyuky;
        this.rozsahHodin = rozsahHodin;
    }

    public String getNazevPredmetu() {
        return nazevPredmetu;
    }

    public void setNazevPredmetu(String nazevPredmetu) {
        this.nazevPredmetu = nazevPredmetu;
    }

    public String getZkratkaPredmet() {
        return zkratkaPredmet;
    }

    public void setZkratkaPredmet(String zkratkaPredmet) {
        this.zkratkaPredmet = zkratkaPredmet;
    }

    public int getKapacitaPredmetu() {
        return kapacitaPredmetu;
    }

    public void setKapacitaPredmetu(int kapacitaPredmetu) {
        this.kapacitaPredmetu = kapacitaPredmetu;
    }

    public String getFormaVyukyPredmetu() {
        return formaVyukyPredmetu;
    }

    public void setFormaVyukyPredmetu(String formaVyukyPredmetu) {
        this.formaVyukyPredmetu = formaVyukyPredmetu;
    }

    public String getDruhVyukyPredmetu() {
        return druhVyukyPredmetu;
    }

    public void setDruhVyukyPredmetu(String druhVyukyPredmetu) {
        this.druhVyukyPredmetu = druhVyukyPredmetu;
    }

    public int getIdRozvrhovyPlan() {
        return idRozvrhovyPlan;
    }

    public void setIdRozvrhovyPlan(int idRozvrhovyPlan) {
        this.idRozvrhovyPlan = idRozvrhovyPlan;
    }

    public int getRozsahHodin() {
        return rozsahHodin;
    }

    public void setRozsahHodin(int rozsahHodin) {
        this.rozsahHodin = rozsahHodin;
    }

}
