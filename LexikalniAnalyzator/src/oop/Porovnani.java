
package oop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Porovnani {
    Check check = new Check();

    public Porovnani() {
    }
    
    public void analyze(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            //BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\caval\\Desktop\\aha.txt"))) {
            String s = "";
            char znak;
            while ((znak = (char) br.read()) != (char) - 1) {
                s += znak;
                if (check.porovnej(s)) {
                    s = "";
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
