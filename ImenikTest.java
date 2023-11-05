package ba.unsa.etf.rpr.t3.z2;

import ba.unsa.etf.rpr.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class ImenikTest {

    private static Imenik imenik = new Imenik();

    @BeforeAll
    public static void setup() throws Izuzetak {
        imenik.dodaj("Eldar", new FiksniBroj(Grad.SARAJEVO, "225-883"));
        imenik.dodaj("Dino", new FiksniBroj(Grad.ZENICA, "225-884"));
        imenik.dodaj("Amir", new MobilniBroj(61, "225-885"));
        imenik.dodaj("Chris", new MedunarodniBroj("+44", "7768878794"));
    }

    @Test
    public void dajBrojFound() {
        String broj = imenik.dajBroj("Eldar");
        assertEquals(broj, "033/225-883");
    }

    @Test
    public void dajBrojNotFound() {
        String broj = imenik.dajBroj("Elldaroniii");
        assertNull(broj);
    }

    @Test
    public void dodajTestPositive() {
        TelefonskiBroj broj = new MobilniBroj(61, "507-855");
        imenik.dodaj("Hamo", broj);
        String brojStr = imenik.dajBroj("Hamo");
        assertEquals(brojStr, "061/507-855");
    }

    @Test
    public void dodajFiksniException() {
        assertThrows(Izuzetak.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new FiksniBroj(null, "123-123");
            }
        });
        assertThrows(Izuzetak.class, () -> {new FiksniBroj(null, "123-123");});
    }
}