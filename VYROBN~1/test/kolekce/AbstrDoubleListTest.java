package kolekce;


import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class AbstrDoubleListTest {

    private class Polozka {
        int hodnota;

        Polozka(int hodnota) {
            this.hodnota = hodnota;
        }

        @Override
        public String toString() {
            return hodnota + "";
        }
    }

    @Test
    public void zrus() {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>();

        assertTrue(list.jePrazdny());
        list.vlozPrvni(new Polozka(1));
        assertFalse(list.jePrazdny());
        list.zrus();
        assertTrue(list.jePrazdny());
    }

    @Test
    public void jePrazdny() {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        assertTrue(list.jePrazdny());
        list.vlozPrvni(new Polozka(1));
        assertFalse(list.jePrazdny());
    }

    @Test
    public void vlozPrvni_01() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        list.vlozPrvni(new Polozka(1));
        assertEquals(1, list.zpristupniPrvni().hodnota);
        assertEquals(1, list.zpristupniNaslednika().hodnota);
        assertEquals(1, list.zpristupniPredchudce().hodnota);
        assertEquals(1, list.zpristupniPosledni().hodnota);
    }

    @Test
    public void vlozPrvni_02() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        for (int i = 1; i <= 5; i++) {
            list.vlozPrvni(new Polozka(i));
        }
        assertEquals(5, list.zpristupniPrvni().hodnota);
        assertEquals(1, list.zpristupniPredchudce().hodnota);
        assertEquals(5, list.zpristupniNaslednika().hodnota);
        assertEquals(4, list.zpristupniNaslednika().hodnota);
        assertEquals(1, list.zpristupniPosledni().hodnota);

    }

    @Test(expected = NullPointerException.class)
    public void vlozPrvni_03() {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        list.vlozPrvni(null);
        fail();
    }

    @Test
    public void vlozPosledni_01() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        list.vlozPosledni(new Polozka(1));
        assertEquals(1, list.zpristupniPosledni().hodnota);
        assertEquals(1, list.zpristupniNaslednika().hodnota);
        assertEquals(1, list.zpristupniPredchudce().hodnota);
        assertEquals(1, list.zpristupniPrvni().hodnota);
    }

    @Test
    public void vlozPosledni_02() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        for (int i = 1; i <= 5; i++) {
            list.vlozPosledni(new Polozka(i));
        }

        assertEquals(1, list.zpristupniPrvni().hodnota);
        assertEquals(5, list.zpristupniPredchudce().hodnota);
        assertEquals(1, list.zpristupniNaslednika().hodnota);
        assertEquals(2, list.zpristupniNaslednika().hodnota);
        assertEquals(5, list.zpristupniPosledni().hodnota);
    }

    @Test(expected = NullPointerException.class)
    public void vlozPosledni_03() {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        list.vlozPosledni(null);
        fail();
    }

    @Test
    public void vlozNaslednika_02() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        for (int i = 1; i <= 5; i++) {
            list.vlozPosledni(new Polozka(i));
        }

        list.zpristupniPosledni();
        list.vlozNaslednika(new Polozka(6));

        assertEquals(5, list.zpristupniAktualni().hodnota);
        assertEquals(6, list.zpristupniNaslednika().hodnota);
        assertEquals(1, list.zpristupniNaslednika().hodnota);
        assertEquals(6, list.zpristupniPredchudce().hodnota);
        assertEquals(5, list.zpristupniPredchudce().hodnota);
        assertEquals(6, list.zpristupniPosledni().hodnota);
    }

    @Test
    public void vlozNaslednika_03() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        list.vlozPrvni(new Polozka(1));
        list.vlozPosledni(new Polozka(3));
        list.zpristupniPrvni();
        list.vlozNaslednika(new Polozka(2));

        assertEquals(1, list.zpristupniAktualni().hodnota);
        assertEquals(2, list.zpristupniNaslednika().hodnota);
        assertEquals(3, list.zpristupniNaslednika().hodnota);
        assertEquals(2, list.zpristupniPredchudce().hodnota);
        assertEquals(1, list.zpristupniPredchudce().hodnota);
        assertEquals(3, list.zpristupniPosledni().hodnota);
        assertEquals(1, list.zpristupniPrvni().hodnota);
    }

    @Test(expected = NullPointerException.class)
    public void vlozNaslednika_04() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        list.vlozPrvni(new Polozka(1));
        list.zpristupniPrvni();
        list.vlozNaslednika(null);
        fail();
    }

    @Test(expected = IAbstrDoubleList.ListException.class)
    public void vlozNaslednika_05() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        list.vlozPrvni(new Polozka(1));
        list.vlozNaslednika(new Polozka(1));
        fail();
    }

    @Test
    public void vlozPredchudce_02() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        for (int i = 1; i <= 5; i++) {
            list.vlozPosledni(new Polozka(i));
        }

        list.zpristupniPrvni();
        list.vlozPredchudce(new Polozka(0));

        assertEquals(1, list.zpristupniAktualni().hodnota);
        assertEquals(0, list.zpristupniPredchudce().hodnota);
        assertEquals(5, list.zpristupniPredchudce().hodnota);
        assertEquals(0, list.zpristupniNaslednika().hodnota);
        assertEquals(1, list.zpristupniNaslednika().hodnota);
        assertEquals(0, list.zpristupniPrvni().hodnota);
        assertEquals(5, list.zpristupniPosledni().hodnota);
    }

    @Test
    public void vlozPredchudce_03() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        list.vlozPrvni(new Polozka(1));
        list.vlozPosledni(new Polozka(3));
        list.zpristupniPosledni();
        list.vlozPredchudce(new Polozka(2));

        assertEquals(3, list.zpristupniAktualni().hodnota);
        assertEquals(2, list.zpristupniPredchudce().hodnota);
        assertEquals(1, list.zpristupniPredchudce().hodnota);
        assertEquals(2, list.zpristupniNaslednika().hodnota);
        assertEquals(3, list.zpristupniNaslednika().hodnota);
        assertEquals(3, list.zpristupniPosledni().hodnota);
        assertEquals(1, list.zpristupniPrvni().hodnota);
    }

    @Test(expected = NullPointerException.class)
    public void vlozPredchudce_04() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        list.vlozPrvni(new Polozka(1));
        list.zpristupniPrvni();
        list.vlozPredchudce(null);
        fail();
    }

    @Test(expected = IAbstrDoubleList.ListException.class)
    public void vlozPredchudce_05() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        list.vlozPrvni(new Polozka(1));
        list.vlozPredchudce(new Polozka(1));
        fail();
    }

    @Test
    public void zpristupniAktualni_01() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        for (int i = 1; i <= 5; i++) {
            list.vlozPosledni(new Polozka(i));
        }

        list.zpristupniPrvni();
        assertEquals(1, list.zpristupniAktualni().hodnota);
    }

    @Test(expected = NoSuchElementException.class)
    public void zpristupniAktualni_02() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        list.zpristupniAktualni();
        fail();
    }

    @Test(expected = IAbstrDoubleList.ListException.class)
    public void zpristupniAktualni_03() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        for (int i = 1; i <= 5; i++) {
            list.vlozPosledni(new Polozka(i));
        }

        list.zpristupniAktualni();
        fail();
    }

    @Test
    public void zpristupniPrvni_01() {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        for (int i = 1; i <= 5; i++) {
            list.vlozPosledni(new Polozka(i));
        }

        assertEquals(1, list.zpristupniPrvni().hodnota);
    }

    @Test(expected = NoSuchElementException.class)
    public void zpristupniPrvni_02() {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        list.zpristupniPrvni();
        fail();
    }

    @Test
    public void zpristupniPosledni_01() {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        for (int i = 1; i <= 5; i++) {
            list.vlozPosledni(new Polozka(i));
        }

        assertEquals(5, list.zpristupniPosledni().hodnota);
    }

    @Test(expected = NoSuchElementException.class)
    public void zpristupniPosledni_02() {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        list.zpristupniPrvni();
        fail();
    }

    @Test
    public void zpristupniNaslednika_01() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        for (int i = 1; i <= 5; i++) {
            list.vlozPosledni(new Polozka(i));
        }

        list.zpristupniPrvni();

        assertEquals(2, list.zpristupniNaslednika().hodnota);
        assertEquals(2, list.zpristupniAktualni().hodnota);
    }

    @Test(expected = IAbstrDoubleList.ListException.class)
    public void zpristupniNaslednika_02() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        for (int i = 1; i <= 5; i++) {
            list.vlozPosledni(new Polozka(i));
        }

        list.zpristupniNaslednika();
        fail();
    }

    @Test
    public void zpristupniPredchudce_01() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        for (int i = 1; i <= 5; i++) {
            list.vlozPosledni(new Polozka(i));
        }

        list.zpristupniPrvni();

        assertEquals(5, list.zpristupniPredchudce().hodnota);
        assertEquals(5, list.zpristupniAktualni().hodnota);
    }

    @Test(expected = IAbstrDoubleList.ListException.class)
    public void zpristupniPredchudce_02() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        for (int i = 1; i <= 5; i++) {
            list.vlozPosledni(new Polozka(i));
        }

        list.zpristupniPredchudce();
        fail();
    }

    @Test
    public void odeberAktualni_01() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        list.vlozPrvni(new Polozka(1));
        list.zpristupniPrvni();

        assertEquals(1, list.odeberAktualni().hodnota);
        assertTrue(list.jePrazdny());
    }

    @Test
    public void odeberAktualni_02() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        for (int i = 1; i <= 5; i++) {
            list.vlozPosledni(new Polozka(i));
        }

        list.zpristupniPrvni();

        assertEquals(1, list.odeberAktualni().hodnota);
        assertEquals(2, list.zpristupniAktualni().hodnota);
        assertEquals(2, list.zpristupniPrvni().hodnota);
        assertEquals(5, list.zpristupniPredchudce().hodnota);
        assertEquals(2, list.zpristupniNaslednika().hodnota);
        assertEquals(3, list.zpristupniNaslednika().hodnota);
        assertEquals(2, list.zpristupniPredchudce().hodnota);
    }

    @Test
    public void odeberAktualni_03() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        for (int i = 1; i <= 5; i++) {
            list.vlozPosledni(new Polozka(i));
        }

        list.zpristupniPosledni();

        assertEquals(5, list.odeberAktualni().hodnota);
        assertEquals(1, list.zpristupniAktualni().hodnota);
        assertEquals(4, list.zpristupniPosledni().hodnota);
        assertEquals(1, list.zpristupniNaslednika().hodnota);
        assertEquals(4, list.zpristupniPredchudce().hodnota);
        assertEquals(3, list.zpristupniPredchudce().hodnota);
        assertEquals(4, list.zpristupniNaslednika().hodnota);
    }

    @Test
    public void odeberAktualni_04() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        for (int i = 1; i <= 5; i++) {
            list.vlozPosledni(new Polozka(i));
        }

        list.zpristupniPrvni();
        list.zpristupniNaslednika();

        assertEquals(2, list.odeberAktualni().hodnota);
        assertEquals(1, list.zpristupniAktualni().hodnota);
        assertEquals(3, list.zpristupniNaslednika().hodnota);
        assertEquals(1, list.zpristupniPredchudce().hodnota);
    }

    @Test(expected = NoSuchElementException.class)
    public void odeberAktualni_05() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        list.odeberAktualni();
        fail();
    }

    @Test(expected = IAbstrDoubleList.ListException.class)
    public void odeberAktualni_06() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        for (int i = 1; i <= 5; i++) {
            list.vlozPosledni(new Polozka(i));
        }

        list.odeberAktualni();
        fail();
    }

    @Test
    public void odeberPrvni_01() {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        list.vlozPrvni(new Polozka(1));

        assertEquals(1, list.odeberPrvni().hodnota);
        assertTrue(list.jePrazdny());
    }

    @Test
    public void odeberPrvni_02() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        for (int i = 1; i <= 5; i++) {
            list.vlozPosledni(new Polozka(i));
        }

        list.zpristupniPrvni();

        assertEquals(1, list.odeberPrvni().hodnota);
        assertEquals(2, list.zpristupniAktualni().hodnota);
        assertEquals(5, list.zpristupniPredchudce().hodnota);
        assertEquals(2, list.zpristupniNaslednika().hodnota);
        assertEquals(3, list.zpristupniNaslednika().hodnota);
        assertEquals(2, list.zpristupniPredchudce().hodnota);
        assertEquals(2, list.zpristupniPrvni().hodnota);
    }

    @Test(expected = NoSuchElementException.class)
    public void odeberPrvni_03() {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        list.odeberPrvni();
        fail();
    }

    @Test
    public void odeberPosledni_01() {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        list.vlozPrvni(new Polozka(1));

        assertEquals(1, list.odeberPosledni().hodnota);
        assertTrue(list.jePrazdny());
    }

    @Test
    public void odeberPosledni_02() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        for (int i = 1; i <= 5; i++) {
            list.vlozPosledni(new Polozka(i));
        }

        list.zpristupniPosledni();

        assertEquals(5, list.odeberPosledni().hodnota);
        assertEquals(1, list.zpristupniAktualni().hodnota);
        assertEquals(2, list.zpristupniNaslednika().hodnota);
        assertEquals(1, list.zpristupniPredchudce().hodnota);
        assertEquals(4, list.zpristupniPredchudce().hodnota);
        assertEquals(1, list.zpristupniNaslednika().hodnota);
        assertEquals(4, list.zpristupniPosledni().hodnota);
    }

    @Test(expected = NoSuchElementException.class)
    public void odeberPosledni_03() {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        list.odeberPosledni();
        fail();
    }

    @Test
    public void odeberNaslednika_01() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        list.vlozPrvni(new Polozka(1));
        list.zpristupniPrvni();

        assertEquals(1, list.odeberNaslednika().hodnota);
        assertTrue(list.jePrazdny());
    }

    @Test
    public void odeberNaslednika_02() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        for (int i = 1; i <= 5; i++) {
            list.vlozPosledni(new Polozka(i));
        }

        list.zpristupniPrvni();

        assertEquals(2, list.odeberNaslednika().hodnota);
        assertEquals(1, list.zpristupniAktualni().hodnota);
        assertEquals(3, list.zpristupniNaslednika().hodnota);
        assertEquals(1, list.zpristupniPredchudce().hodnota);
    }

    @Test(expected = NoSuchElementException.class)
    public void odeberNaslednika_03() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        list.odeberNaslednika();
        fail();
    }

    @Test(expected = IAbstrDoubleList.ListException.class)
    public void odeberNaslednika_04() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        for (int i = 1; i <= 5; i++) {
            list.vlozPosledni(new Polozka(i));
        }

        list.odeberNaslednika();
        fail();
    }

    @Test
    public void odeberPredchudce_01() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        list.vlozPrvni(new Polozka(1));
        list.zpristupniPrvni();

        assertEquals(1, list.odeberPredchudce().hodnota);
        assertTrue(list.jePrazdny());
    }

    @Test
    public void odeberPredchudce_02() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        for (int i = 1; i <= 5; i++) {
            list.vlozPosledni(new Polozka(i));
        }

        list.zpristupniPrvni();

        assertEquals(5, list.odeberPredchudce().hodnota);
        assertEquals(1, list.zpristupniAktualni().hodnota);
        assertEquals(4, list.zpristupniPredchudce().hodnota);
        assertEquals(1, list.zpristupniNaslednika().hodnota);
        assertEquals(4, list.zpristupniPosledni().hodnota);
    }

    @Test(expected = NoSuchElementException.class)
    public void odeberPredchudce_03() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        list.odeberPredchudce();
        fail();
    }

    @Test(expected = IAbstrDoubleList.ListException.class)
    public void odeberPredchudce_04() throws IAbstrDoubleList.ListException {
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        for (int i = 1; i <= 5; i++) {
            list.vlozPosledni(new Polozka(i));
        }

        list.odeberNaslednika();
        fail();
    }

    @Test
    public void iterator_01() {
        Log.write("Vytvoření instance třídy AbstrDoubleList;");
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        Log.write("Vkládání položek do seznamu na poslední místo;");
        for (int i = 1; i <= 5; i++) {
            Polozka polozka = new Polozka(i * 10);
            list.vlozPosledni(polozka);
            Log.write("Položka %d: hodnota: %d", i, polozka.hodnota);
        }

        Iterator<Polozka> iterator = list.iterator();
        for (int i = 0; i < 5; i++) {
            assertTrue(iterator.hasNext());
            Log.write("%s", iterator.next());
        }
        assertFalse(iterator.hasNext());
    }

    @Test (expected = NoSuchElementException.class)
    public void iterator_02() {
        Log.write("Vytvoření instance třídy AbstrDoubleList;");
        IAbstrDoubleList<Polozka> list = new AbstrDoubleList<>() ;

        Log.write("Vkládání položek do seznamu na poslední místo;");
        for (int i = 1; i <= 5; i++) {
            Polozka polozka = new Polozka(i * 10);
            list.vlozPosledni(polozka);
            Log.write("Položka %d: hodnota: %d", i, polozka.hodnota);
        }
        Log.write("Vkládání položek dokončeno;");

        Log.write("Testování iterátoru;");
        Iterator<Polozka> iterator = list.iterator();
        for (int i = 1; i <= 5; i++) {
            assertTrue(iterator.hasNext());
            Log.write("Polozka %d, hodnota: %d", i, iterator.next().hodnota);
        }
        assertFalse(iterator.hasNext());
        iterator.next();
        fail();
    }
}