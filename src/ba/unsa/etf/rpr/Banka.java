package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.List;

public class Banka {
    private Long brojRacuna;
    private List<Korisnik> korisnici;
    private List<Uposlenik> uposlenici;



    public List<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(List<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }


    public List<Uposlenik> getUposlenici() {
        return uposlenici;
    }

    public void setUposlenici(List<Uposlenik> uposlenici) {
        this.uposlenici = uposlenici;
    }


    public Banka() {
        korisnici = new ArrayList<>();
        uposlenici = new ArrayList<>();
    }

    public Korisnik kreirajNovogKorisnika (String korisnik1, String korisnik2) {
        Korisnik noviKorisnik = new Korisnik(korisnik1, korisnik2);
        korisnici.add(noviKorisnik);
        return noviKorisnik;
    }

    public Uposlenik kreirajNovogUposlenika (String uposlenik1, String uposlenik2) {
        Uposlenik noviUposlenik = new Uposlenik(uposlenik1, uposlenik2);
        uposlenici.add(noviUposlenik);
        return noviUposlenik;
    }

    public Racun kreirajRacunZaKorisnika (Korisnik korisnik) {
        long noviBrojRacuna = brojRacuna++;
        Racun noviRacun = new Racun(noviBrojRacuna, korisnik);
        return noviRacun;
    }
}