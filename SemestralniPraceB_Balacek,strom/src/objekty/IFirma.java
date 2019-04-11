/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekty;

import java.util.Iterator;
import java.util.stream.Stream;


public interface IFirma extends Iterable<IPobocka>{

    Iterator<IPobocka> iterator();

    IPobocka najdi(String nazev);

    IPobocka odeber(String nazev);

    void vloz(String nazev, IPobocka pobocka);

    public Stream<IPobocka> stream();
    
}
