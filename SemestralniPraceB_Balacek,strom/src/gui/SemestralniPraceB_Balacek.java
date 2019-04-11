
package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SemestralniPraceB_Balacek extends Application {
    
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }
    
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setTitle("Evidence zaměstnanců ve firmě");
        stage.setScene(scene);
        stage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
