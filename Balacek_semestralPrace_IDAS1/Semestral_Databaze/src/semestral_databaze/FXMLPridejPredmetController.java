package semestral_databaze;

import data.Predmet;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import knihovna.Alerts;


public class FXMLPridejPredmetController implements Initializable {

    @FXML
    private Button btnOk;
    @FXML
    private TextField textNazev;
    @FXML
    private TextField textZkratka;
    @FXML
    private TextField textZakonceni;
    @FXML
    private TextField textForma;
    @FXML
    private TextField textKapacita;
    
    
    /*---------inicializace vlastnich promenych ------------ */
    private Stage stage;
    private Predmet predmet;
    private boolean clickOk = false;
    private int id = 0;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    /*----------------------- event handlery -----------------*/
    @FXML
    private void clickOk(ActionEvent event) {
        try{
        this.predmet = new Predmet(id, textNazev.getText(), textZkratka.getText(), 
                textZakonceni.getText(), textForma.getText(), Integer.parseInt(textKapacita.getText()));
        this.clickOk = true;
        }catch(Exception ex){
            this.clickOk = false;
            Alerts.showErrorAlert("Chyba", "Parsování dat se nezdařilo!");
        }
        stage.close();
    }
    
    /*---------------------- gettery, settery -----------------*/

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Predmet getPredmet() {
        return this.predmet;
    }    

    boolean getClick() {
        return clickOk;
    }

    void setId(int idPredmet) {
        this.id = idPredmet;
    }

    public TextField getTextNazev() {
        return textNazev;
    }

    public TextField getTextZkratka() {
        return textZkratka;
    }

    public TextField getTextZakonceni() {
        return textZakonceni;
    }

    public TextField getTextForma() {
        return textForma;
    }

    public TextField getTextKapacita() {
        return textKapacita;
    }
}
