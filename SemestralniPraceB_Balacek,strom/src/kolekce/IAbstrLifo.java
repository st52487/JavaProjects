package kolekce;

import objekty.Pozice;

public interface IAbstrLifo<T> {

    boolean jePrazdny();

    T odeber();

    void vloz(T data);

    void zrus();

}
