package kolekce;

import java.util.Iterator;
import java.util.NoSuchElementException;


public interface IAbstrTree<E> extends Iterable<E>{

    Iterator<E> iterator();

    boolean jePrazdny();

    int mohutnost();

    E odeberKoren() throws NoSuchElementException, NullPointerException;

    E odeberList(int poradi) throws NoSuchElementException, NullPointerException;

    void vlozKoren(E data) throws NullPointerException;

    void vlozList(E data) throws NullPointerException, NoSuchElementException;

    E zpristupniKoren() throws NullPointerException;

    E zpristupniOtce() throws NullPointerException;

    E zpristupniSyna(int poradi) throws NullPointerException, NoSuchElementException;

    void zrus();
    
}
