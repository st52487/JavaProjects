package kolekce;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstrTree<E> implements IAbstrTree<E> {

    private Uzel koren, aktualni;
    private int mohutnost;

    private class Uzel {

        IAbstrDoubleList<Uzel> potomci;
        Uzel rodic;
        E data;

        public Uzel(Uzel rodic, E data) {
            potomci = new AbstrDoubleList<>();
            this.rodic = rodic;
            this.data = data;
        }
    }

    @Override
    public void zrus() {
        mohutnost = 0;
        this.aktualni = null;
        this.koren = null;
    }

    @Override
    public boolean jePrazdny() {
        return mohutnost == 0;
    }

    @Override
    public int mohutnost() {
        return mohutnost;
    }

    @Override
    public void vlozKoren(E data) throws NullPointerException {
        Objects.requireNonNull(data);
        if (mohutnost == 0) {
            this.koren = new Uzel(null, data);
            mohutnost++;
        }
    }

    @Override
    public void vlozList(E data) throws NullPointerException, NoSuchElementException {
        Objects.requireNonNull(data);
        if (aktualni == null) {
            throw new NoSuchElementException();
        }
        if (koren != null) {
            aktualni.potomci.vlozPosledni(new Uzel(aktualni, data));
        }
        mohutnost++;
    }

    @Override
    public E odeberKoren() throws NoSuchElementException, NullPointerException {
        if (!jePrazdny()) {
            if (koren.potomci.jePrazdny()) {
                E data = koren.data;
                zrus();
                return data;
            } else {
                throw new NullPointerException();
            }
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public E odeberList(int poradi) throws NoSuchElementException, NullPointerException {
        E data = null;
        if (poradi == 0) {
            throw new NoSuchElementException();
        } else if (aktualni == null) {
            throw new NullPointerException();
        } else {
            int pocitadlo = 1;
            Iterator it = aktualni.potomci.iterator();
            try {
                while (it.hasNext()) {
                    it.next();
                    if (pocitadlo == 1) {
                        aktualni.potomci.zpristupniPrvni();
                    } else {
                        aktualni.potomci.zpristupniNaslednika();
                    }
                    if (poradi == pocitadlo) {
                        data = aktualni.potomci.odeberAktualni().data;
                        break;
                    }
                    pocitadlo++;
                }
            } catch (IAbstrDoubleList.ListException ex) {
                System.out.println("chyba u odebirani listu");
            }
        }
        return data;
    }

    @Override
    public E zpristupniKoren() throws NullPointerException {
        if (koren == null) {
            throw new NullPointerException();
        }
        aktualni = koren;
        return aktualni.data;
    }

    @Override
    public E zpristupniSyna(int poradi) throws NullPointerException, NoSuchElementException {
        E data = null;
        if (!jePrazdny()) {
            if (poradi > 0) {
                if (aktualni != null) {
                    int pocitadlo = 1;
                    Iterator it = aktualni.potomci.iterator();
                    try {
                        while (it.hasNext()) {
                            it.next();
                            if (pocitadlo == 1) {
                                aktualni.potomci.zpristupniPrvni();
                                if (poradi == pocitadlo) {
                                    aktualni = aktualni.potomci.zpristupniAktualni();
                                    data = aktualni.data;
                                    break;
                                }
                                pocitadlo++;
                            } else {
                                aktualni.potomci.zpristupniNaslednika();
                                if (poradi == pocitadlo) {
                                    aktualni = aktualni.potomci.zpristupniAktualni();
                                    data = aktualni.data;
                                    break;
                                }
                                pocitadlo++;
                            }

                        }
                    } catch (IAbstrDoubleList.ListException ex) {
                    }
                } else {
                    throw new NoSuchElementException();
                }
            } else {
                throw new NullPointerException();
            }
        }
        return data;
    }

    @Override
    public E zpristupniOtce() throws NullPointerException {
        if (aktualni == null) {
            throw new NullPointerException();
        }
        aktualni = aktualni.rodic;
        return aktualni.data;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            IAbstrLifo<Uzel> lifo = new AbstrLifo<>();
            boolean prosel = false;

            @Override
            public boolean hasNext() {
                if (prosel == false) {
                    lifo.vloz(koren);
                    prosel = true;
                } else if (lifo.jePrazdny()) {
                    return false;
                }
                return true;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    Uzel uzel = lifo.odeber();
                    Iterator<Uzel> it = uzel.potomci.iterator();
                    while (it.hasNext()) {
                        lifo.vloz(it.next());
                    }
                    return uzel.data;
                }
                throw new NoSuchElementException();
            }
        };
    }

    public Iterator<E> iteratorSynu() {
        return new Iterator<E>() {
            Uzel index = aktualni.potomci.zpristupniPrvni();
            Uzel pomocny = null;

            @Override
            public boolean hasNext() {
                if (index.potomci.jePrazdny()) {
                    return false;
                }
                return index != pomocny;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E data = index.data;
                    if (pomocny == null) {
                        pomocny = index;
                    }
                    try {
                        index = aktualni.potomci.zpristupniNaslednika();
                    } catch (NoSuchElementException ex) {
                        Logger.getLogger(AbstrTree.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IAbstrDoubleList.ListException ex) {
                        Logger.getLogger(AbstrTree.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return data;
                }
                throw new NoSuchElementException();
            }
        };
    }
}
