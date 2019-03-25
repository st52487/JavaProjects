package oop;

public class HexCislaCheck extends Token{
    
    private int cislo;

    public HexCislaCheck() {
        super("Token");
    }
    
    

    public boolean vypis(String s){
        for (int i = 0; i < HexDecCisla.values().length; i++) {
            if((char) HexDecCisla.values()[i].getZnak() == s.charAt(s.length() - 1)){
                
               
                
                return false;
            }
            
        }
        cislo = prevodHextoInt(s.substring(2, s.length()-1 ));
        System.out.println(toString("NumberToken = Hexadecimal", "value", "" + cislo));
        return true;
    }

    private int prevodHextoInt(String s) {

        int[] hexCisla = new int[s.length()];
        int vysledek = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < HexDecCisla.values().length; j++) {
                if (s.charAt(i) == HexDecCisla.values()[j].getZnak()) {
                    hexCisla[i] = HexDecCisla.values()[j].ordinal();
                }
            }
        }

        for (int i = 0; i < hexCisla.length; i++) {
            vysledek += hexCisla[hexCisla.length - 1 - i] * Math.pow(16, i);
        }
        return vysledek;
    }

}
