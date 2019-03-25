package seznam;

import alert.ChybovaHlaska;
import automobily.Automobil;
import automobily.BarvaAuta;
import automobily.Dodavka;
import automobily.NakladniAutomobil;
import automobily.OsobniAutomobil;
import automobily.NahraniAutomobiluTest;
import automobily.Tahac;
import automobily.TypAutomobilu;
import automobily.Valnik;
import java.util.function.Predicate;
import javafx.scene.control.ListView;
import kolekce.ISeznam;
import kolekce.KolekceException;
import kolekce.Seznam;
import data.UlozeniNacteni;

public class Adapter {

    private final ISeznam<Automobil> SEZNAM;
    private static Adapter adapter;

    public static Adapter getInstance(int velikost) {
        if (adapter == null) {
            try {
                adapter = new Adapter(velikost);
            } catch (KolekceException ex) {
                ChybovaHlaska.zobrazAlert("Chyba ve vytváření adaptéru!");
            }
        }

        return adapter;
    }

    private Adapter(int velikost) throws KolekceException {
        SEZNAM = new Seznam<>(velikost);
    }

    public ISeznam<Automobil> getSeznam() {
        return  SEZNAM;
    }

    public void clickTest() {
        SEZNAM.zrus();
        try {
            SEZNAM.pridej(NahraniAutomobiluTest.getTest());
        } catch (KolekceException ex) {
            ChybovaHlaska.zobrazAlert("Nepodařilo se přidat testovací automobily!");
        }
    }

    public void clickPridejOsobni(int maxPocetPolozek, boolean jede,
            TypAutomobilu typAutomobilu, int pocetKol,
            BarvaAuta barva,
            String znackaAuta, boolean funkcni) throws KolekceException {
        try {
            SEZNAM.pridej(new OsobniAutomobil(maxPocetPolozek, jede, 
                    pocetKol, barva, znackaAuta,
                    funkcni));
        } catch (KolekceException ex) {
           ChybovaHlaska.zobrazAlert("Nepodařilo se přidat Osobní automobil!");
        }
    }

    public void clickPridejDodavka(int maxPocetPolozek, boolean jede,
            TypAutomobilu typAutomobilu, int pocetKol,
            BarvaAuta barva,
            int hmotnost, String typMotoru) throws KolekceException {
        try {
            SEZNAM.pridej(new Dodavka(maxPocetPolozek,jede, 
                    pocetKol, barva, hmotnost, typMotoru));
        } catch (KolekceException e) {
            ChybovaHlaska.zobrazAlert("Nepodařilo se přidat Dodávku!");
        }
    }
    
    public void clickPridejTahac(int maxPocetPolozek, boolean jede,
            TypAutomobilu typAutomobilu, int pocetKol,
            BarvaAuta barva,
            String identifikace, boolean jeBenzin) throws KolekceException {
        try {
            SEZNAM.pridej(new Tahac(maxPocetPolozek,jede,pocetKol, barva,
                    identifikace,
                    jeBenzin));
        } catch (KolekceException ex) {
           ChybovaHlaska.zobrazAlert("Nepodařilo se přidat Osobní automobil!");
        }
    }
    
    public void clickPridejValnik(int maxPocetPolozek, boolean jede,          
            TypAutomobilu typAutomobilu, int pocetKol,
            BarvaAuta barva,
            int stupenZniceni, String druhVyfuku) throws KolekceException {
        try {
            SEZNAM.pridej(new Valnik(maxPocetPolozek, jede, 
                    pocetKol, barva, stupenZniceni, druhVyfuku));
        } catch (KolekceException e) {
            ChybovaHlaska.zobrazAlert("Nepodařilo se přidat Dodávku!");
        }
    }

    public void clickPridejNakladni(int maxPocetPolozek, boolean jede,
            TypAutomobilu typAutomobilu, int pocetKol,
            BarvaAuta barva,
            int pocetKoni, boolean nahonNaVsechny) throws KolekceException {
        try {
            SEZNAM.pridej(new NakladniAutomobil(maxPocetPolozek, jede, 
                    pocetKol, barva, pocetKoni,
                    nahonNaVsechny));
        } catch (KolekceException ex) {
            ChybovaHlaska.zobrazAlert("Nepodařilo se přidat Nakladni automobil!");
        }
    }

    public void smaz(Automobil autmobil) {
        SEZNAM.odeber(autmobil);
    }

    public void vypisFiltrovanyAutomobil(ListView<Automobil> list,
            Predicate<Automobil> filter) {
        list.getItems().clear();
        SEZNAM.stream().filter(filter)
                .forEach(t -> list.getItems().add(t));
    }

    public void serializuj() {
        UlozeniNacteni.serializuj(SEZNAM);
    }

    public void deserializuj() {
        UlozeniNacteni.deserializuj(SEZNAM);
    }

    

   
}
