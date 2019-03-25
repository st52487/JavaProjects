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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class databaseHelper {

    public databaseHelper(String login, String pswd) throws SQLException {
        myInit(login, pswd);
    }

    public static void myInit(String login, String pswd) throws SQLException {
        OracleConnector.setUpConnection("fei-sql1.upceucebny.cz", 1521, "IDAS12", login, pswd);
    }

    /*-------------------SELECT ---------------*/
    public ArrayList<Pracoviste> getPracovisteAll() throws SQLException {
        ArrayList<Pracoviste> pracoviste = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PRACOVISTE");
        ResultSet rset = stmt.executeQuery();

        while (rset.next()) {
            pracoviste.add(new Pracoviste(
                    rset.getInt("id_pracoviste"),
                    rset.getString("zkratka"),
                    rset.getString("nazev"),
                    rset.getString("fakulta"),
                    rset.getString("plnynazevfakulta")));
        }
        return pracoviste;
    }

    public ArrayList<Vyucujici> getVyucujiciAll() throws SQLException {
        ArrayList<Vyucujici> vyucujici = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();

        Statement stm;
        stm = conn.createStatement();
        String sql = "SELECT * FROM VYUCUJICI";
        ResultSet rst;
        rst = stm.executeQuery(sql);
        while (rst.next()) {
            vyucujici.add(new Vyucujici(
                    rst.getInt("id_evidvyuc"),
                    rst.getString("prijmeni"),
                    rst.getString("jmeno"),
                    rst.getString("titulza"),
                    rst.getString("titulpred"),
                    rst.getInt("id_kontakt")));
        }
        return vyucujici;
    }

    public ArrayList<RozvrhovyPlan> getRozvrhovyPlanUcitele(Vyucujici vyucujici) throws SQLException {
        ArrayList<RozvrhovyPlan> rovrhPlan = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt1 = conn.prepareStatement("SELECT * FROM UKAZROZVRHPLAN_UCITELE where id_evidvyuc = ?");
        stmt1.setInt(1, vyucujici.getIdVyucujici());
        ResultSet rst = stmt1.executeQuery();

        while (rst.next()) {
            rovrhPlan.add(new RozvrhovyPlan(
                    rst.getInt("id_rozvrhovyplan"),
                    rst.getString("nazev"),
                    rst.getString("zkratka"),
                    rst.getInt("kapacita"),
                    rst.getString("formavyuky"),
                    rst.getString("druhVyuky(cvic,pred,sem)"),
                    rst.getInt("rozsahhodin"),
                    rst.getInt("id_zpusobvyuky")));
        }
        stmt1.executeUpdate();
        conn.commit();
        return rovrhPlan;
    }

    public ArrayList<Predmet> getPredmetyAll() throws SQLException {
        ArrayList<Predmet> predmety = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();

        Statement stm;
        stm = conn.createStatement();
        String sql = "SELECT * FROM PREDMET";
        ResultSet rst;
        rst = stm.executeQuery(sql);
        while (rst.next()) {
            predmety.add(new Predmet(
                    rst.getInt("id_predmet"),
                    rst.getString("nazev"),
                    rst.getString("zkratka"),
                    rst.getString("zpusobzakonceni"),
                    rst.getString("formavyuky"),
                    rst.getInt("kapacita")));
        }
        return predmety;
    }

    public int najdiKontakt(Vyucujici vyuc) throws SQLException {
        Connection conn;
        int id = 0;

        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM KONTAKT where telefon = ?");
        stmt.setInt(1, vyuc.getKontakt().getTelefon());
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            id = rset.getInt("id_kontakt");

        }
        stmt.executeUpdate();
        conn.commit();
        return id;
    }

    public Kontakt vratKontaktView(Vyucujici vyucujici) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        Kontakt kontakt = null;
        PreparedStatement stmt1 = conn.prepareStatement("SELECT * FROM UKAZKONTAKT_UCITELE where ID_EVIDVYUC = ?");
        stmt1.setInt(1, vyucujici.getIdVyucujici());
        ResultSet rset = stmt1.executeQuery();
        while (rset.next()) {
            kontakt = new Kontakt(
                    rset.getInt("telefon"),
                    rset.getInt("mobil"),
                    rset.getString("email"));
        }
        stmt1.executeUpdate();
        conn.commit();
        return kontakt;
    }

    public Pracoviste vratPracovisteView(Vyucujici vyucujici) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        Pracoviste prc = null;
        PreparedStatement stmt1 = conn.prepareStatement("SELECT * FROM UKAZPRACOVISTE_UCITELE where ID_EVIDVYUC = ?");
        stmt1.setInt(1, vyucujici.getIdVyucujici());
        ResultSet rset = stmt1.executeQuery();
        while (rset.next()) {
            prc = new Pracoviste(
                    rset.getInt("id_pracoviste"),
                    rset.getString("zkratka"),
                    rset.getString("nazev"),
                    rset.getString("fakulta"),
                    rset.getString("plnynazevfakulta"));
        }
        stmt1.executeUpdate();
        conn.commit();
        return prc;
    }

    public ArrayList<StudijniObor> getOboryAll() throws SQLException {
        ArrayList<StudijniObor> studijniObor = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();

        Statement stm;
        stm = conn.createStatement();
        String sql = "SELECT * FROM STUD_OBOR";
        ResultSet rst;
        rst = stm.executeQuery(sql);
        while (rst.next()) {
            studijniObor.add(new StudijniObor(
                    rst.getInt("id_studobor"),
                    rst.getString("slozeniplanu"),
                    rst.getString("nazevoboru"),
                    rst.getInt("odhadpocetstudent")));
        }
        return studijniObor;
    }

    public ArrayList<StudijniPlan> getStudijniPlanOboru(StudijniObor obor) throws SQLException {
        ArrayList<StudijniPlan> studijniPlan = new ArrayList<>();
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM UKAZSTUDIJNIPLAN_OBORU where ID_STUDOBOR = ?");
        stmt.setInt(1, obor.getIdStudijniObor());
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            studijniPlan.add(new StudijniPlan(
                    rset.getInt("id_studijniplan"),
                    rset.getString("druhkategorie"),
                    rset.getString("nazev"),
                    rset.getString("zkratka"),
                    rset.getString("nazevoboru"),
                    rset.getInt("odhadpocetstudent"),
                    rset.getInt("kapacita")));
        }
        stmt.executeUpdate();
        conn.commit();

        return studijniPlan;
    }

    public ArrayList<PlanPredmetu> getFiltrovanePredmety(Predmet predmet) throws SQLException {
        ArrayList<PlanPredmetu> planPredmetu = new ArrayList<>();
        Connection conn;
        conn = OracleConnector.getConnection();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM UKAZ_PREDMETYBYKAPACITA where id_predmet = ?");
        stmt.setInt(1, predmet.getIdPredmet());

        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            planPredmetu.add(new PlanPredmetu(
                    rset.getString("nazev"),
                    rset.getString("zkratka"),
                    rset.getString("jmeno"),
                    rset.getString("prijmeni"),
                    rset.getInt("kapacita")));
        }
        stmt.executeUpdate();
        conn.commit();
        return planPredmetu;
    }

    /* -------------- INSERT ------------------------*/
    public void insertRozvrhPlan(int rozsahHodin, Predmet predmet, Vyucujici vyucujici) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO ROZVRHOVY_PLAN "
                + "(id_rozvrhovyplan, rozsahhodin, predmet_id_predmet, vyucujici_id_evidvyuc)"
                + "VALUES (ROZVRHPLAN_ID.NEXTVAL, ?, ?, ?)");
        stmt.setInt(1, rozsahHodin);
        stmt.setInt(2, predmet.getIdPredmet());
        stmt.setInt(3, vyucujici.getIdVyucujici());
        stmt.executeUpdate();
        conn.commit();
    }

    public void insertDataKontakt(Vyucujici vyucujici) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO KONTAKT (id_kontakt, telefon, mobil, email)"
                + "values (SEQ_KONTAKT_ID.NEXTVAL, ?, ?, ?)");
        stmt.setInt(1, vyucujici.getKontakt().getTelefon());
        stmt.setInt(2, vyucujici.getKontakt().getMobil());
        stmt.setString(3, vyucujici.getKontakt().getEmail());
        stmt.executeUpdate();
        conn.commit();
    }

    public void insertDataPredmet(Predmet predmet) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO PREDMET (id_predmet, nazev, zkratka, zpusobzakonceni, formavyuky, kapacita)"
                + "VALUES(SEQ_PREDMET_ID.NEXTVAL, ?, ?, ?, ?, ?)");
        stmt.setString(1, predmet.getNazev());
        stmt.setString(2, predmet.getZkratka());
        stmt.setString(3, predmet.getZpusobZakonceni());
        stmt.setString(4, predmet.getFormaVyuky());
        stmt.setInt(5, predmet.getKapacita());
        stmt.executeUpdate();
        conn.commit();
    }

    public void addZpusobVyuky(ZpusobVyuky zpusob) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO ZPUSOB_VYUKY (id_zpusobVyuky, \"druhVyuky(cvic,pred,sem)\")"
                + "values (ZPUSOBVYUKY_ID.NEXTVAL, ?)");
        stmt.setString(1, zpusob.getDruhVyuky());
        stmt.executeUpdate();
        conn.commit();
    }

    public void propojRozvrhPlanZpusobVyuky() throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        int id_rozvrhPlan = 0;
        int id_zpusobVyuky = 0;
        PreparedStatement stmt = conn.prepareStatement("SELECT MAX(ID_ROZVRHOVYPLAN) FROM ROZVRHOVY_PLAN");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            id_rozvrhPlan = rset.getInt("MAX(ID_ROZVRHOVYPLAN)");
        }
        stmt.executeUpdate();
        PreparedStatement stmt1 = conn.prepareStatement("SELECT MAX(ID_ZPUSOBVYUKY) FROM ZPUSOB_VYUKY");
        ResultSet rset1 = stmt1.executeQuery();
        while (rset1.next()) {
            id_zpusobVyuky = rset1.getInt("MAX(ID_ZPUSOBVYUKY)");
        }
        stmt1.executeUpdate();

        PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO ZPUSOBVYUKY_ROZVRHOVYPLAN values (?, ?)");
        stmt2.setInt(1, id_rozvrhPlan);
        stmt2.setInt(2, id_zpusobVyuky);
        stmt2.executeUpdate();
        conn.commit();
    }

    public void addPracoviste(Pracoviste pracoviste) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO PRACOVISTE (id_pracoviste, zkratka, nazev, fakulta, plnynazevfakulta)"
                + "values (SEQ_PRACOVISTE_ID.NEXTVAL, ?, ?, ?, ?)");
        stmt.setString(1, pracoviste.getZkratka());
        stmt.setString(2, pracoviste.getNazev());
        stmt.setString(3, pracoviste.getFakulta());
        stmt.setString(4, pracoviste.getPlnyNazevFakulta());
        stmt.executeUpdate();
        conn.commit();
    }

    public void addStudijniPlan(Predmet predm, StudijniObor studijniObor, int druh) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO STUDIJNI_PLAN (id_studijniplan, id_kategorie, id_studobor, id_predmet)"
                + "values (SEQ_IDSTUDIJNIPLAN.NEXTVAL, ?, ?,?)");
        stmt.setInt(1, druh);
        stmt.setInt(2, studijniObor.getIdStudijniObor());
        stmt.setInt(3, predm.getIdPredmet());
        stmt.executeUpdate();
        conn.commit();
    }

    public void insertDataStudijniObor(StudijniObor studijniObor) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO STUD_OBOR (id_studobor, slozeniplanu, nazevoboru,odhadpocetstudent)"
                + "values (SEQ_STUDOBOR_ID.NEXTVAL, ?, ?, ?)");
        stmt.setString(1, studijniObor.getSlozeniPlanu());
        stmt.setString(2, studijniObor.getNazevOboru());
        stmt.setInt(3, studijniObor.getOdhadPoctuStudentu());
        stmt.executeUpdate();
        conn.commit();
    }

    public void insertDataVyuc(Vyucujici vyucujici, int idPracoviste, int idKontakt) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();

        PreparedStatement stmt1 = conn.prepareStatement("INSERT INTO VYUCUJICI (ID_EVIDVYUC, prijmeni, jmeno, titulza, titulpred, id_kontakt, id_pracoviste) "
                + "VALUES (SEQ_VYUC_ID.NEXTVAL, ?, ?, ?, ?, ?, ?)");
        stmt1.setString(1, vyucujici.getPrijmeni());
        stmt1.setString(2, vyucujici.getJmeno());
        stmt1.setString(3, vyucujici.getTitulZa());
        stmt1.setString(4, vyucujici.getTitulPred());
        stmt1.setInt(5, idKontakt);
        stmt1.setInt(6, idPracoviste);
        stmt1.executeUpdate();
        conn.commit();
    }

    /*---------------DELETE ------------------------*/
    public void deleteDataPredmet(Predmet predmet) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM PREDMET WHERE id_predmet = ?");
        stmt.setInt(1, predmet.getIdPredmet());
        stmt.executeUpdate();
        conn.commit();
    }

    public void deleteDataVyucujici(Vyucujici vyucujici) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM VYUCUJICI where id_evidvyuc = ?");
        stmt.setInt(1, vyucujici.getIdVyucujici());
        stmt.executeUpdate();
        conn.commit();
    }

    public void deleteDataKontakt(int idKontakt) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt1 = conn.prepareStatement("DELETE FROM KONTAKT where id_kontakt = ?");
        stmt1.setInt(1, idKontakt);
        stmt1.executeUpdate();
        conn.commit();
    }

    public void deleteDataMeziTabulka(RozvrhovyPlan rozvrhovyPlan) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();

        PreparedStatement stmt1 = conn.prepareStatement("DELETE FROM ZPUSOBVYUKY_ROZVRHOVYPLAN where id_zpusobvyuky = ?");
        stmt1.setInt(1, rozvrhovyPlan.getIdZpusobVyuky());
        stmt1.executeUpdate();
        conn.commit();
    }

    public void deleteDataZpusobVyuky(RozvrhovyPlan rozvrhovyPlan) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();

        PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM ZPUSOB_VYUKY where id_zpusobvyuky = ?");
        stmt2.setInt(1, rozvrhovyPlan.getIdZpusobVyuky());
        stmt2.executeUpdate();
        conn.commit();
    }

    public void deleteDataRozvrhovyPlan(RozvrhovyPlan rozvrhovyPlan) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();

        PreparedStatement stmt = conn.prepareStatement("DELETE FROM ROZVRHOVY_PLAN where id_rozvrhovyplan = ?");
        stmt.setInt(1, rozvrhovyPlan.getIdRozvrhovyPlan());
        stmt.executeUpdate();
        conn.commit();
    }

    public void deletePracoviste(Pracoviste selectedItem) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();

        PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM PRACOVISTE WHERE ID_PRACOVISTE = ?");
        stmt2.setInt(1, selectedItem.getIdPracoviste());
        stmt2.executeUpdate();
        conn.commit();
    }

    public void deleteObor(StudijniObor selectedItem) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();

        PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM STUD_OBOR WHERE ID_STUDOBOR = ?");
        stmt2.setInt(1, selectedItem.getIdStudijniObor());
        stmt2.executeUpdate();
        conn.commit();
    }

    public void deleteStudijniPlan(StudijniPlan selectedItem) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();

        PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM STUDIJNI_PLAN WHERE ID_STUDIJNIPLAN = ?");
        stmt2.setInt(1, selectedItem.getId_studijniPlan());
        stmt2.executeUpdate();
        conn.commit();
    }

    /*------------- UPDATE -------------------------*/
    public void editujPracoviste(Pracoviste pracoviste) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();

        PreparedStatement stmt2 = conn.prepareStatement("UPDATE PRACOVISTE SET zkratka = ?, nazev = ?, "
                + "fakulta = ?, plnynazevfakulta = ? WHERE ID_PRACOVISTE = ?");
        stmt2.setString(1, pracoviste.getZkratka());
        stmt2.setString(2, pracoviste.getNazev());
        stmt2.setString(3, pracoviste.getFakulta());
        stmt2.setString(4, pracoviste.getPlnyNazevFakulta());
        stmt2.setInt(5, pracoviste.getIdPracoviste());
        stmt2.executeUpdate();
        conn.commit();
    }

    public void editujPredmetu(Predmet predmet) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();

        PreparedStatement stmt2 = conn.prepareStatement("UPDATE PREDMET SET nazev = ?, zkratka = ?, "
                + "zpusobzakonceni = ?, formavyuky = ?, kapacita = ?  WHERE ID_PREDMET = ?");
        stmt2.setString(1, predmet.getNazev());
        stmt2.setString(2, predmet.getZkratka());
        stmt2.setString(3, predmet.getZpusobZakonceni());
        stmt2.setString(4, predmet.getFormaVyuky());
        stmt2.setInt(5, predmet.getKapacita());
        stmt2.setInt(6, predmet.getIdPredmet());
        stmt2.executeUpdate();
        conn.commit();
    }

    public void editujObor(StudijniObor studijniObor) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();

        PreparedStatement stmt2 = conn.prepareStatement("UPDATE STUD_OBOR SET slozeniplanu = ?, "
                + "nazevoboru = ?, odhadpocetstudent = ? WHERE ID_STUDOBOR = ?");
        stmt2.setString(1, studijniObor.getSlozeniPlanu());
        stmt2.setString(2, studijniObor.getNazevOboru());
        stmt2.setInt(3, studijniObor.getOdhadPoctuStudentu());
        stmt2.setInt(4, studijniObor.getIdStudijniObor());
        stmt2.executeUpdate();
        conn.commit();
    }

    public void editujUcitele(Vyucujici vyucujici) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();

        PreparedStatement stmt2 = conn.prepareStatement("UPDATE VYUCUJICI SET prijmeni = ?, jmeno = ?, "
                + "titulza = ?, titulpred = ? WHERE ID_EVIDVYUC = ?");
        stmt2.setString(1, vyucujici.getPrijmeni());
        stmt2.setString(2, vyucujici.getJmeno());
        stmt2.setString(3, vyucujici.getTitulZa());
        stmt2.setString(4, vyucujici.getTitulPred());
        stmt2.setInt(5, vyucujici.getIdVyucujici());
        stmt2.executeUpdate();
        conn.commit();
    }

    public void editujKontaktUcitele(Vyucujici vyucujici) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();

        PreparedStatement stmt2 = conn.prepareStatement("UPDATE KONTAKT SET telefon = ?, mobil = ?, "
                + "email = ? where id_kontakt = ?");
        stmt2.setInt(1, vyucujici.getKontakt().getTelefon());
        stmt2.setInt(2, vyucujici.getKontakt().getMobil());
        stmt2.setString(3, vyucujici.getKontakt().getEmail());
        stmt2.setInt(4, vyucujici.getKontakt().getIdKontakt());
        stmt2.executeUpdate();
        conn.commit();
    }
}
