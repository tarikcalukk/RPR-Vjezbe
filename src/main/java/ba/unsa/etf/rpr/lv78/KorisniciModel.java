package ba.unsa.etf.rpr.lv78;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KorisniciModel {
    private ObservableList<Korisnik> korisnik;
    private SimpleObjectProperty<Korisnik> trenutniKorisnik;

    public KorisniciModel() {
        this.korisnik = FXCollections.observableArrayList();
        this.trenutniKorisnik = new SimpleObjectProperty<>();
    }

    public KorisniciModel(KorisniciModel model) {
        this.korisnik = FXCollections.observableArrayList(model.getKorisnici());
        this.trenutniKorisnik = model.trenutniKorisnikProperty();
    }

    public ObservableList<Korisnik> getKorisnici() {
        return korisnik;
    }

    public void setKorisnici(ObservableList<Korisnik> korisnik) {
        this.korisnik = korisnik;
    }

    public Korisnik getTrenutniKorisnik() {
        return trenutniKorisnik.get();
    }

    public SimpleObjectProperty<Korisnik> trenutniKorisnikProperty() {
        return trenutniKorisnik;
    }

    public void setTrenutniKorisnik(Korisnik trenutniKorisnik) {
        this.trenutniKorisnik.set(trenutniKorisnik);
    }

    public void napuni() {
        korisnik.add(new Korisnik("Tarik", "Konjevic", "tare@gmail.com", "Tare", "123"));
        korisnik.add(new Korisnik("Fare", "Saulic", "fare@gmail.com", "Fare", "123456"));
        korisnik.add(new Korisnik("Muhamed", "Savic", "muha@gmail.com", "Muha", "213"));
        trenutniKorisnik.set(null);
    }
}