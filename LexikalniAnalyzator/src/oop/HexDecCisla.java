
package oop;

public enum HexDecCisla {

    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUT(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    A(10),
    B(11),
    C(12),
    D(13),
    E(14),
    F(15);

    private int cislo;

    private HexDecCisla(int cislo) {
        this.cislo = cislo;
    }

    public char getZnak() {
        if (cislo < 10) {
            return (char) (cislo + 48);
        } else {
            return (char) (cislo + 87);
        }
    }
}
