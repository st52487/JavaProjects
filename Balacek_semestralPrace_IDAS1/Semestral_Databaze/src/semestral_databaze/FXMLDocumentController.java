/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestral_databaze;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author caval
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField textFieldId;
    @FXML
    private TextField textFieldHeslo;
    @FXML
    private Button btnOk;
    @FXML
    private Button btnReset;

    /* Inicializace */
    private Controller c = new Controller();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textFieldId.setText("C##ST52487");
    }

    private static Stage myStage;

    public static Stage getMyStage() {
        return myStage;
    }

    /*---------------- action items, kliknutí na buttony ---------------------------------*/ 
    @FXML
    private void clickOk(ActionEvent event) throws IOException {
        if (c.login(textFieldId.getText(), textFieldHeslo.getText())) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Semestral_Databaze.class.getResource("FXMLMain.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage();
            myStage = stage;
            stage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(parent);
            stage.setTitle("Evidence vyučujících");
            stage.setScene(scene);
            stage.setResizable(false);
            FXMLMainController controller = loader.getController();
            controller.setController(c);
            Semestral_Databaze.getStage().close();
            stage.showAndWait();
        }
    }

    @FXML
    private void clickReset(ActionEvent event) {
        textFieldHeslo.setText("");
        textFieldId.setText("C##ST52487");
    }

}
