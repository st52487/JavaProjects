/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

/**
 *
 * @author caval
 */
public enum Numbers {
    zero(0),one(1), two(2), three(3), four(4), five(5), six(6), seven(7), eight(8), nine(9);
    
    private int cislo;

    private Numbers(int cislo) {
        this.cislo = cislo;
    }

    public int getCislo() {
        return cislo;
    }
   
}
