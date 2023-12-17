package ba.unsa.etf.rpr.lv78;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KorisniciModelTest {

    @Test
    public void testDodavanjeKorisnika() {
        KorisniciModel model = new KorisniciModel();
        assertEquals(0, model.getKorisnici().size());

        model.napuni();
        assertEquals(3, model.getKorisnici().size());
    }

    @Test
    public void testPostavljanjeTrenutnogKorisnika() {
        KorisniciModel model = new KorisniciModel();
        model.napuni();

        assertNull(model.getTrenutniKorisnik());

        Korisnik korisnik = model.getKorisnici().get(0);
        model.setTrenutniKorisnik(korisnik);

        assertEquals(korisnik, model.getTrenutniKorisnik());
    }

    @Test
    public void testBrisanjeKorisnika() {
        KorisniciModel model = new KorisniciModel();
        model.napuni();
        int initialSize = model.getKorisnici().size();

        Korisnik korisnik = model.getKorisnici().get(0);
        model.getKorisnici().remove(korisnik);

        assertEquals(initialSize - 1, model.getKorisnici().size());
        assertFalse(model.getKorisnici().contains(korisnik));
    }
}
