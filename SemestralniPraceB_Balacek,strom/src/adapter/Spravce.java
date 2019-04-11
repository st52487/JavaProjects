package adapter;

import objekty.Firma;
import java.util.Iterator;
import knihovna.Alerts;
import static knihovna.Generator.generujFirmu;
import static knihovna.Generator.generujZamestnance;
import objekty.IFirma;
import objekty.IPobocka;
import objekty.IZamestnanci;
import objekty.Zamestnanci;
import objekty.Zamestnanec;
import objekty.Pozice;
import objekty.TypPozice;

public class Spravce {

    private IFirma FIRMA;
    private static Spravce spravce;
    private IZamestnanci ZAMESTNANCI;
    private int id = 1;

    private Spravce() {
        FIRMA = new Firma();
        ZAMESTNANCI = new Zamestnanci();
    }

    public void pridejZamestnance(String jmeno, String prijmeni, String email) {
        ZAMESTNANCI.vloz(id, new Zamestnanec(id, jmeno, prijmeni, email));
        id++;
    }

    public Iterator<IPobocka> iteratorPobocka() {
        return FIRMA.iterator();
    }

    public Iterator<Zamestnanec> iteratorZamestnanci() {
        return ZAMESTNANCI.iterator();
    }

    public Iterator<Pozice> iteratorPozice(IPobocka pobocka) {
        return pobocka.iterator();
    }

    public void generujData() {
        id = 600;
        ZAMESTNANCI = generujZamestnance();
        try {
            FIRMA = generujFirmu(ZAMESTNANCI);
        } catch (IllegalAccessException ex) {
            Alerts.showErrorAlert("Chyba", "Nevytvořil se správce");
        }
    }

    public void generujZamest() {
        id = 600;
        ZAMESTNANCI = generujZamestnance();
    }

    public IFirma getFirma() {
        return FIRMA;
    }

    public void odeberPobocku(String nazev) {
        FIRMA.odeber(nazev);
    }

    public static Spravce getInstance() {
        if (spravce == null) {
            try {
                spravce = new Spravce();
            } catch (NullPointerException ex) {
                Alerts.showErrorAlert("Chyba", "Vytvoření správce se nezdařilo");
            }
        }
        return spravce;
    }

    public void pridejNovehoPotomka(TypPozice typPozice, Zamestnanec zamestnanec, IPobocka pobocka, Pozice pozice) {
        try {
            Pozice nova = new Pozice(typPozice, zamestnanec);
            pobocka.zpristupniPoziciReditele();
            int index = 1;
            switch (nova.getTypPozice()) {
                case VEDOUCIPOBOCKY:
                    pobocka.vlozPozici(nova);
                    break;
                case VEDOUCIUSEKU:
                    for (Iterator it1 = pobocka.iteratorSynu(); it1.hasNext();) {
                        Pozice syn = (Pozice) it1.next();
                        if (syn.getZamestnanec().getId() == pozice.getZamestnanec().getId()) {
                            break;
                        }
                        index++;
                    }
                    pobocka.zpristupniPodrizenouPozici(index);
                    pobocka.vlozPozici(nova);
                    break;
                case PRACOVNIK:
                    boolean konec = false;
                    int index1 = 1;
                    for (Iterator<Pozice> it2 = pobocka.iteratorSynu(); it2.hasNext();) {
                        it2.next();
                        if (konec == true) {
                            break;
                        }
                        index = 1;
                        pobocka.zpristupniPodrizenouPozici(index1);

                        for (Iterator<Pozice> it3 = pobocka.iteratorSynu(); it3.hasNext();) {
                            Pozice syn = it3.next();
                            if (syn.getZamestnanec().getId() == pozice.getZamestnanec().getId()) {
                                konec = true;
                                break;
                            }
                            index++;
                        }
                        if (index != 1) {
                            pobocka.zpristupniNadrizenou();
                        }
                        index1++;
                    }
                    pobocka.zpristupniPodrizenouPozici(index);
                    pobocka.vlozPozici(nova);
                    break;
            }
        } catch (Exception ex) {
            Alerts.showErrorAlert("Chyba", "Nepodařilo se přidat");
        }
    }

    public void vlozRiditele(Pozice pozice, IPobocka pobocka) {
        pobocka.vlozPoziciReditele(pozice);
    }

    public void zrusPozice(IPobocka pobocka) {
        pobocka.zrus();
    }
}
