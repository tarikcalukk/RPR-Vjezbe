package ba.unsa.etf.rpr;

public class Racun {

    private Long brojRacuna;
    private Osoba korisnikRacuna;
    private boolean odobrenjePrekoracenja;
    private Double stanjeRacuna;


    public Long getBrojRacuna() {
        return brojRacuna;
    }

    public void setBrojRacuna(Long brojRacuna) {
        this.brojRacuna = brojRacuna;
    }


    public Osoba getKorisnikRacuna() {
        return korisnikRacuna;
    }

    public void setKorisnikRacuna(Osoba korisnikRacuna) {
        this.korisnikRacuna = korisnikRacuna;
    }


    public boolean isOdobrenjePrekoracenja() {
        return odobrenjePrekoracenja;
    }

    public void setOdobrenjePrekoracenja(boolean odobrenjePrekoracenja) {
        this.odobrenjePrekoracenja = odobrenjePrekoracenja;
    }


    public double getStanjeRacuna() {
        return stanjeRacuna;
    }

    public void setStanjeRacuna(Double stanjeRacuna) {
        this.stanjeRacuna = stanjeRacuna;
    }


    public Racun (Long num, Osoba osoba) {
        this.brojRacuna = num;
        this.korisnikRacuna = osoba;
        this.odobrenjePrekoracenja = false;
        this.stanjeRacuna = 0.0;
    }


    private boolean provjeriOdobrenjePrekoracenja(Double prekoracenje) {
        return odobrenjePrekoracenja && (stanjeRacuna - prekoracenje >= 0);
    }


    public boolean izvrsiUplatu (Double uplata) {
        if (uplata > 0) {
            stanjeRacuna += uplata;
            return true;
        }
        return false;
    }


    public boolean izvrsiIsplatu (Double isplata) {
        if (isplata > 0 && provjeriOdobrenjePrekoracenja(isplata)) {
            stanjeRacuna -= isplata;
            return true;
        }
        return false;
    }


    public void odobriPrekoracenje (Double prekoracenje) {
        if (prekoracenje > 0)
            odobrenjePrekoracenja = true;
    }
}
