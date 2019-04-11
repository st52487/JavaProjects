
import java.util.Iterator;
import java.util.NoSuchElementException;
import kolekce.AbstrTable;
import kolekce.IAbstrTable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestTable {

    public TestTable() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testJePrazdny() {
        IAbstrTable<Prvek, Integer> table = new AbstrTable();

        assertTrue(table.jePrazdny());
        table.vloz(1, new Prvek(5));
        assertFalse(table.jePrazdny());
        table.zrus();
        assertTrue(table.jePrazdny());

    }

    @Test
    public void testZrus() {
        IAbstrTable<Prvek, Integer> table = new AbstrTable();

        table.vloz(1, new Prvek(5));
        table.vloz(2, new Prvek(7));
        assertFalse(table.jePrazdny());
        table.zrus();
        assertTrue(table.jePrazdny());
    }

    @Test
    public void testVloz() throws NullPointerException {
        IAbstrTable<Prvek, Integer> table = new AbstrTable();

        assertTrue(table.jePrazdny());
        table.vloz(5, new Prvek(1));
        assertFalse(table.jePrazdny());
    }

    @Test(expected = NullPointerException.class)
    public void testVloz2() throws NullPointerException {
        IAbstrTable<Prvek, Integer> table = new AbstrTable();

        table.vloz(1, null);
        fail();
    }

    @Test
    public void testOdeber() throws NullPointerException {
        IAbstrTable<Prvek, Integer> table = new AbstrTable();

        for (int i = 1; i < 7; i++) {
            table.vloz(i, new Prvek(i));
        }
        int expResult = 5;
        assertEquals(expResult, table.odeber(5).getHodnota());
    }

    @Test(expected = NullPointerException.class)
    public void testOdeber1() throws NullPointerException {
        IAbstrTable<Prvek, Integer> table = new AbstrTable();

        table.vloz(7, new Prvek(8));
        table.vloz(4, null);
        table.vloz(1, new Prvek(7));

        table.odeber(4);
        fail();
    }

    @Test
    public void testNajdi() throws NullPointerException {
        IAbstrTable<Prvek, Integer> table = new AbstrTable();

        for (int i = 1; i < 7; i++) {
            table.vloz(i, new Prvek(i));
        }
        int expResult = 6;
        assertEquals(expResult, table.najdi(6).getHodnota());
    }

    @Test(expected = NullPointerException.class)
    public void testNajdi2() throws NullPointerException {
        IAbstrTable<Prvek, Integer> table = new AbstrTable();

        for (int i = 1; i < 7; i++) {
            table.vloz(i, new Prvek(i));
        }
        table.najdi(null);
        fail();
    }

    @Test
    public void testIterator() throws NoSuchElementException{
        IAbstrTable<Prvek, Integer> table = new AbstrTable();

        for (int i = 1; i < 10; i++) {
            table.vloz(i, new Prvek(1));
        }
        Iterator iterator = table.iterator();
        Prvek prvek = null;
        while(iterator.hasNext()){
            prvek = (Prvek) iterator.next();
        }
        assertEquals(1, prvek.getHodnota());
    }
    @Test(expected = NoSuchElementException.class)
    public void testIterator2() throws NoSuchElementException{
        IAbstrTable<Prvek, Integer> table = new AbstrTable();

        for (int i = 1; i < 10; i++) {
            table.vloz(i, new Prvek(1));
        }
        Iterator iterator = table.iterator();
        Prvek prvek = null;
        while(iterator.hasNext()){
            prvek = (Prvek) iterator.next();
        }
        iterator.next();
        fail();
    }

    private class Prvek {

        private int hodnota;

        Prvek(int hodnota) {
            this.hodnota = hodnota;
        }

        @Override
        public String toString() {
            return hodnota + "";
        }

        public int getHodnota() {
            return hodnota;
        }
    }
}
