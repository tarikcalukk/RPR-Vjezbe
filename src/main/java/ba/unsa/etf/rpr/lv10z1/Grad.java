package ba.unsa.etf.rpr.lv10z1;
import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

public class Grad {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty naziv = new SimpleStringProperty();
    private final IntegerProperty brojStanovnika = new SimpleIntegerProperty();
    private final ObjectProperty<Drzava> drzava = new SimpleObjectProperty<>();
    private static List<Grad> gradovi = new ArrayList<>();


    public static void setGradovi(List<Grad> gradovi) {
        Grad.gradovi = gradovi;
    }

    public static List<Grad> getGradovi() {
        return gradovi;
    }

    public Grad(int id, String naziv, int brojStanovnika, Drzava drzava) {
        setId(id);
        setNaziv(naziv);
        setBrojStanovnika(brojStanovnika);
        setDrzava(drzava);
    }

    public Grad() {
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNaziv() {
        return naziv.get();
    }

    public StringProperty nazivProperty() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv.set(naziv);
    }

    public int getBrojStanovnika() {
        return brojStanovnika.get();
    }

    public IntegerProperty brojStanovnikaProperty() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika.set(brojStanovnika);
    }

    public Drzava getDrzava() {
        return drzava.get();
    }

    public ObjectProperty<Drzava> drzavaProperty() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava.set(drzava);
    }

    public static void dodajGrad(Grad noviGrad) {
        gradovi.add(noviGrad);
    }
}

