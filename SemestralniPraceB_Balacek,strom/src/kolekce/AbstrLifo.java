package kolekce;

import objekty.Pozice;

public class AbstrLifo<T> implements IAbstrLifo<T> {

    private final IAbstrDoubleList<T> STACK;
    private  IAbstrLifo<T> lifo;

    public AbstrLifo() {
        STACK = new AbstrDoubleList();
    }

    @Override
    public void zrus() {
        STACK.zrus();
    }

    @Override
    public boolean jePrazdny() {
        return STACK.jePrazdny();
    }

    @Override
    public void vloz(T data) {
        STACK.vlozPrvni(data);
    }

    @Override
    public T odeber() {
        return STACK.odeberPrvni();
    }
}
