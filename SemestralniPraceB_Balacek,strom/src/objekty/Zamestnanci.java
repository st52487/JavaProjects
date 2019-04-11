package objekty;

import objekty.Zamestnanec;
import kolekce.AbstrTable;
import java.util.Iterator;


public class Zamestnanci implements IZamestnanci {
    
    private final AbstrTable<Zamestnanec, Integer> TABULKA_ZAMESTANCI;
    
    public Zamestnanci(){
        TABULKA_ZAMESTANCI = new AbstrTable<>();
    }
    
    @Override
    public void vloz(int id, Zamestnanec zamestnanec){
        TABULKA_ZAMESTANCI.vloz(id, zamestnanec);
    }
    
    @Override
    public Zamestnanec najdi(int id){
        return TABULKA_ZAMESTANCI.najdi(id);
    }
    
   public Zamestnanec odeber(int id) {
        return TABULKA_ZAMESTANCI.odeber(id);
    }
    
    @Override
    public Iterator<Zamestnanec> iterator(){
        return TABULKA_ZAMESTANCI.iterator();
    }
}
