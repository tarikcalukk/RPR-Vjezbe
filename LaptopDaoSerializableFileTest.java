package ba.unsa.etf.rpr;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

class LaptopDaoSerializableFileTest {

    private LaptopDaoSerializableFile laptopDao;

    @BeforeEach
    void setUp() {
        File mockFile = mock(File.class);
        laptopDao = new LaptopDaoSerializableFile(mockFile);
    }

    @Test
    void dodajLaptopUListu() {
        Laptop laptop = new Laptop("Brend123", "Model123", 1000.0, 16, 512, 0, "Procesor123", "GPU123", 15.6);

        laptopDao.dodajLaptopUListu(laptop);

        assertEquals(1, laptopDao.getLaptopi().size());
    }

    @Test
    void dodajLaptopUFile() {
        Laptop laptop = new Laptop("Brend123", "Model123", 1000.0, 16, 512, 0, "Procesor123", "GPU123", 15.6);

        // Mockiranje metoda za sacuvajPodatkeUDatoteku
        LaptopDaoSerializableFile spyDao = spy(laptopDao);
        doNothing().when(spyDao).sacuvajPodatkeUDatoteku();

        spyDao.dodajLaptopUFile(laptop);

        assertEquals(1, spyDao.getLaptopi().size());

        // Verifikacija da je metoda sacuvajPodatkeUDatoteku pozvana
        verify(spyDao, times(1)).sacuvajPodatkeUDatoteku();
    }

    @Test
    void getLaptop() {
        Laptop laptop = new Laptop("Brend123", "Model123", 1000.0, 16, 512, 0, "Procesor123", "GPU123", 15.6);
        laptopDao.dodajLaptopUListu(laptop);

        Laptop result = laptopDao.getLaptop("Procesor123");

        assertNotNull(result);
        assertEquals(laptop, result);
    }

    @Test
    void napuniListu() {
        ArrayList<Laptop> lista = new ArrayList<Laptop>();
        lista.add(new Laptop("Brend456", "Model456", 1200.0, 8, 256, 512, "Procesor456", "GPU456", 14.0));

        laptopDao.napuniListu(lista);

        assertEquals(lista, laptopDao.getLaptopi());
    }
}
