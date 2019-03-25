
package oop;


public class KeyWordsCheck extends Token{

    public KeyWordsCheck() {
        super("Token");
    }

    public boolean klicovaSlova(String s) throws Exception{
        for (int i = 0; i < KeyWords.values().length; i++) {
            if (KeyWords.values()[i].name().equals(s)) {

                System.out.println(toString("KeyToken", "KeyWord", KeyWords.values()[i].name()));
                return true;
            }
        }
        return false;
    }

}
