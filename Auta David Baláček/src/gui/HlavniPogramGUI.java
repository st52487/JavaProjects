package gui;

import seznam.Adapter;
import automobily.Automobil;
import automobily.TypAutomobilu;
import java.util.Iterator;
import java.util.function.Predicate;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static gui.Komponenty.*;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;

public class HlavniPogramGUI extends Application {

    private ListView<Automobil> list;
    private final Adapter ADAPTER = Adapter.getInstance(100);
    private final ToggleGroup GROUP = new ToggleGroup();

    private DialogNovy_Zmenit dialogNovy;

    private Button btnUloz;
    private Button btnSmaz;
    private Button btnTest;
    private Button btnObnov;
    private Button btnNovy;
    private Button btnEdituj;

    private Iterator iterator;

    private RadioButton osobni;
    private RadioButton tahac;
    private RadioButton dodavka;
    private RadioButton nakladni;
    private RadioButton valnik;
    private RadioButton zadny;
    private Separator verticalSeparator;
    private Separator horizontalSeparator;

    private BorderPane borderPane;
    private HBox hbox;
    private VBox vb;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Evidence automobilů");

        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(500);

        borderPane = new BorderPane();
        hbox = new HBox(10);
        vb = new VBox(10);
        list = new ListView<>();
        vb.setPadding(new Insets(10, 10, 10, 10));
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setAlignment(Pos.CENTER);
        verticalSeparator = addSeparator(Orientation.VERTICAL);
        horizontalSeparator = addSeparator(Orientation.HORIZONTAL);

        osobni = addRadioButton("Osobní Automobil", GROUP, filtr(), false);
        tahac = addRadioButton("Tahač", GROUP, filtr(), false);
        valnik = addRadioButton("Valník", GROUP, filtr(), false);
        dodavka = addRadioButton("Dodávka", GROUP, filtr(), false);
        nakladni = addRadioButton("Nákladní Automobil", GROUP, filtr(), false);
        zadny = addRadioButton("Žádný filtr", GROUP, filtr(), true);

        btnUloz = addButton("Ulož", 100, 20, buttonUlozAction());
        btnSmaz = addButton("Smaž", 100, 20, buttonSmazAction());
        btnTest = addButton("Test", 100, 20, buttonTestAction());
        btnObnov = addButton("Obnov", 100, 20, buttonObnovAction());
        btnNovy = addButton("Nový", 100, 20, buttonNovyAction());
        btnEdituj = addButton("Edituj", 100, 20, buttonEditujAction());

        hbox.getChildren().addAll(osobni, dodavka, nakladni,tahac,valnik, zadny);
        vb.getChildren().addAll(btnUloz, btnSmaz, btnTest, btnObnov, btnNovy,
                btnEdituj);

        borderPane.setTop(hbox);
        borderPane.setCenter(list);
        borderPane.setRight(vb);
        borderPane.setLeft(verticalSeparator);
        borderPane.setBottom(horizontalSeparator);

        Scene scene = new Scene(borderPane, 1100, 740);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private EventHandler<ActionEvent> buttonUlozAction() {
        return (event) -> {
            ADAPTER.serializuj();
        };
    }

    private EventHandler<ActionEvent> buttonObnovAction() {
        return (event) -> {
            ADAPTER.deserializuj();
            aktualizuj();
        };
    }

    private EventHandler<ActionEvent> buttonNovyAction() {
        return (event) -> {
            dialogNovy = new DialogNovy_Zmenit(ADAPTER, null);
            aktualizuj();
        };
    }

    private EventHandler<ActionEvent> buttonEditujAction() {
        return (event) -> {
            Automobil autmobil = list.getSelectionModel().getSelectedItem();
            if (autmobil != null) {
                dialogNovy = new DialogNovy_Zmenit(ADAPTER, autmobil);
            }
            aktualizuj();
        };
    }

    private EventHandler<ActionEvent> buttonSmazAction() {
        return (event) -> {
            Automobil autmobil = list.getSelectionModel().getSelectedItem();
            if (autmobil != null) {
                ADAPTER.smaz(autmobil);
            }

            aktualizuj();
        };
    }

    private EventHandler<ActionEvent> buttonTestAction() {
        return (event) -> {
            list.getItems().clear();
            ADAPTER.clickTest();
            nacti();
        };
    }

    private void nacti() {
        iterator = getIterator();
        while (iterator.hasNext()) {
            list.getItems().add((Automobil) iterator.next());
        }
    }

    private Iterator getIterator() {
        return iterator = ADAPTER.getSeznam().iterator();
    }

    private void aktualizuj() {
        list.getItems().clear();
        iterator = getIterator();
        while (iterator.hasNext()) {
            list.getItems().add((Automobil) iterator.next());
        }
    }

    private EventHandler<ActionEvent> filtr() {
        return (event) -> {
            Predicate<Automobil> predicate = null;
            if (osobni.isSelected()) {
                predicate = t -> t.getTyp() == TypAutomobilu.OSOBNI;
            } else if (dodavka.isSelected()) {
                predicate = t -> t.getTyp() == TypAutomobilu.DODAVKA;
            } else if (nakladni.isSelected()) {
                predicate = t -> t.getTyp() == TypAutomobilu.NAKLADNI;
            }else if (valnik.isSelected()) {
                predicate = t -> t.getTyp() == TypAutomobilu.VALNIK;
            }else if (tahac.isSelected()) {
                predicate = t -> t.getTyp() == TypAutomobilu.TAHAC;
            }
            else if (zadny.isSelected()) {
                predicate = t -> t != null;
            }

            ADAPTER.vypisFiltrovanyAutomobil(list, predicate);
        };
    }
}
