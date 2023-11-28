package ba.unsa.etf.rpr;

class Osoba {
    private String ime;
    private String prezime;

    public Osoba(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
}



class LicneInformacije extends Osoba {
    public LicneInformacije(String ime, String prezime) {
        super(ime, prezime);
    }
}

class InformacijeOStudentu extends Osoba {
    private String godinaStudija;
    private String brojIndexa;

    public InformacijeOStudentu(String ime, String prezime, String godinaStudija, String brojIndexa) {
        super(ime, prezime);
        this.godinaStudija = godinaStudija;
        this.brojIndexa = brojIndexa;
    }

    public String getGodinaStudija() {
        return godinaStudija;
    }

    public void setGodinaStudija(String godinaStudija) {
        this.godinaStudija = godinaStudija;
    }

    public String getBrojIndexa() {
        return brojIndexa;
    }

    public void setBrojIndexa(String brojIndexa) {
        this.brojIndexa = brojIndexa;
    }
}

class InformacijeONastavniku extends Osoba {
    private String titula;

    public InformacijeONastavniku(String ime, String prezime, String titula) {
        super(ime, prezime);
        this.titula = titula;
    }

    public String getTitula() {
        return titula;
    }

    public void setTitula(String titula) {
        this.titula = titula;
    }
}


class Predmet {
    private String naziv;
    private String opis;

    public Predmet(String naziv, String opis) {
        this.naziv = naziv;
        this.opis = opis;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}


class KolekcijaPoruka {
    private String[] poruke;

    public KolekcijaPoruka(Object... objekti) {
        this.poruke = new String[objekti.length];
        for (int i = 0; i < objekti.length; i++) {
            if (objekti[i] instanceof Osoba) {
                poruke[i] = ((Osoba) objekti[i]).getIme() + " " + ((Osoba) objekti[i]).getPrezime();
            } else if (objekti[i] instanceof Predmet) {
                poruke[i] = ((Predmet) objekti[i]).getNaziv() + ": " + ((Predmet) objekti[i]).getOpis();
            }
        }
    }

    public String[] getPoruke() {
        return poruke;
    }
}


public class Main {
    public static void main(String[] args) {
        LicneInformacije osoba = new LicneInformacije("Ifet", "Feraget");
        InformacijeOStudentu student = new InformacijeOStudentu("Ivica", "Kostelic", "2", "123456");
        InformacijeONastavniku nastavnik = new InformacijeONastavniku("Adis", "Nukic", "Doktor");
        Predmet predmet = new Predmet("Razvoj programskih rjesenja", "Java");

        KolekcijaPoruka kolekcija = new KolekcijaPoruka(osoba, student, nastavnik, predmet);

        for (String poruka : kolekcija.getPoruke()) {
            System.out.println(poruka);
        }
    }
}
