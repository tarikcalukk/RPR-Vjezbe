package ba.unsa.etf.rpr;

import java.util.*;

public class Imenik {
    final private Map<String, TelefonskiBroj> brojevi;

    public Imenik() {
        this.brojevi = new HashMap<>();
    }


    public void dodaj(String ime, TelefonskiBroj broj) {
        this.brojevi.put(ime, broj);
    }

    public String dajBroj(String ime) {
        TelefonskiBroj broj = this.brojevi.get(ime);
        if (broj != null)
            return broj.ispisi();
        else return null;
    }

    public String dajIme(TelefonskiBroj broj) {
        for (Map.Entry<String, TelefonskiBroj> entry : this.brojevi.entrySet()) {
            if (entry.getValue().ispisi().equals(broj.ispisi()))
                return entry.getKey();
        }
        return null;
    }

    public String naSlovo(char s) {
        StringBuilder builder = new StringBuilder();

        int counter = 1;
        for (Map.Entry<String,TelefonskiBroj> entry : this.brojevi.entrySet()) {
            if (entry.getKey().startsWith(String.valueOf(s))) {
                builder.append(counter)
                        .append(". ")
                        .append(entry.getKey())
                        .append(" - ")
                        .append(entry.getValue().ispisi())
                        .append(System.lineSeparator());
            }
            counter++;
        }
        return builder.toString();
    }

    public Set<String> izGrada(Grad g) {
        Set<String> rezultat = new TreeSet<>();
        for (Map.Entry<String,TelefonskiBroj> entry : this.brojevi.entrySet()) {
            if (jelIzGrada(entry.getValue(), g))
                rezultat.add(entry.getKey());
        }
        return rezultat;
    }

    public Set<TelefonskiBroj> izGradaBrojevi(Grad g) {
        Set<TelefonskiBroj> rezultat = new TreeSet<>(new Comparator<>() {
           @Override
           public int compare (TelefonskiBroj o1, TelefonskiBroj o2) { return o1.ispisi().compareTo(o2.ispisi()); }
        });

        for (Map.Entry<String, TelefonskiBroj> entry : this.brojevi.entrySet()) {
            if (jelIzGrada(entry.getValue(), g))
                rezultat.add(entry.getValue());
        }
        return rezultat;
    }

    private boolean jelIzGrada(TelefonskiBroj broj, Grad grad) {
        if (broj instanceof FiksniBroj)
            return grad.equals(((FiksniBroj) broj).getGrad());
        else
            return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int counter = 1;
        for (Map.Entry<String, TelefonskiBroj> entry : this.brojevi.entrySet()) {
            builder.append(counter)
                    .append(". ")
                    .append(entry.getKey())
                    .append(" - ")
                    .append(entry.getValue().ispisi())
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }
}
