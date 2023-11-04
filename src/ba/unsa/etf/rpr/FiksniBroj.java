package ba.unsa.etf.rpr;

import java.util.Objects;

public class FiksniBroj extends TelefonskiBroj {
    final private Grad grad;
    final private String broj;

    public FiksniBroj(Grad grad, String broj) throws Izuzetak {
        if (grad == null || broj == null)
            throw new Izuzetak("Neispravni parametri!");
        this.grad = grad;
        this.broj = broj;
    }

    @Override
    public String ispisi() {
        return grad.getBroj() + "/" + broj;
    }

    @Override
    public int hashCode() {
        return Objects.hash(grad, broj);
    }

    public Grad getGrad() {
        return this.grad;
    }
}
