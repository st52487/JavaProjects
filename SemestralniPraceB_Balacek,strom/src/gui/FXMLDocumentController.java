package gui;

import knihovna.Alerts;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import adapter.Spravce;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import objekty.IPobocka;
import objekty.Zamestnanec;
import objekty.Pozice;
import objekty.TypPozice;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Button btnPridejPobocku;

    private Spravce spravce;
    private Iterator<IPobocka> iteratorPobocek;
    private Iterator<Zamestnanec> iteratorZamestnanec;
    private Iterator<Pozice> iteratorPozice;
    @FXML
    private Button btnOdeberPobocku;
    @FXML
    private ListView<IPobocka> listPobocek;
    @FXML
    private ListView<Pozice> listPracovniku;
    @FXML
    private ListView<Zamestnanec> listZamestnancu;
    @FXML
    private Button btnGeneruj;
    @FXML
    private Button btrnPridejPracovnika;
    @FXML
    private Button btnPridejZamestnance;
    @FXML
    private ComboBox<TypPozice> cbBox;
    @FXML
    private Button btnZrus;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        spravce = Spravce.getInstance();
        cbBox.getItems().add(TypPozice.REDITEL);
        cbBox.getSelectionModel().selectFirst();

    }

    @FXML
    private void pridejPobocku(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SemestralniPraceB_Balacek.class.getResource("FXMLPobocka.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(SemestralniPraceB_Balacek.getStage());
            stage.setResizable(false);
            Scene scene = new Scene(parent);
            stage.setTitle("Přidej pobočku");
            stage.setScene(scene);
            FXMLPobockaController controller = loader.getController();
            controller.setDialogStage(stage);
            stage.showAndWait();
        } catch (IOException ex) {
            Alerts.showErrorAlert("Chyba", "Nepodarilo se otevrit okno!");
        }
        aktualizujListPobocek();
    }

    private void aktualizujListPobocek() {
        listPobocek.getItems().clear();
        iteratorPobocek = spravce.iteratorPobocka();
        while (iteratorPobocek.hasNext()) {
            listPobocek.getItems().add(iteratorPobocek.next());
        }
    }

    private void aktualizujListZamestnancu() {
        listZamestnancu.getItems().clear();
        iteratorZamestnanec = spravce.iteratorZamestnanci();
        while (iteratorZamestnanec.hasNext()) {
            listZamestnancu.getItems().add(iteratorZamestnanec.next());
        }
    }

    private void aktualizujListPozic() {
        listPracovniku.getItems().clear();
        cbBox.getItems().clear();
        cbBox.getItems().add(TypPozice.REDITEL);
        cbBox.getSelectionModel().selectFirst();
        if (listPobocek.getSelectionModel().getSelectedItem() != null) {
            IPobocka pobocka = listPobocek.getSelectionModel().getSelectedItem();
            if (pobocka.jePrazdna() == false) {
                iteratorPozice = spravce.iteratorPozice(pobocka);
                Pozice pomPozice = null;
                Pozice pomPozice1 = null;
                while (iteratorPozice.hasNext()) {
                    Pozice pozice = iteratorPozice.next();
                    switch (pozice.getTypPozice()) {
                        case REDITEL:
                            listPracovniku.getItems().add(0, pozice);
                            break;
                        case VEDOUCIPOBOCKY:
                            listPracovniku.getItems().add(1, pozice);
                            if (listPracovniku.getItems().size() == 2 && pomPozice != null) {
                                listPracovniku.getItems().add(2, pomPozice);
                            }
                            if (listPracovniku.getItems().size() == 3 && pomPozice1 != null) {
                                listPracovniku.getItems().add(3, pomPozice1);
                            }
                            break;
                        case VEDOUCIUSEKU:
                            if (listPracovniku.getItems().size() == 1) {
                                pomPozice = pozice;
                            } else {
                                listPracovniku.getItems().add(2, pozice);
                            }
                            break;
                        case PRACOVNIK:
                            if (listPracovniku.getItems().size() == 2 || listPracovniku.getItems().size() == 1) {
                                pomPozice1 = pozice;
                            } else {
                                listPracovniku.getItems().add(3, pozice);
                            }
                            break;
                    }
                }
            }
        }
    }

    @FXML
    private void odeberPobocku(ActionEvent event) {
        if (listPobocek.getSelectionModel().getSelectedItem() != null) {
            try {
                IPobocka pobocka = listPobocek.getSelectionModel().getSelectedItem();
                spravce.odeberPobocku(pobocka.getNazevPobocky());
            } catch (NullPointerException ex) {
                Alerts.showErrorAlert("Chyba!", "Odebrání pobocky se nezdarilo!");
            }
            aktualizujListPobocek();
            listPracovniku.getItems().clear();
        }
    }

    @FXML
    private void generujData(ActionEvent event) {
        spravce.generujData();
        aktualizujListZamestnancu();
        aktualizujListPobocek();
    }

    @FXML
    private void pridejZamestnance(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SemestralniPraceB_Balacek.class.getResource("FXMLPridejZamestnance.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(SemestralniPraceB_Balacek.getStage());
            stage.setResizable(false);
            Scene scene = new Scene(parent);
            stage.setTitle("Přidej zaměstnance");
            stage.setScene(scene);
            FXMLPridejZamestnanceController controller = loader.getController();
            controller.setDialogStage(stage);
            stage.showAndWait();
        } catch (IOException ex) {
            Alerts.showErrorAlert("Chyba", "Nepodarilo se otevrit okno!");
        }
        aktualizujListZamestnancu();
    }

    @FXML
    private void clickListPobocek(MouseEvent event) {
        aktualizujListPozic();
    }

    @FXML
    private void pridejPotomka(ActionEvent event) {
        Pozice pozice = listPracovniku.getSelectionModel().getSelectedItem();
        Zamestnanec zamestnanec = listZamestnancu.getSelectionModel().getSelectedItem();
        IPobocka pobocka = listPobocek.getSelectionModel().getSelectedItem();
        if (pobocka != null) {
            if (zamestnanec != null) {
                if (pozice == null) {
                    pozice = new Pozice(TypPozice.REDITEL, zamestnanec);
                    spravce.vlozRiditele(pozice, pobocka);
                } else {
                    spravce.pridejNovehoPotomka(cbBox.getSelectionModel().getSelectedItem(), zamestnanec, pobocka, pozice);
                }
                aktualizujListPozic();
            } else {
                Alerts.informationAlert("Information", "Nebyl vybrán zaměstnanec");
            }
        } else {
            Alerts.informationAlert("information", "Nebyla vybrána pobočka");
        }
    }

    @FXML
    private void clickListPracovniku(MouseEvent event) {
        Pozice pozice = listPracovniku.getSelectionModel().getSelectedItem();
        if (pozice != null) {
            switch (pozice.getTypPozice()) {
                case REDITEL:
                    cbBox.getItems().clear();
                    cbBox.getItems().add(TypPozice.VEDOUCIPOBOCKY);
                    cbBox.getSelectionModel().selectFirst();
                    break;
                case VEDOUCIPOBOCKY:
                    cbBox.getItems().clear();
                    cbBox.getItems().add(TypPozice.VEDOUCIUSEKU);
                    cbBox.getSelectionModel().selectFirst();
                    break;
                case VEDOUCIUSEKU:
                    cbBox.getItems().clear();
                    cbBox.getItems().add(TypPozice.PRACOVNIK);
                    cbBox.getSelectionModel().selectFirst();
                    break;
                case PRACOVNIK:
                    cbBox.getItems().clear();
                    break;
            }
        }
    }

    @FXML
    private void zrusPozice(ActionEvent event) {
        IPobocka pobocka = listPobocek.getSelectionModel().getSelectedItem();
        if (pobocka != null) {
            spravce.zrusPozice(pobocka);
            listPracovniku.getItems().clear();
        }
    }

    @FXML
    private void clickGeneruj600Zamestnancu(ActionEvent event) {
        spravce.generujZamest();
        aktualizujListZamestnancu();
    }
}
