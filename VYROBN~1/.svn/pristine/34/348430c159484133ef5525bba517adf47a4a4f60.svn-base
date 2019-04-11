package zpracovani;

import kolekce.AbstrDoubleList;
import kolekce.IAbstrDoubleList;
import procesy.IProces;

public class AbstrLifo<T> implements IAbstrLifo<T> {

    private final IAbstrDoubleList<IProces> STACK;
    private static IAbstrLifo<IProces> lifo;

    private AbstrLifo() {
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
        STACK.vlozPrvni((IProces) data);
    }

    @Override
    public IProces odeber() {
        return STACK.odeberPrvni();
    }
    
    public static IAbstrLifo getInstance() {
        if (lifo == null) {
            try {
                lifo = new AbstrLifo();
            } catch (NullPointerException ex) {
                knihovna.Alerts.showErrorAlert("Chyba!", "Nastala chyba při vytváření zasobníku!");
            }
        }
        return lifo;
    }
}
