/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekty;

import java.util.Iterator;
import java.util.stream.Stream;

/**
 *
 * @author caval
 */
public interface IPobocka extends Iterable<Pozice>{

    String getMesto();

    String getNazevPobocky();

    Pozice odeberPozici(int poradi);

    Pozice odeberReditele();

    String toString();
    
    boolean jePrazdna();

    void vlozPozici(Pozice pozice);

    void vlozPoziciReditele(Pozice reditel);

    Pozice zpristupniNadrizenou();

    Pozice zpristupniPodrizenouPozici(int poradi);

    Pozice zpristupniPoziciReditele();
    
    Stream<Pozice> stream();
    
    Iterator<Pozice> iteratorSynu();
    
    void zrus();
}
