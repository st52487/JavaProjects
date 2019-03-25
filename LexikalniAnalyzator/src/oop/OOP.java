/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.io.File;


public class OOP {

    
    public static void main(String[] args) {

        File file = null;
        Porovnani porrovnani = new Porovnani();
        
        if(args.length > 0){
            file = new File(args[0]);
        }
   
        porrovnani.analyze(file);
        
    }
    
}
