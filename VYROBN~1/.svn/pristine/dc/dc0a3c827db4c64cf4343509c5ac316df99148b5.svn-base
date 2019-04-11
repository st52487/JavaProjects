/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpracovani;

import vycty.enumPozice;
import vycty.enumReorg;
import java.util.Iterator;
import kolekce.IAbstrDoubleList;
import procesy.IProces;


public interface IVyrobniProces {

    void deserializuj();

    int importDat(String soubor);

    Iterator iterator();

    void reorganizuj(int kriterium, enumReorg reorganizace);

    void serializuj();

    void vlozProces(IProces proces, enumPozice pozice);

    int vratPocetPolozek();

    void zrus();

    void zrusProces(IProces proces) throws IAbstrDoubleList.ListException;
    
}
