package oop;


public class Check {

    NumbersCheck numbersCheck;
    KeyWordsCheck keyWordsCheck;
    SeparatorCheck separatorCheck;
    BileZnakyCheck bileZnakyCheck;
    Promenna promena;
    HexCislaCheck hexCislaCheck;

    Check() {
        numbersCheck = new NumbersCheck();
        keyWordsCheck = new KeyWordsCheck();
        separatorCheck = new SeparatorCheck();
        bileZnakyCheck = new BileZnakyCheck(numbersCheck);
        promena = new Promenna();
        hexCislaCheck = new HexCislaCheck();
    }

    public boolean porovnej(String s) throws Exception {
        if (s.length() < 32) {

            if (promena.velkePismeno(s)) {
                return true;
            }
            if (s.length() > 2 && s.charAt(0) == '0' && s.charAt(1) == 'x') {

                return hexCislaCheck.vypis(s);

            } else if (s.charAt(0) == '0') {
                return false;
            } else if (numbersCheck.cisla(s)) {
                return false;
            } else if (!numbersCheck.cisla(s) && (keyWordsCheck.klicovaSlova(s)
                    || separatorCheck.separator(s) || bileZnakyCheck.bileZnaky(s))) {

                return true;
            }

        } else {
            throw new Exception("Error - {Retezec je moc velky}");
        }

        return false;
    }

}
