package kolekce;

import kolekce.IAbstrTable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class AbstrTable<V, K> implements IAbstrTable<V, K> {

    private final IAbstrDoubleList<Prvek> SEZNAM;

    public AbstrTable() {
        SEZNAM = new AbstrDoubleList<Prvek>();
    }

    @Override
    public void zrus() {
        SEZNAM.zrus();
    }

    @Override
    public boolean jePrazdny() {
        return SEZNAM.jePrazdny();
    }

    @Override
    public V najdi(K key) throws NullPointerException {
        Objects.requireNonNull(key);
        V data = null;
        SEZNAM.zpristupniPrvni();
        try {
            while (!SEZNAM.zpristupniAktualni().klic.equals(key)) {
                SEZNAM.zpristupniNaslednika();
            }
            data = SEZNAM.zpristupniAktualni().data;

        } catch (IAbstrDoubleList.ListException ex) {
            System.out.println("chyba v najdi");
        }
        return data;
    }

    @Override
    public void vloz(K key, V value) throws NullPointerException {
        Objects.requireNonNull(value, "Nejsou data");
        Prvek prvek = new Prvek(key, value);
        SEZNAM.vlozPosledni(prvek);
    }

    @Override
    public V odeber(K key) throws NullPointerException {
        Objects.requireNonNull(key);
        SEZNAM.zpristupniPrvni();
        V data = null;
        try {
            while (!SEZNAM.zpristupniAktualni().klic.equals(key)) {
                SEZNAM.zpristupniNaslednika();
            }
            data = SEZNAM.odeberAktualni().data;

        } catch (IAbstrDoubleList.ListException ex) {
            System.out.println("chyba v odeber");
        }
        return data;

    }

    @Override
    public Iterator iterator() {
        return new Iterator<V>() {

            Iterator<Prvek> iterator = SEZNAM.iterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public V next() {
                if (hasNext()) {
                    return iterator.next().data;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    private class Prvek {

        private K klic;
        private V data;

        private Prvek(K klic, V data) {
            this.klic = klic;
            this.data = data;
        }
    }
}
