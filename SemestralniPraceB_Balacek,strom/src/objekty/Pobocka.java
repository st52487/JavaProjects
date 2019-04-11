package objekty;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import kolekce.AbstrTree;

public class Pobocka implements IPobocka {

    private String nazevPobocky;
    private String mesto;
    private final AbstrTree<Pozice> STROM;

    public void setNazevPobocky(String nazevPobocky) {
        this.nazevPobocky = nazevPobocky;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public Pobocka(String nazevPobocky, String mesto) {
        this.nazevPobocky = nazevPobocky;
        this.mesto = mesto;
        STROM = new AbstrTree<>();
    }

    @Override
    public String getMesto() {
        return mesto;
    }

    @Override
    public String getNazevPobocky() {
        return nazevPobocky;
    }

    @Override
    public void vlozPozici(Pozice pozice) {
        STROM.vlozList(pozice);
    }

    @Override
    public Pozice zpristupniPoziciReditele() {
        return STROM.zpristupniKoren();
    }

    @Override
    public Pozice zpristupniPodrizenouPozici(int poradi) {
        return STROM.zpristupniSyna(poradi);
    }

    @Override
    public Pozice zpristupniNadrizenou() {
        return STROM.zpristupniOtce();
    }

    @Override
    public Pozice odeberReditele() {
        return STROM.odeberKoren();
    }

    @Override
    public boolean jePrazdna() {
        return STROM.jePrazdny();
    }

    @Override
    public Pozice odeberPozici(int poradi) {
        return STROM.odeberList(poradi);
    }

    public Iterator<Pozice> iteratorPozice() {
        return STROM.iterator();
    }

    @Override
    public String toString() {
        return "Nazev pobočky: " + nazevPobocky + ", mesto: " + mesto;
    }

    public void zpristupniReditele() {
        STROM.zpristupniKoren();
    }

    public void vlozPoziciReditele(Pozice pozice) {
        STROM.vlozKoren(pozice);
    }

    public void zpristupniNadrizenouPozici() {
        STROM.zpristupniOtce();
    }

    public Stream<Pozice> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    @Override
    public Iterator<Pozice> iterator() {
        return STROM.iterator();
    }

    public Iterator<Pozice> iteratorSynu() {
        return STROM.iteratorSynu();
    }
    
    @Override
    public void zrus(){
        STROM.zrus();
    }
}
