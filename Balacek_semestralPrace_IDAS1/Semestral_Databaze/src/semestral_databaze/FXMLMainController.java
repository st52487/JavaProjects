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
import enumy.ZpusobVyukyEnum;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import knihovna.Alerts;

public class FXMLMainController implements Initializable {

    @FXML
    private ListView<Pracoviste> listPracovist;
    @FXML
    private Button btnPridejUcitele;
    @FXML
    private ListView<Vyucujici> listviewVyucujici;
    @FXML
    private ListView<RozvrhovyPlan> listVyucujiciPlan;
    @FXML
    private ListView<Predmet> listPredmetu;
    @FXML
    private Button btnPridejPredmet;
    @FXML
    private Button btnSmazPredmet;
    @FXML
    private Button btnSmazVuyc;
    @FXML
    private ListView<Object> listKOntakt;
    @FXML
    private Button btnPlan;
    @FXML
    private Button btnSmazRozvrh;
    @FXML
    private Button btnNahraj;
    @FXML
    private ListView<StudijniObor> listStudijniObor;
    @FXML
    private Button btnPridejObor;
    @FXML
    private ListView<StudijniPlan> listStudijniPlan;
    @FXML
    private Button btnStudijniPlan;
    @FXML
    private Button btnPridejPracoviste;
    @FXML
    private Button btnSmazPracoviste;
    @FXML
    private Button btnEditujPracoviste;
    @FXML
    private Button btnOdeberStudijniObor;
    @FXML
    private Button btnOdeberplan;
    @FXML
    private Button btnEditujPredmet;
    @FXML
    private Button btnEditujObor;
    @FXML
    private Button btnEditujUcitele;
    @FXML
    private ListView<PlanPredmetu> listPlanPredmetu;

    
    /* --------------- vlastní inicializace ------------*/
    private FXMLPridejUciteleController controllerUcitel;
    private FXMLPridejPredmetController controllerPredmet;
    private FXMLVytvorRozvrhPlanController controllerVytvorPlan;
    private FXMLPridejStudijniOborController controllerPridejObor;
    private FXMLPridejStudijniPlanController controllerPridejPlan;
    private FXMLPridejPracovisteController controllerPridejPracoviste;

    private Controller c;
    private Stage stage;
    private Vyucujici vyuc;
    private Kontakt kontaktEditace;
    private Predmet predmet;
    private String typ;
    private String druh;
    private int rozsahHodin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*--------- buttons disabled ----------*/ 
        btnPlan.setDisable(true);
        btnPridejPredmet.setDisable(true);
        btnPridejUcitele.setDisable(true);
        btnSmazPredmet.setDisable(true);
        btnSmazRozvrh.setDisable(true);
        btnSmazVuyc.setDisable(true);
        btnPridejObor.setDisable(true);
        btnStudijniPlan.setDisable(true);
        btnPridejPracoviste.setDisable(true);
        btnSmazPracoviste.setDisable(true);
        btnEditujPracoviste.setDisable(true);
        btnOdeberplan.setDisable(true);
        btnOdeberStudijniObor.setDisable(true);
        btnEditujPredmet.setDisable(true);
        btnEditujObor.setDisable(true);
        btnEditujUcitele.setDisable(true);
    }

    /*--------- metody na plneni listu -----------------*/
    private void fillTablePracoviste(ArrayList<Pracoviste> pracoviste) {
        listPracovist.getItems().clear();
        for (Pracoviste pr : pracoviste) {
            listPracovist.getItems().add(pr);
        }
    }
    
    private void fillTableStudijniPlan(ArrayList<StudijniPlan> studijniPlan) {
        listStudijniPlan.getItems().clear();
        for (StudijniPlan plan : studijniPlan) {
            listStudijniPlan.getItems().add(plan);
        }
    }
    
    private void fillListProPredmet(ArrayList<PlanPredmetu> planPredmetu) {
        listPlanPredmetu.getItems().clear();
        for (PlanPredmetu p : planPredmetu) {
            listPlanPredmetu.getItems().add(p);
        }
    }

    private void fillTablePredmet(ArrayList<Predmet> predmety) {
        listPredmetu.getItems().clear();
        for (Predmet pred : predmety) {
            listPredmetu.getItems().add(pred);
        }
    }

    private void fillTableVyucujici(ArrayList<Vyucujici> vyucujici) {
        listviewVyucujici.getItems().clear();
        for (Vyucujici vyuc : vyucujici) {
            listviewVyucujici.getItems().add(vyuc);
        }
    }

    private void fillTableStudijniObor(ArrayList<StudijniObor> studijniObor) {
        listStudijniObor.getItems().clear();
        for (StudijniObor stud : studijniObor) {
            listStudijniObor.getItems().add(stud);
        }
    }

    private void fillTableRozvrhUcitele(ArrayList<RozvrhovyPlan> rovrhPlan) {
        listVyucujiciPlan.getItems().clear();
        for (RozvrhovyPlan plan : rovrhPlan) {
            listVyucujiciPlan.getItems().add(plan);
        }
    }

    private void loadTables() throws SQLException {
        try {
            fillTablePracoviste(c.getPracoviste());
            fillTableVyucujici(c.getVyucujici());
            fillTablePredmet(c.getPredmety());
            fillTableStudijniObor(c.getStudijniObory());
        } catch (SQLException ex) {
            Alerts.showErrorAlert("Chyba", "Načítání dat z tabulek do aplikace se nezdařilo!");
        }
    }
    
    /*-------------- vlastni metody na pridavani, odebirani, editovani ---------*/
    
    private void pridejVyucujiciho() throws SQLException {
        try {
            this.vyuc = controllerUcitel.getVyucujici();
            int idPracoviste = listPracovist.getSelectionModel().getSelectedIndex() + 1;
            c.addVyucujici(vyuc, idPracoviste);
        } catch (NullPointerException ex) {
            Alerts.showErrorAlert("Chyba", "Pridani se nezdarilo, nejspise neco bylo spatne vyplneno!");
        }
    }

    private void pridejPredmet() throws SQLException {
        try {
            this.predmet = controllerPredmet.getPredmet();
            c.addPredmet(predmet);
        } catch (SQLException ex) {
            Alerts.showErrorAlert("Chyba", "Pridani se nezdarilo, nejspise bylo spatne neco vyplneno!");
        }
    }

    public void pridejStudijniObor() throws SQLException {
        try {
            c.addStudijniObor(controllerPridejObor.getStudijniObor());
        } catch (Exception ex) {
            Alerts.showErrorAlert("Chyba", "Pridavani oboru se nezdarilo");
        }
    }

    private void vytvorStudijniPlan() throws SQLException {
        try {
            this.druh = controllerPridejPlan.getDruh();
            Predmet predm = listPredmetu.getSelectionModel().getSelectedItem();
            StudijniObor studijniObor = listStudijniObor.getSelectionModel().getSelectedItem();
            int idDruhKategorie = zjistiIdDruhu(druh);
            c.addStudijniPlan(predm, studijniObor, idDruhKategorie);
        } catch (SQLException ex) {
            Alerts.showErrorAlert("Chyba", "Nepodařilo se vytvořit plán!");
        }
    }
    
    
    private int zjistiIdDruhu(String druh) {
        switch (druh) {
            case "A":
                return 1;
            case "B":
                return 2;
            default:
                return 3;
        }
    }
 
    private void vytvorEditaciObor() {
        try {
            c.editujObor(controllerPridejObor.getStudijniObor());
        } catch (Exception ex) {
            Alerts.showErrorAlert("Chyba", "Editace oboru se nezdarila");
        }
    }

    private void vytvorEditaciPredmet() {
        try {
            c.editujPredmet(controllerPredmet.getPredmet());
        } catch (Exception ex) {
            Alerts.showErrorAlert("Chyba", "Editace předmětu se nezdarila");
        }
    }

    private void vytvorEditaciPracoviste() {
        try {
            c.editujPracoviste(controllerPridejPracoviste.getPracoviste());
        } catch (Exception ex) {
            Alerts.showErrorAlert("Chyba", "Editace pracoviste se nezdarilo");
        }
    }
    
    private void editujUciteleKontakt() {
        try {
            c.editujUcitele(controllerUcitel.getVyucujici());
        } catch (Exception ex) {
            Alerts.showErrorAlert("Chyba", "Editace oboru se nezdarila");
        }
    }

    private void pridejPracoviste() {
        try {
            c.addPracoviste(controllerPridejPracoviste.getPracoviste());
        } catch (Exception ex) {
            Alerts.showErrorAlert("Chyba", "Pridavani pracoviste se nezdarilo");
        }
    }

    private void vytvorRozPlan() {
        try {
            this.rozsahHodin = controllerVytvorPlan.getRozsahHodin();
            this.typ = controllerVytvorPlan.getTypVyuky();
            ZpusobVyuky zpusob = new ZpusobVyuky(0, typ.toLowerCase());
            Predmet predm = listPredmetu.getSelectionModel().getSelectedItem();
            Vyucujici vyu = listviewVyucujici.getSelectionModel().getSelectedItem();
            c.addRozvrhPlanVyucujicimu(rozsahHodin, predm, vyu);
            c.addZpusobVyuky(zpusob);
            c.propojRozvrhPlanZpusobVyuky();
        } catch (SQLException ex) {
            Alerts.showErrorAlert("Chyba", "Nepodařilo se vytvořit plán!");
        }
    }

    
    /*--------------------- settery ------------------*/
    public void setController(Controller c) {
        this.c = c;
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }

    /*---------------- action handlery, buttony, kliknutí atd ----------------*/
    @FXML
    private void pridejUcitele(ActionEvent event) throws IOException, SQLException {
        if (listPracovist.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Semestral_Databaze.class.getResource("FXMLPridejUcitele.fxml"));
                Parent parent = loader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(Semestral_Databaze.getStage());
                stage.setResizable(false);
                Scene scene = new Scene(parent);
                stage.setTitle("Přidej učitele");
                stage.setScene(scene);
                controllerUcitel = loader.getController();
                controllerUcitel.setStage(stage);
                stage.showAndWait();
                if (controllerUcitel.getClick()) {
                    pridejVyucujiciho();
                    loadTables();
                }
            } catch (IOException ex) {
                Alerts.showErrorAlert("Chyba", "Nepodarilo se otevrit okno!");
            }
        } else {
            Alerts.informationAlert("Information", "Vyberte pracoviště v listu pracovišť");
        }
    }

    @FXML
    private void vytvorRozvrhovyPlan() throws SQLException, IOException {
        if (listviewVyucujici.getSelectionModel().getSelectedItem() != null) {
            if (listPredmetu.getSelectionModel().getSelectedItem() != null) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(Semestral_Databaze.class.getResource("FXMLVytvorRozvrhPlan.fxml"));
                    Parent parent = loader.load();
                    Stage stage = new Stage();
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.setResizable(false);
                    Scene scene = new Scene(parent);
                    stage.setTitle("Vytvoř rozvrh");
                    stage.setScene(scene);
                    controllerVytvorPlan = loader.getController();
                    controllerVytvorPlan.setStage(stage);
                    stage.showAndWait();
                    
                    if (controllerVytvorPlan.getClick()) {
                        vytvorRozPlan();
                        loadTables();
                        listviewVyucujici.getSelectionModel().clearSelection();
                        listVyucujiciPlan.getItems().clear();
                    }
                } catch (IOException ex) {
                    Alerts.showErrorAlert("Chyba", "Nepodarilo se otevrit okno!");
                }

            } else {
                Alerts.informationAlert("Information", "Vyberte předmět, který vložíte do plánu!");
            }
        } else {
            Alerts.informationAlert("Information", "Vyberte vyučujícího v listu vyučujících!");
        }
    }

    @FXML
    private void přidejPředmet(ActionEvent event) throws SQLException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Semestral_Databaze.class.getResource("FXMLPridejPredmet.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);
            Scene scene = new Scene(parent);
            stage.setTitle("Přidej předmět");
            stage.setScene(scene);
            controllerPredmet = loader.getController();
            controllerPredmet.setStage(stage);
            stage.showAndWait();
            if (controllerPredmet.getClick()) {
                pridejPredmet();
                loadTables();
            }
        } catch (IOException ex) {
            Alerts.showErrorAlert("Chyba", "Nepodarilo se otevrit okno!");
        }
    }

    @FXML
    private void smazPredmet(ActionEvent event) throws SQLException {
        if (listPredmetu.getSelectionModel().getSelectedItem() != null) {
            try {
                c.deletePredmet(listPredmetu.getSelectionModel().getSelectedItem());
                loadTables();
            } catch (SQLException ex) {
                Alerts.showErrorAlert("Chyba", "Smazání předmětu se nezdařilo, nejspíše je předmět ve studijním plánu a proto jej nemůžete smazat");
            }
        }
    }

    @FXML
    private void vymazVyucujiciho(ActionEvent event) throws SQLException {
        if (listviewVyucujici.getSelectionModel().getSelectedItem() != null) {
            try {
                c.deleteVyucujici(listviewVyucujici.getSelectionModel().getSelectedItem());
                loadTables();
            } catch (SQLException ex) {
                Alerts.showErrorAlert("Chyba", "Nepodařilo se vymazani vyučujiciho, pravděpodobně jste předtím nevymazali jeho rozvrh");
            }
        }
    }

    @FXML
    private void clickVyucujici(MouseEvent event) throws SQLException {
        if (listviewVyucujici.getSelectionModel().getSelectedItem() != null) {
            try {
                Vyucujici vyucujici = listviewVyucujici.getSelectionModel().getSelectedItem();
                listPracovist.getSelectionModel().clearSelection();
                fillTableRozvrhUcitele(c.getRozvrhovyPlanUcitele(vyucujici));
                if (vyucujici != null) {
                    listKOntakt.getItems().clear();
                    listKOntakt.getItems().add(c.dejKontaktProVyucujiciho(vyucujici));
                    this.kontaktEditace = c.dejKontaktProVyucujiciho(vyucujici);
                    listKOntakt.getItems().add(c.dejPracovisteVyucujiciho(vyucujici));
                }
            } catch (SQLException ex) {
                Alerts.showErrorAlert("Chyba", "Načtení iformací se nezdařilo!");
            }
        }

    }

    @FXML
    private void smazRozvrhovyPlan(ActionEvent event) {
        if (listVyucujiciPlan.getSelectionModel().getSelectedItem() != null) {
            RozvrhovyPlan rozvrhPlan = listVyucujiciPlan.getSelectionModel().getSelectedItem();
            try {
                c.deleteRozvrhovyPlan(rozvrhPlan);
                fillTableRozvrhUcitele(c.getRozvrhovyPlanUcitele(listviewVyucujici.getSelectionModel().getSelectedItem()));
                listPlanPredmetu.getItems().clear();
            } catch (SQLException ex) {
                Alerts.showErrorAlert("Chyba", "Nepodařilo se vymazani rozvrhového plánu");
            }
        }
    }

    @FXML
    private void nahrajData(ActionEvent event) throws SQLException {
        try {
            loadTables();
            btnPlan.setDisable(false);
            btnPridejPredmet.setDisable(false);
            btnPridejUcitele.setDisable(false);
            btnSmazPredmet.setDisable(false);
            btnSmazRozvrh.setDisable(false);
            btnSmazVuyc.setDisable(false);
            btnPridejObor.setDisable(false);
            btnStudijniPlan.setDisable(false);
            btnPridejPracoviste.setDisable(false);
            btnSmazPracoviste.setDisable(false);
            btnEditujPracoviste.setDisable(false);
            btnOdeberplan.setDisable(false);
            btnOdeberStudijniObor.setDisable(false);
            btnEditujPredmet.setDisable(false);
            btnEditujObor.setDisable(false);
            btnEditujUcitele.setDisable(false);
        } catch (SQLException ex) {
            Alerts.showErrorAlert("Chyba", "Nepodařilo se nahrát data z databáze");
        }
    }

    @FXML
    private void pridejObor(ActionEvent event) throws IOException, SQLException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Semestral_Databaze.class.getResource("FXMLPridejStudijniObor.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);
            Scene scene = new Scene(parent);
            stage.setTitle("Přidej obor");
            stage.setScene(scene);
            controllerPridejObor = loader.getController();
            controllerPridejObor.setStage(stage);
            stage.showAndWait();
            if (controllerPridejObor.getClick()) {
                pridejStudijniObor();
                loadTables();
            }
        } catch (SQLException | IOException ex) {
            Alerts.showErrorAlert("Chyba", "Přidavani oboru selhalo");
        }
    }

    @FXML
    private void pridejStudijniPlan(ActionEvent event) throws IOException, SQLException {
        if (listPredmetu.getSelectionModel().getSelectedItem() != null) {
            if (listStudijniObor.getSelectionModel().getSelectedItem() != null) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Semestral_Databaze.class.getResource("FXMLPridejStudijniPlan.fxml"));
                Parent parent = loader.load();
                Stage myStage = new Stage();
                myStage.initModality(Modality.WINDOW_MODAL);
                myStage.setResizable(false);
                Scene scene = new Scene(parent);
                myStage.setTitle("Přidej studijni plán");
                myStage.setScene(scene);
                controllerPridejPlan = loader.getController();
                controllerPridejPlan.setStage(myStage);

                myStage.showAndWait();
                if (controllerPridejPlan.getClickOk()) {
                    vytvorStudijniPlan();
                }
                listStudijniPlan.getItems().clear();
                listStudijniObor.getSelectionModel().clearSelection();
            } else {
                Alerts.informationAlert("Information", "Vyber studijní obor pro plán!");
            }
        } else {
            Alerts.informationAlert("Information", "Vyber předmět pro plán!");
        }
    }

    @FXML
    private void cliknulNaStudObor(MouseEvent event) throws SQLException {
        if (listStudijniObor.getSelectionModel().getSelectedItem() != null) {
            try {
                fillTableStudijniPlan(c.getStudijniPlanOboru(listStudijniObor.getSelectionModel().getSelectedItem()));
            } catch (SQLException ex) {
                Alerts.showErrorAlert("chyba", "nepodařilo se zobrazení studijního plánu!");
            }
        }
    }

    @FXML
    private void pridejPracoviste(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Semestral_Databaze.class.getResource("FXMLPridejPracoviste.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);
            Scene scene = new Scene(parent);
            stage.setTitle("Přidej pracoviště");
            stage.setScene(scene);
            controllerPridejPracoviste = loader.getController();
            controllerPridejPracoviste.setStage(stage);
            stage.showAndWait();
            if (controllerPridejPracoviste.getClick()) {
                pridejPracoviste();
                loadTables();
            }
        } catch (SQLException | IOException ex) {
            Alerts.showErrorAlert("Chyba", "Přidavani pracoviste selhalo");
        }
    }

    @FXML
    private void smazPracoviste(ActionEvent event) {
        if (listPracovist.getSelectionModel().getSelectedItem() != null) {
            try {
                c.deletePracoviste(listPracovist.getSelectionModel().getSelectedItem());
                loadTables();
            } catch (SQLException ex) {
                Alerts.showErrorAlert("Chyba", "Nepodařilo se vymazani rozvrhového plánu");
            }
        }
    }

    @FXML
    private void editujPracoviste(ActionEvent event) {
        if (listPracovist.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Semestral_Databaze.class.getResource("FXMLPridejPracoviste.fxml"));
                Parent parent = loader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.setResizable(false);
                Scene scene = new Scene(parent);

                stage.setTitle("Edituj pracoviště");
                stage.setScene(scene);
                controllerPridejPracoviste = loader.getController();
                controllerPridejPracoviste.setStage(stage);
                Pracoviste prac = listPracovist.getSelectionModel().getSelectedItem();
                controllerPridejPracoviste.setId(prac.getIdPracoviste());
                stage.setOnShown((eve)
                        -> {
                    controllerPridejPracoviste.getTextNazev().setText(prac.getNazev());
                    controllerPridejPracoviste.getTextZkratka().setText(prac.getZkratka());
                    controllerPridejPracoviste.getTextFakulta().setText(prac.getFakulta());
                    controllerPridejPracoviste.getTextPlnyNazev().setText(prac.getPlnyNazevFakulta());
                }
                );
                stage.showAndWait();
                if (controllerPridejPracoviste.getClick()) {
                    vytvorEditaciPracoviste();
                    loadTables();
                }
            } catch (SQLException | IOException ex) {
                Alerts.showErrorAlert("Chyba", "Editovani pracoviste selhalo");
            }
        } else {
            Alerts.showErrorAlert("Chyba", "Není vybráno pracoviště pro editaci!");
        }
    }

    @FXML
    private void odeberObor(ActionEvent event) {
        if (listStudijniObor.getSelectionModel().getSelectedItem() != null) {
            try {
                c.deleteObor(listStudijniObor.getSelectionModel().getSelectedItem());
                loadTables();
            } catch (SQLException ex) {
                Alerts.showErrorAlert("Chyba", "Nepodařilo se vymazani rozvrhového plánu, nejspíše existuje pro obor studijní plán!");
            }
        }
    }

    @FXML
    private void odeberPlan(ActionEvent event) {
        if (listStudijniPlan.getSelectionModel().getSelectedItem() != null) {
            try {
                c.deleteStudijniPlan(listStudijniPlan.getSelectionModel().getSelectedItem());
                listStudijniPlan.getItems().clear();
            } catch (SQLException ex) {
                Alerts.showErrorAlert("Chyba", "Nepodařilo se vymazani rozvrhového plánu, nejspíše existuje pro obor studijní plán!");
            }
        }
    }

    @FXML
    private void editujPredmet(ActionEvent event) {
        if (listPredmetu.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Semestral_Databaze.class.getResource("FXMLPridejPredmet.fxml"));
                Parent parent = loader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.setResizable(false);
                Scene scene = new Scene(parent);

                stage.setTitle("Edituj předmět");
                stage.setScene(scene);
                controllerPredmet = loader.getController();
                controllerPredmet.setStage(stage);
                Predmet predmet = listPredmetu.getSelectionModel().getSelectedItem();
                controllerPredmet.setId(predmet.getIdPredmet());
                stage.setOnShown((eve)
                        -> {
                    controllerPredmet.getTextForma().setText(predmet.getFormaVyuky());
                    controllerPredmet.getTextKapacita().setText(Integer.toString(predmet.getKapacita()));
                    controllerPredmet.getTextNazev().setText(predmet.getNazev());
                    controllerPredmet.getTextZakonceni().setText(predmet.getZpusobZakonceni());
                    controllerPredmet.getTextZkratka().setText(predmet.getZkratka());
                }
                );
                stage.showAndWait();
                if (controllerPredmet.getClick()) {
                    vytvorEditaciPredmet();
                    loadTables();
                }
            } catch (SQLException | IOException ex) {
                Alerts.showErrorAlert("Chyba", "Editovani předmětu selhalo");
            }
        } else {
            Alerts.showErrorAlert("Chyba", "Není vybráno předmět pro editaci!");
        }
    }

    @FXML
    private void editujObor(ActionEvent event) {
        if (listStudijniObor.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Semestral_Databaze.class.getResource("FXMLPridejStudijniObor.fxml"));
                Parent parent = loader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.setResizable(false);
                Scene scene = new Scene(parent);

                stage.setTitle("Edituj obor");
                stage.setScene(scene);
                controllerPridejObor = loader.getController();
                controllerPridejObor.setStage(stage);
                StudijniObor obor = listStudijniObor.getSelectionModel().getSelectedItem();
                controllerPridejObor.setId(obor.getIdStudijniObor());
                stage.setOnShown((eve)
                        -> {
                    controllerPridejObor.getTextNazev().setText(obor.getNazevOboru());
                    controllerPridejObor.getTextSlozeniPlanu().setText(obor.getSlozeniPlanu());
                    controllerPridejObor.getTextOdhad().setText(Integer.toString(obor.getOdhadPoctuStudentu()));
                }
                );
                stage.showAndWait();
                if (controllerPridejObor.getClick()) {
                    vytvorEditaciObor();
                    loadTables();
                }
            } catch (SQLException | IOException ex) {
                Alerts.showErrorAlert("Chyba", "Editovani oboru selhalo");
            }
        } else {
            Alerts.showErrorAlert("Chyba", "Není vybrán obor pro editaci!");
        }
    }

    @FXML
    private void editujUcitele(ActionEvent event) {
        if (listviewVyucujici.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Semestral_Databaze.class.getResource("FXMLPridejUcitele.fxml"));
                Parent parent = loader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.setResizable(false);
                Scene scene = new Scene(parent);
                stage.setTitle("Edituj učitele");
                stage.setScene(scene);
                controllerUcitel = loader.getController();
                controllerUcitel.setStage(stage);
                Vyucujici vyucujici = listviewVyucujici.getSelectionModel().getSelectedItem();
                controllerUcitel.setId(vyucujici.getIdVyucujici());
                controllerUcitel.setIdKontakt(vyucujici.getIdKontakt());
                stage.setOnShown((eve)
                        -> {
                    controllerUcitel.getTxtJmeno().setText(vyucujici.getJmeno());
                    controllerUcitel.getTxtEmail().setText(kontaktEditace.getEmail());
                    controllerUcitel.getTxtMobil().setText(Integer.toString(kontaktEditace.getMobil()));
                    controllerUcitel.getTxtPred().setText(vyucujici.getTitulPred());
                    controllerUcitel.getTxtPrijmeni().setText(vyucujici.getPrijmeni());
                    controllerUcitel.getTxtTelefon().setText(Integer.toString(kontaktEditace.getTelefon()));
                    controllerUcitel.getTxtZa().setText(vyucujici.getTitulZa());
                }
                );
                stage.showAndWait();
                if (controllerUcitel.getClick()) {
                    editujUciteleKontakt();
                    listKOntakt.getItems().clear();
                    listviewVyucujici.getSelectionModel().clearSelection();
                    loadTables();
                }
            } catch (SQLException | IOException ex) {
                Alerts.showErrorAlert("Chyba", "Editovani učitele selhalo");
            }
        } else {
            Alerts.showErrorAlert("Chyba", "Není vybrán učitel pro editaci!");
        }
    }

    @FXML
    private void clickPredmet(MouseEvent event) {
        if (listPredmetu.getSelectionModel().getSelectedItem() != null) {
            try {
                fillListProPredmet(c.getFiltrovanePredmety(listPredmetu.getSelectionModel().getSelectedItem()));
            } catch (Exception ex) {
                Alerts.showErrorAlert("Chyba", "Nepodařilo se zobrazit vyfiltrovane plány pro předmět!");
            }
        }
    }
}
