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
public class ZpusobVyuky {
    private int idZpusobVyuky;
    private String druhVyuky;

    @Override
    public String toString() {
        return "Druh vyuky: " + druhVyuky;
    }

    public ZpusobVyuky(int idZpusobVyuky, String druhVyuky) {
        this.idZpusobVyuky = idZpusobVyuky;
        this.druhVyuky = druhVyuky;
    }

    public int getIdZpusobVyuky() {
        return idZpusobVyuky;
    }

    public void setIdZpusobVyuky(int idZpusobVyuky) {
        this.idZpusobVyuky = idZpusobVyuky;
    }

    public String getDruhVyuky() {
        return druhVyuky;
    }

    public void setDruhVyuky(String druhVyuky) {
        this.druhVyuky = druhVyuky;
    }
    
    
}
