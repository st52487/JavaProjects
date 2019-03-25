package semestral_databaze;

import enumy.ZpusobVyukyEnum;
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


public class FXMLVytvorRozvrhPlanController implements Initializable {

    @FXML
    private TextField textRozsahHodin;
    @FXML
    private Button btnOk;
    @FXML
    private ComboBox<ZpusobVyukyEnum> cbBox;
    
    /*--------------inicializace vlastnich promenych ------------*/
    private Stage stage;
    private int rozsahHodin;
    private ZpusobVyukyEnum zpusobVyuky;
    private String typ;
    private boolean clickOK = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbBox.getItems().addAll(zpusobVyuky.values());
        cbBox.getSelectionModel().selectFirst();
    }

    /*--------------- event handlery----------*/
    @FXML
    private void clickOK(ActionEvent event) {
        try {
            this.rozsahHodin = Integer.parseInt(textRozsahHodin.getText());
            this.typ = cbBox.getSelectionModel().getSelectedItem().name();
            this.clickOK = true;
        } catch (Exception ex) {
            clickOK = false;
            Alerts.showErrorAlert("Chyba", "Jiny datový typ než očekáván!");
        }
        stage.close();
    }

    
    /*------------------ gettery, settery -------------------*/
    public String getTypVyuky() {
        return typ;
    }

    public ZpusobVyukyEnum getZpusobVyuky() {
        return zpusobVyuky;
    }

    
    public int getRozsahHodin() {
        return rozsahHodin;
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }

    boolean getClick() {
        return clickOK;
    }

    public ComboBox<ZpusobVyukyEnum> getCbBox() {
        return cbBox;
    }
}
