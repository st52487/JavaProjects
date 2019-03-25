/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestral_databaze;

import data.Kontakt;
import data.Vyucujici;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import knihovna.Alerts;

public class FXMLPridejUciteleController implements Initializable {

    @FXML
    private Button btnOk;
    @FXML
    private TextField txtPrijmeni;
    @FXML
    private TextField txtJmeno;
    @FXML
    private TextField txtZa;
    @FXML
    private TextField txtPred;
    @FXML
    private TextField txtTelefon;
    @FXML
    private TextField txtMobil;
    @FXML
    private TextField txtEmail;

    
    /*--------------inicializace vlastncih promenych -------------*/
    private Stage stage;
    private Vyucujici vyucujici;
    private boolean clickOk = false;
    private int id = 0;
    private int idKontakt = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    
    /*------------- event handlery---------------*/
    @FXML
    private void clickOk(ActionEvent event) {
        try {
            this.vyucujici = new Vyucujici(id, txtJmeno.getText(), txtPrijmeni.getText(),
                    " " +txtZa.getText(), " " +txtPred.getText(), new Kontakt(idKontakt, Integer.parseInt(txtTelefon.getText()),
                    Integer.parseInt(txtMobil.getText()), txtEmail.getText()));
            this.clickOk = true;
        } catch (Exception ex) {
            Alerts.showErrorAlert("Chyba", "Jiny datový typ než očekáván");
        }
        stage.close();
    }

    
    /*--------------- gettery, settery ----------------*/
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Vyucujici getVyucujici() {
        return this.vyucujici;
    }

    public boolean getClick() {
        return clickOk;
    }

    void setId(int idVyucujici) {
        this.id = idVyucujici;
    }

    public TextField getTxtPrijmeni() {
        return txtPrijmeni;
    }

    public TextField getTxtJmeno() {
        return txtJmeno;
    }

    public TextField getTxtZa() {
        return txtZa;
    }

    public TextField getTxtPred() {
        return txtPred;
    }

    public TextField getTxtTelefon() {
        return txtTelefon;
    }

    public TextField getTxtMobil() {
        return txtMobil;
    }

    public TextField getTxtEmail() {
        return txtEmail;
    }

    void setIdKontakt(int idKontakt) {
        this.idKontakt = idKontakt;
    }

}
