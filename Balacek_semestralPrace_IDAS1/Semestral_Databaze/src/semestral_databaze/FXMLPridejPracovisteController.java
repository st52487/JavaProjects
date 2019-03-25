package semestral_databaze;

import data.Pracoviste;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import knihovna.Alerts;

public class FXMLPridejPracovisteController implements Initializable {

    @FXML
    private Button btnOk;

    private Stage stage;
    private Pracoviste pracoviste;
    private boolean clickOk = false;
    @FXML
    private TextField textNazev;
    @FXML
    private TextField textZkratka;
    @FXML
    private TextField textFakulta;
    @FXML
    private TextField textPlnyNazev;
    
    /*-------------- inicializace vlastnich promenych -----------*/
    private int id = 0;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }
    
    /*------------------- event handlery --------------------*/ 

    @FXML
    private void clickOk(ActionEvent event) {
        try {
            pracoviste = new Pracoviste(id, textZkratka.getText(), textNazev.getText(), textFakulta.getText(),
                    textPlnyNazev.getText());
            this.clickOk = true;
            stage.close();
        } catch (Exception ex) {
            this.clickOk = false;
            Alerts.showErrorAlert("Chyba", "Parsování dat se nezdařilo");
        }

    }

    /* ----------------- gettery, settery ---------------- */ 
    public TextField getTextNazev() {
        return textNazev;
    }

    public TextField getTextZkratka() {
        return textZkratka;
    }

    public TextField getTextFakulta() {
        return textFakulta;
    }

    public TextField getTextPlnyNazev() {
        return textPlnyNazev;
    }
    
    

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    Pracoviste getPracoviste() {
        return this.pracoviste;
    }

    boolean getClick() {
        return clickOk;
    }

    void setPracoviste(Pracoviste selectedItem) {
        this.pracoviste = selectedItem;
    }

    void setId(int idPracoviste) {
        this.id = idPracoviste;
    }
    

}
