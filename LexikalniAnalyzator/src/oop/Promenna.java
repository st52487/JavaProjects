package oop;


public class Promenna {

    public Promenna() {
    }

    public boolean velkePismeno(String s) throws Exception {
        for (int a = 0; a < Alphabet.values().length; a++) {
            if (Alphabet.values()[a].name().equals(s)) {
                System.out.println("Error - {Retezec obsahuje velke pismeno = " + Alphabet.values()[a].name() + "}");
                return true;
            }
        }
        return false;
    }
}
