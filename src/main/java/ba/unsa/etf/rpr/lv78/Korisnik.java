package ba.unsa.etf.rpr.lv78;

import javafx.beans.property.SimpleStringProperty;

public class Korisnik {
    private SimpleStringProperty ime, prezime, mail, korIme, lozinka;

    public String getIme() {
        return ime.get();
    }

    public Korisnik(Korisnik noviKorisnik) {
        ime = new SimpleStringProperty("");
        prezime = new SimpleStringProperty("");
        mail = new SimpleStringProperty("");
        korIme = new SimpleStringProperty("");
        lozinka = new SimpleStringProperty("");
    }

    public Korisnik(String ime, String prezime, String mail, String korIme, String lozinka) {
        this.ime = new SimpleStringProperty(ime);
        this.prezime = new SimpleStringProperty(prezime);
        this.mail = new SimpleStringProperty(mail);
        this.korIme = new SimpleStringProperty(korIme);
        this.lozinka = new SimpleStringProperty(lozinka);
    }

    public SimpleStringProperty imeProperty() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime.set(ime);
    }

    public String getPrezime() {
        return prezime.get();
    }

    public SimpleStringProperty prezimeProperty() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime.set(prezime);
    }

    public String getMail() {
        return mail.get();
    }

    public SimpleStringProperty mailProperty() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail.set(mail);
    }

    public String getKorIme() {
        return korIme.get();
    }

    public SimpleStringProperty korImeProperty() {
        return korIme;
    }

    public void setKorIme(String korIme) {
        this.korIme.set(korIme);
    }

    public String getLozinka() {
        return lozinka.get();
    }

    public SimpleStringProperty lozinkaProperty() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka.set(lozinka);
    }

    @Override
    public String toString() {
        return ime.get() + " " + prezime.get();
    }
}