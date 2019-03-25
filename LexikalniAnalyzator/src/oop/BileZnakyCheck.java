
package oop;


public class BileZnakyCheck extends Token{

    private int pocetBilychZnaku = 0;
    private NumbersCheck numbersCheck;
    
    public BileZnakyCheck(NumbersCheck numbersCheck) {
        super("Token");
        this.numbersCheck=numbersCheck;
    }

    public boolean bileZnaky(String s) throws Exception{
        for (int i = 0; i < BileZnaky.values().length; i++) {
            if (s.charAt(s.length() - 1) == BileZnaky.values()[i].getZn()) {
                if (s.length() > 1 && numbersCheck.getPocetCisel() !=0) {
                    System.out.println(toString("Prom", "Variable", s.substring(0, s.length() - 1)));
                    pocetBilychZnaku = 0;
                }
                if (pocetBilychZnaku == 0) {
                    System.out.println(toString("Sign", "Bílý znak", BileZnaky.values()[i].name()));
                    pocetBilychZnaku++;
                  
                }
                return true;
            }
        }
        return false;
    }
}
