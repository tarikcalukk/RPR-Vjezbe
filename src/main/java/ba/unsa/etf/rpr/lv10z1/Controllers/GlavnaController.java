package ba.unsa.etf.rpr.lv10z1.Controllers;

import ba.unsa.etf.rpr.lv10z1.GlavnaMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.io.IOException;


public class GlavnaController {
    public TableView tableViewGradovi;
    public Button btnDodajGrad;
    public Button btnDodajDrzavu;
    public Button btnIzmijeniGrad;
    public Button btnObrisiGrad;

    @FXML
    private void dodajGrad(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GlavnaMain.class.getResource("fxml/grad.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = new Stage();
        stage.setTitle("Grad");
        stage.setScene(scene);
        stage.show();
    }

    public void dodajDrzavu(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GlavnaMain.class.getResource("fxml/drzava.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = new Stage();
        stage.setTitle("Država");
        stage.setScene(scene);
        stage.show();
    }

    public void izmijeniGrad(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GlavnaMain.class.getResource("fxml/grad.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = new Stage();
        stage.setTitle("Grad");
        stage.setScene(scene);
        stage.show();
    }

    public void obrisiGrad(ActionEvent actionEvent) {
    }
}