package ba.unsa.etf.rpr.lv78;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
    public TextField fldIme;
    public TextField fldPrezime;
    public TextField fldMail;
    public TextField fldKorIme;
    public PasswordField fldLozinka;
    public ListView<Korisnik> listKorisnici;

    private KorisniciModel model = new KorisniciModel();

    public Controller() {
        model.napuni();
    }


    @FXML
    public void initialize() {
        listKorisnici.setItems(model.getKorisnici());

        listKorisnici.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldKorisnik, newKorisnik) -> {
                    if (oldKorisnik != null)
                        unbindBidirectional();
                    if (newKorisnik == null) {
                        fldIme.setText("");
                        fldPrezime.setText("");
                        fldMail.setText("");
                        fldKorIme.setText("");
                        fldLozinka.setText("");
                    }
                    else {
                        model.setTrenutniKorisnik(newKorisnik);
                        listKorisnici.refresh();
                        bindBidirectional();
                    }
                }
        );
    }

    @FXML
    public void dodavanje(ActionEvent actionEvent) {
        unbindBidirectional();

        Korisnik noviKorisnik = new Korisnik("", "", "", "", "");
        model.getKorisnici().add(noviKorisnik);
        model.setTrenutniKorisnik(noviKorisnik);

        bindBidirectional();
    }

    @FXML
    public void zavrsi(ActionEvent actionEvent) {
        System.exit(0);
    }

    private void unbindBidirectional() {
        fldIme.textProperty().unbindBidirectional(model.getTrenutniKorisnik().imeProperty());
        fldPrezime.textProperty().unbindBidirectional(model.getTrenutniKorisnik().prezimeProperty());
        fldMail.textProperty().unbindBidirectional(model.getTrenutniKorisnik().mailProperty());
        fldKorIme.textProperty().unbindBidirectional(model.getTrenutniKorisnik().korImeProperty());
        fldLozinka.textProperty().unbindBidirectional(model.getTrenutniKorisnik().lozinkaProperty());
    }

    private void bindBidirectional() {
        fldIme.textProperty().bindBidirectional(model.getTrenutniKorisnik().imeProperty());
        fldPrezime.textProperty().bindBidirectional(model.getTrenutniKorisnik().prezimeProperty());
        fldMail.textProperty().bindBidirectional(model.getTrenutniKorisnik().mailProperty());
        fldKorIme.textProperty().bindBidirectional(model.getTrenutniKorisnik().korImeProperty());
        fldLozinka.textProperty().bindBidirectional(model.getTrenutniKorisnik().lozinkaProperty());
    }
}