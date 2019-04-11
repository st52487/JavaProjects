package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import knihovna.Alerts;
import zpracovani.IVyrobniProces;
import zpracovani.VyrobniProces;
import vycty.enumReorg;

public class FXMLReorganizujController implements Initializable {

    @FXML
    private Label label1;
    @FXML
    private TextField textField;
    @FXML
    private ComboBox<enumReorg> comboBox;
    private Stage stage;
    private ObservableList<enumReorg> reorg;
    @FXML
    private Button btnOK;
    private IVyrobniProces VYROBNI_PROCES;
    @FXML
    private Label labelHodnota;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reorg = FXCollections.observableArrayList(enumReorg.values());
        comboBox.getItems().addAll(reorg);
        comboBox.getSelectionModel().selectLast();
        VYROBNI_PROCES = VyrobniProces.getInstance();
    }

    void setDialogStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void clickOk(ActionEvent event) {
        try{
              VYROBNI_PROCES.reorganizuj(Integer.parseInt(textField.getText()),
                comboBox.getSelectionModel().getSelectedItem());
        }catch(NumberFormatException ex){
            Alerts.showErrorAlert("Chyba!", "Okno není správně vyplněno!!");
        }
        stage.close();
    }

    @FXML
    private void comboBoxAction(ActionEvent event) {
        switch(comboBox.getSelectionModel().getSelectedItem()){
            case AGREGACE:
                labelHodnota.setText("Agreguj vše menší jak <");
                break;
            case DEKOMPOZICE:
                labelHodnota.setText("Dekompozicuj větší jak >");
                break;
        }
    }

}
