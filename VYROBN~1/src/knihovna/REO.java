package knihovna;

import kolekce.IAbstrDoubleList;
import procesy.IProces;
import procesy.Manualni;
import vycty.TypProcesu;
import zpracovani.AbstrLifo;
import zpracovani.IAbstrLifo;
import zpracovani.IVyrobniProces;
import zpracovani.VyrobniProces;

public final class REO {

    private REO() {

    }

    public static void nahazejDoZasobnikuAgregace(IAbstrDoubleList<IProces> SEZNAM, int kriterium) throws IAbstrDoubleList.ListException {
        final IAbstrLifo<IProces> LIFO = AbstrLifo.getInstance();
        final IVyrobniProces VyrProces = VyrobniProces.getInstance();
        int index = 0;
        int pocet = VyrProces.vratPocetPolozek();
        int posobeJdouci = 0;
        SEZNAM.zpristupniPrvni();
        while (SEZNAM.zpristupniAktualni() != null) {
            if (SEZNAM.zpristupniAktualni().getTypProcesu().equals(TypProcesu.MANUALNI)
                    && SEZNAM.zpristupniAktualni().getCasProcesu() < kriterium) {
                posobeJdouci++;
                LIFO.vloz(SEZNAM.zpristupniAktualni());
                if (posobeJdouci == 2) {
                    posobeJdouci = 0;
                }
            } else {
                if (posobeJdouci == 1) {
                    posobeJdouci = 0;
                    LIFO.odeber();
                }
            }
            index++;
            if (index == pocet) {
                break;
            }
            SEZNAM.zpristupniNaslednika();
        }
    }

    public static int agreguj(IAbstrDoubleList<IProces> SEZNAM) throws IAbstrDoubleList.ListException {
        final IAbstrLifo LIFO = AbstrLifo.getInstance();
        final IVyrobniProces VyrProces = VyrobniProces.getInstance();
        int pocet = 0;
        while (LIFO.jePrazdny() != true) {
            pocet++;
            IProces proces = (IProces) LIFO.odeber();
            IProces proces2 = (IProces) LIFO.odeber();
            VyrProces.zrusProces(proces);
            VyrProces.zrusProces(proces2);

            int casProcesu = proces.getCasProcesu();
            int casProcesu2 = proces2.getCasProcesu();
            int pocetOsob = ((Manualni) proces).getPocetOsob();
            int pocetOsob2 =((Manualni) proces2).getPocetOsob();

            SEZNAM.vlozPrvni(new Manualni(proces.getId() + "1", casProcesu + casProcesu2, pocetOsob + casProcesu2));
        }
        LIFO.zrus();
        return pocet;
    }

    public static int dekompozicuj(IAbstrDoubleList<IProces> SEZNAM) throws IAbstrDoubleList.ListException {
        final IAbstrLifo LIFO = AbstrLifo.getInstance();
        final IVyrobniProces VyrProces = VyrobniProces.getInstance();
        int pocet = 0;
        while (LIFO.jePrazdny() != true) {
            pocet++;
            IProces proces = (IProces) LIFO.odeber();
            VyrProces.zrusProces(proces);

            int casProcesu = (int) proces.getCasProcesu() / 2;
            int lichyProces = proces.getCasProcesu() % 2;
            int pocetOsob = (int) ((Manualni) proces).getPocetOsob() / 2;
            int lichyOsoby = (int) ((Manualni) proces).getPocetOsob() % 2;

            SEZNAM.vlozPrvni(new Manualni(proces.getId() + "1", casProcesu + lichyProces, pocetOsob + lichyOsoby));
            SEZNAM.vlozPrvni(new Manualni(proces.getId() + "2", casProcesu, pocetOsob));
        }
        LIFO.zrus();
        return pocet;
    }

    public static void nahazejDoZasobnikuDekompozice(IAbstrDoubleList<IProces> SEZNAM, int hodnota) throws IAbstrDoubleList.ListException {
        final IAbstrLifo LIFO = AbstrLifo.getInstance();
        SEZNAM.zpristupniPrvni();
        final IVyrobniProces VyrProces = VyrobniProces.getInstance();
        int pocet = VyrProces.vratPocetPolozek();
        int index = 0;
        while (SEZNAM.zpristupniAktualni() != null) {
            if (SEZNAM.zpristupniAktualni().getTypProcesu().equals(TypProcesu.MANUALNI)
                    && SEZNAM.zpristupniAktualni().getCasProcesu() > hodnota) {
                LIFO.vloz(SEZNAM.zpristupniAktualni());
            }
            index++;
            if (index == pocet) {
                break;
            }
            SEZNAM.zpristupniNaslednika();
        }
    }

}
