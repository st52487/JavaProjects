package semestral_databaze;

import enumy.DruhKategorie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;


public class FXMLPridejStudijniPlanController implements Initializable {
 
    @FXML
    private ComboBox<DruhKategorie> cbBox;
    
    /*-------------- inicializace vlastnich promenych ---------*/ 
    private Stage stage;
    private boolean clickOk = false;
    private String druh;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbBox.getItems().addAll(DruhKategorie.values());
        cbBox.getSelectionModel().selectFirst();
    }    

    /*----------- event handlery -------------*/
    @FXML
    private void clickOk(ActionEvent event) {
        this.druh = cbBox.getSelectionModel().getSelectedItem().name();
        this.clickOk = true;
        this.stage.close();
    }
    
    /*--------- gettery, settery --------------*/
    
    public void setStage(Stage myStage) {
        this.stage = myStage;
    }

    
    public boolean getClickOk(){
        return clickOk;
    }
    
     public String getDruh(){
        return druh;
    }
    
}
