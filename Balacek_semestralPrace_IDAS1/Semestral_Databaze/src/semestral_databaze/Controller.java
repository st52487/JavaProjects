/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestral_databaze;

import data.Kontakt;
import data.PlanPredmetu;
import data.Pracoviste;
import data.Predmet;
import data.RozvrhovyPlan;
import data.StudijniObor;
import data.StudijniPlan;
import data.Vyucujici;
import data.ZpusobVyuky;
import java.sql.SQLException;
import java.util.ArrayList;
import knihovna.Alerts;

public class Controller {

    private databaseHelper dh;

    public boolean login(String login, String psw) {
        try {
            dh = new databaseHelper(login, psw);
            return true;
        } catch (SQLException ex) {
            Alerts.showErrorAlert("Chyba", "Nepodařilo se připojení do databáze");
        }
        return false;
    }

    public ArrayList<Pracoviste> getPracoviste() throws SQLException {
        return dh.getPracovisteAll();
    }

    public ArrayList<RozvrhovyPlan> getRozvrhovyPlanUcitele(Vyucujici vyucujici) throws SQLException {
        return dh.getRozvrhovyPlanUcitele(vyucujici);
    }

    public Kontakt dejKontaktProVyucujiciho(Vyucujici vyucujici) throws SQLException {
        return dh.vratKontaktView(vyucujici);
    }

    public void addRozvrhPlanVyucujicimu(int rozsahHodin, Predmet predmet, Vyucujici vyucujici) throws SQLException {
        dh.insertRozvrhPlan(rozsahHodin, predmet, vyucujici);
    }

    public Pracoviste dejPracovisteVyucujiciho(Vyucujici vyucujici) throws SQLException {
        return dh.vratPracovisteView(vyucujici);
    }

    public ArrayList<Vyucujici> getVyucujici() throws SQLException {
        return dh.getVyucujiciAll();
    }

    public ArrayList<Predmet> getPredmety() throws SQLException {
        return dh.getPredmetyAll();
    }

    public void deletePredmet(Predmet predmet) throws SQLException {
        dh.deleteDataPredmet(predmet);
    }

    public void deleteVyucujici(Vyucujici vyucujici) throws SQLException {
        dh.deleteDataVyucujici(vyucujici);
        dh.deleteDataKontakt(vyucujici.getIdKontakt());
    }

    public void addVyucujici(Vyucujici vyucujici, int idPracoviste) throws SQLException {
        dh.insertDataKontakt(vyucujici);
        int id = dh.najdiKontakt(vyucujici);
        dh.insertDataVyuc(vyucujici, idPracoviste, id);
    }

    public void addPredmet(Predmet predmet) throws SQLException {
        dh.insertDataPredmet(predmet);
    }

    public void deleteRozvrhovyPlan(RozvrhovyPlan rozvrhovyPlan) throws SQLException {
        dh.deleteDataMeziTabulka(rozvrhovyPlan);
        dh.deleteDataZpusobVyuky(rozvrhovyPlan);
        dh.deleteDataRozvrhovyPlan(rozvrhovyPlan);
    }

    public void addZpusobVyuky(ZpusobVyuky zpusob) throws SQLException {
        dh.addZpusobVyuky(zpusob);
    }

    public void propojRozvrhPlanZpusobVyuky() throws SQLException {
        dh.propojRozvrhPlanZpusobVyuky();
    }

    public void addStudijniObor(StudijniObor studijniObor) throws SQLException {
        dh.insertDataStudijniObor(studijniObor);
    }

    public ArrayList<StudijniObor> getStudijniObory() throws SQLException {
        return dh.getOboryAll();
    }

    public void addStudijniPlan(Predmet predm, StudijniObor studijniObor, int druh) throws SQLException {
        dh.addStudijniPlan(predm, studijniObor, druh);
    }

    public ArrayList<StudijniPlan> getStudijniPlanOboru(StudijniObor obor) throws SQLException {
        return dh.getStudijniPlanOboru(obor);
    }

    public void addPracoviste(Pracoviste pracoviste) throws SQLException {
        dh.addPracoviste(pracoviste);
    }

    public void deletePracoviste(Pracoviste selectedItem) throws SQLException {
        dh.deletePracoviste(selectedItem);
    }

    public void deleteObor(StudijniObor selectedItem) throws SQLException {
        dh.deleteObor(selectedItem);
    }

    public void deleteStudijniPlan(StudijniPlan selectedItem) throws SQLException {
        dh.deleteStudijniPlan(selectedItem);
    }

    public void editujPracoviste(Pracoviste pracoviste) throws SQLException {
        dh.editujPracoviste(pracoviste);
    }

    public void editujPredmet(Predmet predmet) throws SQLException {
        dh.editujPredmetu(predmet);
    }

    public void editujObor(StudijniObor studijniObor) throws SQLException {
        dh.editujObor(studijniObor);
    }

    public void editujUcitele(Vyucujici vyucujici) throws SQLException {
        dh.editujKontaktUcitele(vyucujici);
        dh.editujUcitele(vyucujici);

    }

    public ArrayList<PlanPredmetu> getFiltrovanePredmety(Predmet predmet) throws SQLException {
        return dh.getFiltrovanePredmety(predmet);
    }
}
