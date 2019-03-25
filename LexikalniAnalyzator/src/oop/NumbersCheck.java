
package oop;

public class NumbersCheck extends Token {

    private static int pocetCisel = 0;

    public NumbersCheck() {
        super("Token");
    }

    public boolean cisla(String s) throws Exception {
        for (int i = 0; i < Numbers.values().length; i++) {
            if ((char) (Numbers.values()[i].getCislo() + 48) == s.charAt(s.length() - 1)) {
                pocetCisel++;
                return true;
            }
        }
        

        if (pocetCisel != 0) {
            System.out.println(toString("NumberToken", "value", s.substring(0, s.length() - 1)));
            pocetCisel = 0;
        }
        return false;
    }

    public int getPocetCisel() {
        return pocetCisel;
    }
}
