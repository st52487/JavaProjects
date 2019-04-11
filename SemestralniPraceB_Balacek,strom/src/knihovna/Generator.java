package knihovna;

import lorem.Lorem;
import lorem.LoremIpsum;
import objekty.Firma;
import objekty.IFirma;
import objekty.IPobocka;
import objekty.IZamestnanci;
import objekty.Pobocka;
import objekty.Pozice;
import objekty.TypPozice;
import objekty.Zamestnanci;
import objekty.Zamestnanec;

public final class Generator {

    private static final Lorem lorem = new LoremIpsum();
    private static int indexZamestnanec = 1;

    private Generator() {

    }

    public static IZamestnanci generujZamestnance() {
        IZamestnanci zamestnanci = new Zamestnanci();
        for (int i = 1; i < 600; i++) {
            zamestnanci.vloz(i, new Zamestnanec(i, lorem.getFirstName(), lorem.getLastName(), lorem.getEmail()));
        }
        return zamestnanci;
    }

    public static IPobocka generujPobocku(IZamestnanci zamestnanci)
            throws IllegalAccessException {
        Pobocka pobocka = new Pobocka(lorem.getWords(1), lorem.getCity());
        Pozice pozice = new Pozice(TypPozice.REDITEL, zamestnanci.najdi(indexZamestnanec++));
        pobocka.vlozPoziciReditele(pozice);
        pobocka.zpristupniReditele();
        int pocetUseku = nahodneCislo(2, 3);
        for (int i = 1; i < pocetUseku; i++) {
            pozice = new Pozice(TypPozice.VEDOUCIPOBOCKY, zamestnanci.najdi(indexZamestnanec++));
            pobocka.vlozPozici(pozice);
            pobocka.zpristupniPodrizenouPozici(i + 1);
            int pocetOddeleni = nahodneCislo(2, 3);
            for (int j = 1; j < pocetOddeleni; j++) {
                pozice = new Pozice(TypPozice.VEDOUCIUSEKU, zamestnanci.najdi(indexZamestnanec++));
                pobocka.vlozPozici(pozice);
                pobocka.zpristupniPodrizenouPozici(j + 1);
                int pocetPracovniku = nahodneCislo(1, 2);
                for (int k = 0; k < pocetPracovniku; k++) {
                    pozice = new Pozice(TypPozice.PRACOVNIK, zamestnanci.najdi(indexZamestnanec++));
                    pobocka.vlozPozici(pozice);
                }
                pobocka.zpristupniNadrizenouPozici();
            }
        }
        return pobocka;
    }

    public static IFirma generujFirmu(IZamestnanci zamestnanci)
            throws IllegalAccessException {
        IFirma firma = new Firma();
        IPobocka pobocka;
        for (int i = 0; i < 4; i++) {
            pobocka = generujPobocku(zamestnanci);
            firma.vloz(pobocka.getNazevPobocky(), pobocka);
        }
        return firma;
    }

    private static int nahodneCislo(int min, int max) {
        return (int) (Math.round(Math.random()) * (max - min) + min);
    }

}
