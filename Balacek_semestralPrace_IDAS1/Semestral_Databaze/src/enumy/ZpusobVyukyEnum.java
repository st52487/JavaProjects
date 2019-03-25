/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enumy;


public enum ZpusobVyukyEnum {

    PREDNASKA("Přednáška"), CVICENI("Cvičení"), SEMINAR("Seminář");

    private String nazev;

    private ZpusobVyukyEnum(String nazev) {
        this.nazev = nazev;
    }

    @Override
    public String toString() {
        return nazev;
    }

}
