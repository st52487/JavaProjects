package objekty;

import kolekce.AbstrTable;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Firma implements IFirma {
    
    private final AbstrTable<IPobocka, String> TABULKA_POBOCEK;
    
    public Firma() {
        TABULKA_POBOCEK = new AbstrTable<>();
    }
    
    @Override
    public IPobocka najdi(String nazev) {
        return TABULKA_POBOCEK.najdi(nazev);
    }
    
    @Override
    public void vloz(String nazev, IPobocka pobocka) {
        TABULKA_POBOCEK.vloz(nazev, pobocka);
    }
    
    @Override
    public IPobocka odeber(String nazev) {
        return TABULKA_POBOCEK.odeber(nazev);
    }
    
    @Override
    public Iterator<IPobocka> iterator(){
        return TABULKA_POBOCEK.iterator();
    }
    
   public Stream<IPobocka> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
    
    
}
