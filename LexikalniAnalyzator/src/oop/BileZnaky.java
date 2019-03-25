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
public enum BileZnaky {
    
    enter('\n'), mezera(' '), tab('\t'), newLine('\r');
    
    private char zn;
    

    private BileZnaky(char zn) {
        this.zn = zn;
    }

    public char getZn() {
        return zn;
    }
}
