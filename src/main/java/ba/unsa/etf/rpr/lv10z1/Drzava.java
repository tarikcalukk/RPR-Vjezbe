package ba.unsa.etf.rpr.lv10z1;
import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

public class Drzava {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty naziv = new SimpleStringProperty();
    private final ObjectProperty<Grad> glavniGrad = new SimpleObjectProperty<>();
    public static ArrayList<Drzava> drzave = new ArrayList<>();

    public static void setDrzave(ArrayList<Drzava> drzave) {
        Drzava.drzave = drzave;
    }

    public static ArrayList<Drzava> getDrzave() {
        return drzave;
    }

    public Drzava(int id, String naziv, Grad glavniGrad) {
        setId(id);
        setNaziv(naziv);
        setGlavniGrad(glavniGrad);
    }

    public Drzava() {
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

    public Grad getGlavniGrad() {
        return glavniGrad.get();
    }

    public ObjectProperty<Grad> glavniGradProperty() {
        return glavniGrad;
    }

    public void setGlavniGrad(Grad glavniGrad) {
        this.glavniGrad.set(glavniGrad);
    }
}
