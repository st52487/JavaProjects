/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestral_databaze;

import data.StudijniObor;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import knihovna.Alerts;

/**
 * FXML Controller class
 *
 * @author caval
 */
public class FXMLPridejStudijniOborController implements Initializable {

    private Stage stage;
    @FXML
    private Button btnOK;
    @FXML
    private TextField textNazev;
    @FXML
    private TextField textOdhad;
    @FXML
    private TextArea textSlozeniPlanu;

    
    /* --------------inicializace vlastnich promenych ------------- */
    private StudijniObor studijniObor;
    private boolean clickOk = false;
    private int id = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /*---------------event handlery -----------------*/
    @FXML
    private void clickOK(ActionEvent event) throws SQLException {
        try {
            this.studijniObor = new StudijniObor(id, textSlozeniPlanu.getText(), textNazev.getText(), Integer.parseInt(textOdhad.getText()));
            this.clickOk = true;
        } catch (Exception ex) {
            Alerts.showErrorAlert("Chyba", "Parsování dat se nezdařilo!");
        }
        stage.close();
    }
    
    /*---------------------- gettery, settery ----------------*/
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public StudijniObor getStudijniObor() {
        return this.studijniObor;
    }

    public TextField getTextNazev() {
        return textNazev;
    }

    public TextField getTextOdhad() {
        return textOdhad;
    }

    public TextArea getTextSlozeniPlanu() {
        return textSlozeniPlanu;
    }
    
    

    boolean getClick() {
        return clickOk;
    }

    void setId(int idStudijniObor) {
        this.id = idStudijniObor;
    }

}
