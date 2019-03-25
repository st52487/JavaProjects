package gui;

import alert.ChybovaHlaska;
import seznam.Adapter;
import automobily.Automobil;
import automobily.BarvaAuta;
import automobily.Dodavka;
import automobily.NakladniAutomobil;
import automobily.OsobniAutomobil;
import automobily.Tahac;
import automobily.TypAutomobilu;
import automobily.Valnik;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import kolekce.KolekceException;
import static gui.Komponenty.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class DialogNovy_Zmenit extends Stage {

    private GridPane grid;
    private final Adapter adapter;
    private final Automobil automobily;
    private ComboBox<TypAutomobilu> cbTypAutomobilu;
    private TextField tfPocetKol;
    private ComboBox<BarvaAuta> cbBarvaAuta;
    private TextField tfCtvrtyParametr;
    private TextField tfPatyParametr;
    private TextField tfmaxPocetPolozek;
    private CheckBox cbPatyParametr;
    private CheckBox cbJede;
    private Button btOK;

    public DialogNovy_Zmenit(Adapter adapter, Automobil automobily) {
        this.adapter = adapter;
        this.automobily = automobily;
        vytvorDialog();
    }

    private void vytvorDialog() {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        vytvor();

        btOK = addButton("OK", (event) -> {
            buttonOKAction();
        });

        box.getChildren().addAll(grid, btOK);

        setTitle("Nový automobil");
        Scene scene = new Scene(box, 400, 350);
        setResizable(false);
        setScene(scene);
        showAndWait();
    }

    private void buttonOKAction() {
        if (automobily != null) {
            nastavTyp();
        } else {
            try {
                pridejAuto();
            } catch (KolekceException | NumberFormatException ex) {
                ChybovaHlaska.zobrazAlert("Nepodařilo se vytvořit auto!");
                return;
            }
        }
        close();
    }

    private void pridejAuto() throws KolekceException {
        if (null != cbTypAutomobilu.getSelectionModel().getSelectedItem()) {
            switch (cbTypAutomobilu.getSelectionModel().getSelectedItem()) {
                case OSOBNI:
                    osobniPridej();
                    break;
                case DODAVKA:
                    dodavkaPridej();
                    break;
                case NAKLADNI:
                    nakladniPridej();
                    break;
                case TAHAC:
                    tahacPridej();
                    break;
                case VALNIK:
                    valnikPridej();
                    break;
            }
        }
    }
    
    private void tahacPridej() throws KolekceException {
        adapter.clickPridejTahac(Integer.parseInt(tfmaxPocetPolozek.getText()),
                cbJede.isSelected(),TypAutomobilu.TAHAC,
                Integer.parseInt(tfPocetKol.getText()),
                cbBarvaAuta.getSelectionModel().getSelectedItem(),
                tfCtvrtyParametr.getText(),
                cbPatyParametr.isSelected());
    }


    private void valnikPridej() throws KolekceException {
        adapter.clickPridejValnik(Integer.parseInt(tfmaxPocetPolozek.getText()),
                cbJede.isSelected(),TypAutomobilu.VALNIK,
                Integer.parseInt(tfPocetKol.getText()),
                cbBarvaAuta.getSelectionModel().getSelectedItem(),
                Integer.parseInt(tfCtvrtyParametr.getText()),
                tfPatyParametr.getText()
        );
    }

    private void osobniPridej() throws KolekceException {
        adapter.clickPridejOsobni(Integer.parseInt(tfmaxPocetPolozek.getText()),
                cbJede.isSelected(),TypAutomobilu.OSOBNI,
                Integer.parseInt(tfPocetKol.getText()),
                cbBarvaAuta.getSelectionModel().getSelectedItem(),
                tfCtvrtyParametr.getText(),
                cbPatyParametr.isSelected());
    }

    private void dodavkaPridej() throws KolekceException {
        adapter.clickPridejDodavka(Integer.parseInt(tfmaxPocetPolozek.getText()),
                cbJede.isSelected(),TypAutomobilu.DODAVKA,
                Integer.parseInt(tfPocetKol.getText()),
                cbBarvaAuta.getSelectionModel().getSelectedItem(),
                Integer.parseInt(tfCtvrtyParametr.getText()),
                tfPatyParametr.getText()
        );
    }

    private void nakladniPridej() throws KolekceException {
        adapter.clickPridejNakladni(Integer.parseInt(tfmaxPocetPolozek.getText()),
                cbJede.isSelected(),TypAutomobilu.NAKLADNI,
                Integer.parseInt(tfPocetKol.getText()),
                cbBarvaAuta.getSelectionModel().getSelectedItem(),
                Integer.parseInt(tfCtvrtyParametr.getText()),
                cbPatyParametr.isSelected());
    }

    private void nastavTyp() {
        switch (automobily.getTyp()) {
            case DODAVKA:
                caseDodavka();
                break;
            case NAKLADNI:
                caseNakladni();
                break;
            case OSOBNI:
                caseOsobni();
                break;
            case TAHAC:
                caseTahac();
                break;
            case VALNIK:
                caseValnik();
                break;
        }
    }

    private void caseTahac() {
        automobily.setMaxPocetPolozek(Integer.parseInt(tfmaxPocetPolozek.getText()));
        automobily.setJede(cbJede.isSelected());
        automobily.setPocetKol(Integer.parseInt(tfPocetKol.getText()));
        ((Tahac) automobily).setIdentifikace(tfCtvrtyParametr.getText());
        automobily.setBarva(cbBarvaAuta.getSelectionModel().getSelectedItem());
        ((Tahac) automobily).setJeBenzin(cbPatyParametr
                .isSelected());
    }

    private void caseValnik() {
        automobily.setMaxPocetPolozek(Integer.parseInt(tfmaxPocetPolozek.getText()));
        automobily.setJede(cbJede.isSelected());
        automobily.setPocetKol(Integer.parseInt(tfPocetKol.getText()));
        ((Valnik) automobily).setStupenZniceni(Integer.parseInt(
                tfCtvrtyParametr.getText()));
        automobily.setBarva(cbBarvaAuta.getSelectionModel().getSelectedItem());
        ((Valnik) automobily).setDruhVyfuku(tfPatyParametr.getText());
    }

    private void caseNakladni() {
        automobily.setMaxPocetPolozek(Integer.parseInt(tfmaxPocetPolozek.getText()));
        automobily.setJede(cbJede.isSelected());
        automobily.setPocetKol(Integer.parseInt(tfPocetKol.getText()));
        ((NakladniAutomobil) automobily).setPocetKoni(
                Integer.parseInt(tfCtvrtyParametr.getText()));
        automobily.setBarva(cbBarvaAuta.getSelectionModel().getSelectedItem());
        ((NakladniAutomobil) automobily).setNahonNaVsechny(
                cbPatyParametr.isSelected());
    }

    private void caseOsobni() {
        automobily.setMaxPocetPolozek(Integer.parseInt(tfmaxPocetPolozek.getText()));
        automobily.setJede(cbJede.isSelected());
        automobily.setPocetKol(Integer.parseInt(tfPocetKol.getText()));
        ((OsobniAutomobil) automobily).setZnackaAuta(
                tfCtvrtyParametr.getText());
        automobily.setBarva(cbBarvaAuta.getSelectionModel().getSelectedItem());
        ((OsobniAutomobil) automobily).setFunkcni(cbPatyParametr
                .isSelected());
    }

    private void caseDodavka() {
        automobily.setMaxPocetPolozek(Integer.parseInt(tfmaxPocetPolozek.getText()));
        automobily.setJede(cbJede.isSelected());
        automobily.setPocetKol(Integer.parseInt(tfPocetKol.getText()));
        ((Dodavka) automobily).setHmotnost(Integer.parseInt(
                tfCtvrtyParametr.getText()));
        automobily.setBarva(cbBarvaAuta.getSelectionModel().getSelectedItem());
        ((Dodavka) automobily).setTypMotoru(tfPatyParametr.getText());
    }

    private void vytvor() {
        try {
            grid.getChildren().clear();

            TypAutomobilu typAutomobilu;
            if (automobily == null) {
                if (cbTypAutomobilu != null) {
                    typAutomobilu = cbTypAutomobilu.getSelectionModel()
                            .getSelectedItem();
                } else {
                    typAutomobilu = TypAutomobilu.DODAVKA;
                }
            } else {
                set();
                return;
            }

            cbTypAutomobilu = addRowComboBox(grid, "Typy automobilu: ", 0,
                    TypAutomobilu.values(), typAutomobilu, (event) -> vytvor());
            tfPocetKol = addRowTestField(grid, "Počet kol: ", 1);
            cbBarvaAuta = addRowComboBox(grid, "Barva: ", 2,
                    BarvaAuta.values());
            tfmaxPocetPolozek = addRowTestField(grid, "MaxPocet položek: ", 5);
            cbJede = addRowCheckBox(grid, "Pojízdný: ", 6, false);

            switch (typAutomobilu) {
                case OSOBNI:
                    tfCtvrtyParametr = addRowTestField(grid, "Značka: ", 3);
                    cbPatyParametr = addRowCheckBox(grid, "Funkční: ", 4, false);
                    break;
                case DODAVKA:
                    tfCtvrtyParametr = addRowTestField(grid, "Hmotnost: ", 3);
                    tfPatyParametr = addRowTestField(grid, "Typ motoru: ",
                            "turbo", 4);
                    break;
                case NAKLADNI:
                    tfCtvrtyParametr = addRowTestField(grid, "Počet koni: ", 3);
                    cbPatyParametr = addRowCheckBox(grid, "Nahon na všechny: ",
                            4, false);
                    break;
                case TAHAC:
                    tfCtvrtyParametr = addRowTestField(grid, "Identifikace: ", 3);
                    cbPatyParametr = addRowCheckBox(grid, "Benzin: ", 4, false);
                    break;
                case VALNIK:
                    tfCtvrtyParametr = addRowTestField(grid, "Stupeň zničení: ", 3);
                    tfPatyParametr = addRowTestField(grid, "Druh výfuku: ",
                            "katalyzovaný", 4);
                    break;
            }
        } catch (Exception e) {
            ChybovaHlaska.zobrazAlert("Nepodařilo se vytvořit dialog!");
        }
    }

    private void set() {

        grid.getChildren().clear();
        cbTypAutomobilu = addRowComboBox(grid, "Typy automobilu: ", 0,
                TypAutomobilu.values(), automobily.getTyp());
        tfPocetKol = addRowTestField(grid, "Počet kol: ",
                Integer.toString(automobily.getPocetKol()), 1);
        cbBarvaAuta = addRowComboBox(grid, "Barva: ", 2,
                BarvaAuta.values(), automobily.getBarva());
        tfmaxPocetPolozek = addRowTestField(grid, "MaxPocet položek", Integer.toString(automobily.getMaxPocetPolozek()), 5);
        cbJede = addRowCheckBox(grid, "Pojízdný: ", 6, automobily.isJede());

        switch (automobily.getTyp()) {
            case DODAVKA:
                tfCtvrtyParametr = addRowTestField(grid, "Hmotnost: ",
                        Integer.toString(((Dodavka) automobily).getHmotnost()),
                        3);
                tfPatyParametr = addRowTestField(grid, "Typ motoru: ",
                        ((Dodavka) automobily).getTypMotoru(), 4);
                break;
            case TAHAC:
                tfCtvrtyParametr = addRowTestField(grid, "Identifikace: ",
                        ((Tahac) automobily).getIdentifikace(), 3);
                cbPatyParametr = addRowCheckBox(grid, "Benzin: ", 4,
                        ((Tahac) automobily).isJeBenzin());
                break;
            case NAKLADNI:
                tfCtvrtyParametr = addRowTestField(grid, "Počet koni: ",
                        Integer.toString(((NakladniAutomobil) automobily).
                                getPocetKoni()), 3);
                cbPatyParametr = addRowCheckBox(grid, "Nahon na všechny: ", 4,
                        ((NakladniAutomobil) automobily).isNahonNaVsechny());
                break;
            case OSOBNI:
                tfCtvrtyParametr = addRowTestField(grid, "Značka: ",
                        ((OsobniAutomobil) automobily).getZnackaAuta(), 3);
                cbPatyParametr = addRowCheckBox(grid, "Funkční: ", 4,
                        ((OsobniAutomobil) automobily).isFunkcni());
                break;
            case VALNIK:
                tfCtvrtyParametr = addRowTestField(grid, "Stupeň zničení: ",
                        Integer.toString(((Valnik) automobily).getStupenZniceni()),
                        3);
                tfPatyParametr = addRowTestField(grid, "Druh výfuku: ",
                        ((Valnik) automobily).getDruhVyfuku(), 4);
                break;
        }
        cbTypAutomobilu.setDisable(true);
    }

}
