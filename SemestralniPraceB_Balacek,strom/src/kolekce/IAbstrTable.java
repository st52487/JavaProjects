package kolekce;

import java.util.Iterator;
import java.util.NoSuchElementException;


public interface IAbstrTable<V, K> extends Iterable<V> {

    /*vytvoˇrí iterátor, který umož ˇnuje procházení tabulky*/
    Iterator<V> iterator();

    /*zjistí, zda tabulka je prázdná*/
    boolean jePrazdny();

    /*vyhledá prvek dle klíce*/
    V najdi(K key) throws NullPointerException;

    /*odebere prvek dle klíˇce z tabulky*/
    V odeber(K key) throws NullPointerException;

    /*vloží prvek s klíˇcem do tabulky*/
    void vloz(K key, V value) throws NullPointerException;

    /*zruší celou tabulky*/
    void zrus();
}
