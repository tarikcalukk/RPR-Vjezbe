package ba.unsa.etf.rpr.lv10z1.Controllers;

import ba.unsa.etf.rpr.lv10z1.Drzava;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;


public class GradController {
    public TextField fieldNaziv;
    public TextField fieldBrojStanovnika;
    public ChoiceBox<Drzava> choiceDrzava;


    public void initialize() {
        fieldNaziv.getStyleClass().add("poljeNijeIspravno");
        fieldBrojStanovnika.getStyleClass().add("poljeNijeIspravno");

        fieldNaziv.textProperty().addListener((observableValue, s, t1) -> {
            if (fieldNaziv.getText().trim().isEmpty() ) {
                fieldNaziv.getStyleClass().removeAll("poljeJeIspravno");
                fieldNaziv.getStyleClass().add("poljeNijeIspravno");
            }
            else {
                fieldNaziv.getStyleClass().removeAll("poljeNijeIspravno");
                fieldNaziv.getStyleClass().add("poljeJeIspravno");
            }
        });

        fieldBrojStanovnika.textProperty().addListener((observableValue, s, t1) -> {
            if (BrStanovnika(t1)) {
                fieldBrojStanovnika.getStyleClass().removeAll("poljeNijeIspravno");
                fieldBrojStanovnika.getStyleClass().add("poljeJeIspravno");
            } else {
                fieldBrojStanovnika.getStyleClass().removeAll("poljeJeIspravno");
                fieldBrojStanovnika.getStyleClass().add("poljeNijeIspravno");
            }
        });
    }


    private boolean BrStanovnika(String text) {
        try {
            int broj = Integer.parseInt(text);
            return broj > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void cancelGrad() {
        System.exit(0);
    }

    public void Ok(ActionEvent actionEvent) {
    }
}