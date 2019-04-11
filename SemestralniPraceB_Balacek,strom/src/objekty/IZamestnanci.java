package objekty;

import objekty.Zamestnanec;
import java.util.Iterator;


public interface IZamestnanci {

    Iterator<Zamestnanec> iterator();

    Zamestnanec najdi(int id);
    
    Zamestnanec odeber(int id);
     
    void vloz(int id, Zamestnanec zamestnanec);
    
}
