package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import adapter.Spravce;
import knihovna.Alerts;
import objekty.IPobocka;
import objekty.Pobocka;
import objekty.Pozice;
import objekty.TypPozice;
import objekty.Zamestnanec;

public class FXMLPobockaController implements Initializable {

    private Stage stage;
    @FXML
    private Button btnOk;
    @FXML
    private Label label;
    @FXML
    private Label label1;
    @FXML
    private TextField textNazev;
    @FXML
    private TextField textMesto;

    private Spravce spravce;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        spravce = Spravce.getInstance();
        textMesto.setText("Pardubice");
        textNazev.setText("O2");
    }

    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void clickOk(ActionEvent event) {
        pridejPobocku();
        stage.close();
    }

    private void pridejPobocku() {
        try {
            IPobocka pobocka = new Pobocka(textNazev.getText(), textMesto.getText());
            spravce.getFirma().vloz(pobocka.getNazevPobocky(), pobocka);
        } catch (NullPointerException ex) {
            Alerts.showErrorAlert("Chyba", "Nepodařilo se přidaní pobočky");
        }
    }
}
