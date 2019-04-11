package gui;

import adapter.Spravce;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import knihovna.Alerts;
import objekty.Pobocka;
import objekty.TypPozice;

public class FXMLPridejZamestnanceController implements Initializable {

    @FXML
    private TextField textJmeno;
    @FXML
    private TextField textPrijmeni;
    @FXML
    private TextField textEmail;
    @FXML
    private Button btnOk;
    
    private Spravce spravce;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        spravce = Spravce.getInstance();
        textJmeno.setText("Pepa");
        textPrijmeni.setText("Bořivoj");
        textEmail.setText("borivoj@seznam.cz");
    }


    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void clickOK(ActionEvent event) {
        try{
        spravce.pridejZamestnance(textJmeno.getText(), textPrijmeni.getText(), textEmail.getText());
        }catch(NullPointerException ex){
            Alerts.showErrorAlert("Chyba!", "Nepodarilo se přidat pobočku");
        }
        stage.close();
    }
}
