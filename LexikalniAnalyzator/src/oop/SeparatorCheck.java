
package oop;


public class SeparatorCheck extends Token {

    public SeparatorCheck() {
        super("Token");
    }

    public boolean separator(String s) throws Exception{
        for (int i = 0; i < Chars.values().length; i++) {
            if (s.charAt(0) == Chars.values()[i].getChars()) {
                System.out.println(toString("SeparatorToken", "Separator", Chars.values()[i].name()));
                return true;
            }

        }
        return false;
    }
}
