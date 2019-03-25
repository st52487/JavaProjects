
package oop;

public enum Chars {
    strednik(';'), rovnase('='), tecka('.'), carka(','), dvojtecka(':');
    
    private char chars;
    

    private Chars(char chars) {
        this.chars = chars;
    }

    public char getChars() {
        return chars;
    }
}
