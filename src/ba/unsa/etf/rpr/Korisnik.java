package ba.unsa.etf.rpr;

public class Korisnik extends Osoba {
    private Racun racun;


    public Racun getRacun() {
        return this.racun;
    }


    public void setRacun(Racun noviRacun) {
        this.racun = noviRacun;
    }


    public Korisnik(String i, String p) {
        super(i, p);
    }


    public void dodajRacun(Racun racun) {
        this.racun = racun;
    }
}
