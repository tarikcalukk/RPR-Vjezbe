package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface MozeOcijeniti {
    Ocjena ocijeni(int x);
}

class Ocjena {
    private LicneInformacije osoba;
    private int ocjena;

    public Ocjena(LicneInformacije osoba, int ocjena) {
        this.osoba = osoba;
        setOcjena(ocjena);
    }

    public LicneInformacije getOsoba() {
        return osoba;
    }

    public int getOcjena() {
        return ocjena;
    }

    public void setOcjena(int ocjena) {
        if (ocjena > 0 && ocjena <= 10) {
            this.ocjena = ocjena;
        } else {
            System.out.println("Neispravna ocjena. Ocjena mora biti između 1 i 10.");
        }
    }
}

class Osoba {
    private String ime;
    private String prezime;
    private List<Ocjena> ocjene;

    public Osoba(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
        this.ocjene = new ArrayList<>();
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

    public List<Ocjena> getOcjene() {
        return ocjene;
    }

    public void dodajOcjenu(Ocjena ocjena) {
        ocjene.add(ocjena);
    }
}

class LicneInformacije extends Osoba implements MozeOcijeniti {
    public LicneInformacije(String ime, String prezime) {
        super(ime, prezime);
    }

    @Override
    public Ocjena ocijeni(int x) {
        Ocjena ocjena = new Ocjena(this, x);
        dodajOcjenu(ocjena);
        return ocjena;
    }
}

class InformacijeOStudentu extends Osoba implements MozeOcijeniti {
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

    public String getBrojIndexa() {
        return brojIndexa;
    }

    @Override
    public Ocjena ocijeni(int x) {
        Ocjena ocjena = new Ocjena(new LicneInformacije(getIme(), getPrezime()), x);
        dodajOcjenu(ocjena);
        return ocjena;
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
    public Ocjena ocijeni(int x) {
        Ocjena ocjena = new Ocjena(new LicneInformacije(getIme(), getPrezime()), x);
        return ocjena;
    }
}

class Predmet implements MozeOcijeniti {
    private String naziv;
    private String opis;
    private List<Ocjena> ocjene;

    public Predmet(String naziv, String opis) {
        this.naziv = naziv;
        this.opis = opis;
        this.ocjene = new ArrayList<>();
    }

    public String getNaziv() {
        return naziv;
    }

    public String getOpis() {
        return opis;
    }

    public List<Ocjena> getOcjene() {
        return ocjene;
    }

    @Override
    public Ocjena ocijeni(int x) {
        // Ocjenu može postaviti bilo ko
        Ocjena ocjena = new Ocjena(null, x);
        ocjene.add(ocjena);
        return ocjena;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LicneInformacije osoba = new LicneInformacije("Ifet", "Feraget");
        InformacijeOStudentu student = new InformacijeOStudentu("Ivica", "Kostelic", "2", "123456");
        InformacijeONastavniku nastavnik = new InformacijeONastavniku("Adis", "Nukic", "Doktor");
        Predmet predmet = new Predmet("Razvoj programskih rjesenja", "Java");

        Ocjena ocjena1 = student.ocijeni(8);
        Ocjena ocjena2 = nastavnik.ocijeni(9);
        Ocjena ocjena3 = predmet.ocijeni(7);

        System.out.println("Ocjene studenta " + student.getIme() + ":");
        for (Ocjena o : student.getOcjene()) {
            System.out.println("Predmet: " + o.getOsoba().getIme() + ", Ocjena: " + o.getOcjena());
        }

        System.out.println("\nOcjene nastavnika " + nastavnik.getIme() + ":");
        for (Ocjena o : nastavnik.getOcjene()) {
            System.out.println("Predmet: " + o.getOsoba().getIme() + ", Ocjena: " + o.getOcjena());
        }

        System.out.println("\nOcjene predmeta " + predmet.getNaziv() + ":");
        for (Ocjena o : predmet.getOcjene()) {
            System.out.println("Ocjena: " + o.getOcjena());
        }

        System.out.println("\nUnesite ocjenu za studenta " + student.getIme() + ":");
        int unesenaOcjena = scanner.nextInt();
        Ocjena novaOcjena = student.ocijeni(unesenaOcjena);

        System.out.println("\nNove ocjene studenta " + student.getIme() + ":");
        for (Ocjena o : student.getOcjene()) {
            System.out.println("Predmet: " + o.getOsoba().getIme() + ", Ocjena: " + o.getOcjena());
        }

        scanner.close();
    }
}
