
import java.util.Iterator;
import java.util.NoSuchElementException;
import kolekce.AbstrTree;
import kolekce.IAbstrTree;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestTree {

    public TestTree() {
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
        IAbstrTree<Prvek> tree = new AbstrTree<>();

        assertTrue(tree.jePrazdny());
        tree.vlozKoren(new Prvek(5));
        assertFalse(tree.jePrazdny());
    }

    @Test
    public void testMohutnost() {
        IAbstrTree<Prvek> tree = new AbstrTree<>();

        tree.vlozKoren(new Prvek(1));
        tree.zpristupniKoren();
        for (int i = 0; i < 9; i++) {
            tree.vlozList(new Prvek(i));
        }
        assertEquals(10, tree.mohutnost());
    }

    @Test
    public void testVlozKoren() throws NullPointerException {
        IAbstrTree<Prvek> tree = new AbstrTree<>();

        tree.vlozKoren(new Prvek(5));
        assertEquals(1, tree.mohutnost());
        assertEquals(5, tree.zpristupniKoren().getHodnota());
    }

    @Test(expected = NullPointerException.class)
    public void testVlozKoren2() throws NullPointerException {
        IAbstrTree<Prvek> tree = new AbstrTree<>();

        tree.vlozKoren(null);
        assertEquals(null, tree.zpristupniKoren());
    }

    @Test
    public void testZpristupniKoren() {
        IAbstrTree<Prvek> tree = new AbstrTree<>();

        Prvek prvek = new Prvek(5);
        tree.vlozKoren(prvek);
        assertEquals(prvek, tree.zpristupniKoren());
    }

    @Test
    public void testZrus() {
        IAbstrTree<Prvek> tree = new AbstrTree<>();
        tree.vlozKoren(new Prvek(5));
        tree.zpristupniKoren();

        for (int i = 0; i < 9; i++) {
            tree.vlozList(new Prvek(i));
        }
        tree.zrus();
        assertTrue(tree.jePrazdny());
    }

    @Test(expected = NoSuchElementException.class)
    public void testOdeberKoren() throws NullPointerException, NoSuchElementException {
        IAbstrTree<Prvek> tree = new AbstrTree<>();

        tree.odeberKoren();
        fail();
    }

    @Test(expected = NullPointerException.class)
    public void testOdeberKoren2() throws NullPointerException, NoSuchElementException {
        IAbstrTree<Prvek> tree = new AbstrTree<>();

        tree.vlozKoren(new Prvek(0));
        tree.zpristupniKoren();
        tree.vlozList(new Prvek(0));
        tree.odeberKoren();
        fail();
    }

    @Test
    public void testOdeberKoren3() throws NullPointerException, NoSuchElementException {
        IAbstrTree<Prvek> tree = new AbstrTree<>();

        Prvek exp = new Prvek(0);
        tree.vlozKoren(exp);
        assertEquals(exp, tree.odeberKoren());
    }

    @Test(expected = NoSuchElementException.class)
    public void testOdeberList() throws NoSuchElementException, NullPointerException {
        IAbstrTree<Prvek> tree = new AbstrTree<>();

        tree.vlozKoren(new Prvek(0));
        tree.odeberList(0);
        fail();
    }

    @Test(expected = NullPointerException.class)
    public void testOdeberList2() throws NoSuchElementException, NullPointerException {
        IAbstrTree<Prvek> tree = new AbstrTree<>();
        tree.zpristupniKoren();
        tree.vlozKoren(new Prvek(4));
        tree.vlozList(new Prvek(10));
        tree.vlozList(new Prvek(10));
        tree.vlozList(new Prvek(10));
        tree.odeberList(1);
        fail();
    }

    @Test
    public void testOdeberList3() throws NoSuchElementException, NullPointerException {
        IAbstrTree<Prvek> tree = new AbstrTree<>();
        Prvek data = new Prvek(4);
        tree.vlozKoren(new Prvek(2));
        tree.zpristupniKoren();
        tree.vlozList(new Prvek(1));
        tree.vlozList(new Prvek(5));
        tree.vlozList(data);

        assertEquals(data, tree.odeberList(3));
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
