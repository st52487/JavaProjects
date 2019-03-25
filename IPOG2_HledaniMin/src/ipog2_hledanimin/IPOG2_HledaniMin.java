package ipog2_hledanimin;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class IPOG2_HledaniMin extends Application {

    private final int pocetSloupcu = 15;
    private final int pocetRadku = 20;
    private final ImageView[][] visualniMatice = new ImageView[pocetRadku][pocetSloupcu];
    private final Image pozadi = new Image(getClass().getResourceAsStream("upraveny_pozadi.png"));
    private final Image pozadi_mina = new Image(getClass().getResourceAsStream("pozadi_mina.png"));
    private final Image vlajka = new Image(getClass().getResourceAsStream("upraveny_vlajka.png"));
    private final Image kliknuta_mina = new Image(getClass().getResourceAsStream("kliknuta_mina.png"));
    private final Image dveMiny = new Image(getClass().getResourceAsStream("upraveny_dveminy.png"));
    private final Image triMiny = new Image(getClass().getResourceAsStream("upraveny_triminy.png"));
    private final Image ctyriMiny = new Image(getClass().getResourceAsStream("upraveny_ctyriminy.png"));
    private final Image kliknuto0min = new Image(getClass().getResourceAsStream("upraveny_kliknuto0min.png"));
    private final Image jednaMina = new Image(getClass().getResourceAsStream("upraveny_jednaMina.png"));
    private final Image nekliknutaMina = new Image(getClass().getResourceAsStream("upraveny_nekliknutaMina.png"));
    private final int pocetMin = 37;
    private int x;
    private int y;
    private int puvodniX;
    private int puvodniY;

    @Override
    public void start(Stage primaryStage) {

        GridPane grid = new GridPane();

        vyplnHraciPole(grid);
        Scene scene = new Scene(grid, 488, 364);
        primaryStage.setResizable(false);
        primaryStage.setTitle("_Hledání min_Baláček");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void vyplnHraciPole(GridPane grid) {
        for (int i = 0; i < pocetRadku; i++) {
            for (int j = 0; j < pocetSloupcu; j++) {
                ImageView imageView = new ImageView();
                imageView.setImage(pozadi);
                imageView.setOnMousePressed((event) -> {
                    klikSecButtonDown(event, imageView);
                    if (event.isPrimaryButtonDown()) {
                        if (imageView.getImage().equals(pozadi_mina)) {
                            imageView.setImage(kliknuta_mina);
                            zjistiSouradnice(imageView);
                            try {
                                prohral(grid);
                            } catch (Exception ex) {
                                Logger.getLogger(IPOG2_HledaniMin.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            try {
                                zjistiSouradnice(imageView);
                                imageView.setImage(vratImage(checkMinesAround(x, y)));
                                if (checkMinesAround(x, y) == 0) {
                                    ukazPole(x, y);
                                }
                                if (zkontrolujVyhru()) {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("VYHRAL JSI!");
                                    alert.setHeaderText("Gratuluji k výhře!");
                                    alert.showAndWait();
                                    vyplnHraciPole(grid);
                                }
                            } catch (Exception ex) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("CHYBA");
                                alert.setHeaderText("Něco se nepovedlo");
                            }
                        }
                    }
                });
                visualniMatice[i][j] = imageView;
                grid.add(imageView, i, j);
            }
        }
        for (int i = 0; i < pocetMin; i++) {
            int x = generujNahodneCislo(0, pocetRadku);
            int y = generujNahodneCislo(0, pocetSloupcu);
            visualniMatice[x][y].setImage(pozadi_mina);
        }
    }

    public void ukazPole(int x, int y) throws Exception {
        for (int j = -1; j <= 1; j++) {
            for (int k = -1; k <= 1; k++) {
                if (((x + j < 0) || (x + j >= pocetRadku)) || ((y + k < 0 || y + k >= pocetSloupcu)) || ((x + j) == x && (y + k == y))) {
                    continue;
                }
                if ((this.checkMinesAround(x + j, y + k) == 0 && visualniMatice[x + j][y + k].getImage().equals(pozadi))
                        && !((visualniMatice[x + j][y + k].getImage().equals(vlajka)))) {
                    visualniMatice[x + j][y + k].setImage(vratImage(checkMinesAround(x + j, y + k)));
                    this.ukazPole(x + j, y + k);
                } else {
                    if (!visualniMatice[x + j][y + k].getImage().equals(vlajka)) {
                       visualniMatice[x + j][y + k].setImage(vratImage(checkMinesAround(x + j, y + k)));
                    }
                }
            }
        }
    }

    private void zjistiSouradnice(ImageView imageView) {
        boolean konecCyklu = false;
        for (int k = 0; k < pocetRadku; k++) {
            if (konecCyklu == true) {
                break;
            }
            for (int l = 0; l < pocetSloupcu; l++) {
                if (visualniMatice[k][l].equals(imageView)) {
                    this.x = k;
                    this.y = l;
                    konecCyklu = true;
                    break;
                }
            }
        }
        puvodniX = x;
        puvodniY = y;
    }

    private void prohral(GridPane grid) throws Exception {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Prohrál jsi!");
        alert.setHeaderText("Prohrál jsi!!");
        zobrazPole();
        alert.showAndWait();
        vyplnHraciPole(grid);
    }

    private void klikSecButtonDown(MouseEvent event, ImageView imageView) {
        if (event.isSecondaryButtonDown()) {
            if (imageView.getImage().equals(pozadi) ||imageView.getImage().equals(pozadi_mina) || imageView.getImage().equals(vlajka)) {
                if (imageView.getImage().equals(vlajka)) {
                    imageView.setImage(pozadi);
                } else {
                    imageView.setImage(vlajka);
                }
            }
        }
    }

    private int generujNahodneCislo(int min, int max) {
        return (int) (min + (Math.random()) * (max - min));
    }

    private Image vratImage(int pocet_min) throws Exception {
        switch (pocet_min) {
            case 0:
                return kliknuto0min;
            case 1:
                return jednaMina;
            case 2:
                return dveMiny;
            case 3:
                return triMiny;
            case 4:
                return ctyriMiny;
        }
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private int checkMinesAround(int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((((x + i) < 0) || (x + i) >= pocetRadku) || ((y + j < 0) || (y + j >= pocetSloupcu)) || ((x + i == x) && (y + j == y))) {
                    continue;
                }

                if (visualniMatice[x + i][y + j].getImage().equals(pozadi_mina)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean zkontrolujVyhru() {
        for (int j = 0; j < pocetRadku; j++) {
            for (int k = 0; k < pocetSloupcu; k++) {
                if (visualniMatice[j][k].getImage().equals(pozadi)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void zobrazPole() throws Exception {
        for (int i = 0; i < pocetRadku; i++) {
            for (int j = 0; j < pocetSloupcu; j++) {
                if (visualniMatice[i][j].getImage().equals(pozadi_mina)) {
                    visualniMatice[i][j].setImage(nekliknutaMina);
                } else {
                    visualniMatice[i][j].setImage(vratImage(checkMinesAround(i, j)));
                }
            }
        }
        visualniMatice[puvodniX][puvodniY].setImage(kliknuta_mina);
    }
}
