package ba.unsa.etf.rpr;

import java.util.List;

class KolekcijaImena {
    private List<String> imena;

    public KolekcijaImena(List<String> imena) {
        this.imena = imena;
    }

    public String getNajduzeIme() {
        if (imena.isEmpty()) {
            return null; // ili neki drugi odgovarajući signal za praznu listu
        }

        String najduzeIme = imena.get(0);

        for (String ime : imena) {
            if (ime.length() > najduzeIme.length()) {
                najduzeIme = ime;
            }
        }

        return najduzeIme;
    }
}

class Pobjednik {
    private String ime;
    private String prezime;
    private int brojZnakova;

    public Pobjednik(KolekcijaImena kolekcijaImena) {
        String najduzeIme = kolekcijaImena.getNajduzeIme();

        if (najduzeIme != null) {
            String[] dijelovi = najduzeIme.split(" ");
            if (dijelovi.length == 2) {
                ime = dijelovi[0];
                prezime = dijelovi[1];
                brojZnakova = najduzeIme.length();
            }
        }
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public int getBrojZnakova() {
        return brojZnakova;
    }
}

class KolekcijaImenaIPrezimena {
    private List<String> imena;
    private List<String> prezimena;

    public KolekcijaImenaIPrezimena(List<String> imena, List<String> prezimena) {
        this.imena = imena;
        this.prezimena = prezimena;
    }

    public int getIndexNajduzegPara() {
        int najduziIndex = -1;
        int najduzaDuzina = 0;

        for (int i = 0; i < imena.size() && i < prezimena.size(); i++) {
            String imePrezime = imena.get(i) + prezimena.get(i);
            int duzina = imePrezime.length();

            if (duzina > najduzaDuzina) {
                najduzaDuzina = duzina;
                najduziIndex = i;
            }
        }

        return najduziIndex;
    }

    public String getImeiPrezime(int i) {
        if (i >= 0 && i < imena.size() && i < prezimena.size()) {
            return imena.get(i) + " " + prezimena.get(i);
        } else {
            return null;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Primjer korištenja sa novom kolekcijom
        List<String> imenaIPrezimena = List.of("Ana", "Marko", "Ivan");
        List<String> prezimena = List.of("Anić", "Markić", "Ivanić");
        KolekcijaImenaIPrezimena kolekcijaImenaIPrezimena = new KolekcijaImenaIPrezimena(imenaIPrezimena, prezimena);

        // Korištenje novih metoda
        int indexNajduzegPara = kolekcijaImenaIPrezimena.getIndexNajduzegPara();
        String imePrezime = kolekcijaImenaIPrezimena.getImeiPrezime(indexNajduzegPara);

        // Ispis rezultata
        System.out.println("Index najdužeg para: " + indexNajduzegPara);
        System.out.println("Najduže ime i prezime: " + imePrezime);
    }
}