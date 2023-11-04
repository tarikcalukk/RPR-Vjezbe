package ba.unsa.etf.rpr;

import java.util.Objects;

public class MobilniBroj extends TelefonskiBroj {
    final private int mobilnaMreza;
    final private String broj;

    MobilniBroj(int mobilnaMreza, String broj) {
        this.mobilnaMreza = mobilnaMreza;
        this.broj = broj;
    }

    @Override
    public String ispisi() {
        return "0" + mobilnaMreza + broj;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mobilnaMreza, broj);
    }
}
