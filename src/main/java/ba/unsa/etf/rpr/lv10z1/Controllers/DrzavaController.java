package ba.unsa.etf.rpr.lv10z1.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;


public class DrzavaController {
    public TextField fieldNaziv;
    public ChoiceBox choiceGrad;

    public void initialize() {
        fieldNaziv.getStyleClass().add("poljeNijeIspravno");

        fieldNaziv.textProperty().addListener((observableValue, s, t1) -> {
            if (fieldNaziv.getText().trim().isEmpty()) {
                fieldNaziv.getStyleClass().removeAll("poljeJeIspravno");
                fieldNaziv.getStyleClass().add("poljeNijeIspravno");
            } else {
                fieldNaziv.getStyleClass().removeAll("poljeNijeIspravno");
                fieldNaziv.getStyleClass().add("poljeJeIspravno");
            }
        });
    }

    public void cancelDrzava() {
        System.exit(0);
    }

    public void dodajDrzavu(ActionEvent actionEvent) {

    }
}
