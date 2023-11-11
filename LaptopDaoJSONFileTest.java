package ba.unsa.etf.rpr;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

class LaptopDaoJSONFileTest {

    private LaptopDaoJSONFile laptopDao;

    @BeforeEach
    void setUp() {
        File mockFile = mock(File.class);
        laptopDao = new LaptopDaoJSONFile(mockFile);
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

        LaptopDaoJSONFile spyDao = spy(laptopDao);
        doNothing().when(spyDao).sacuvajPodatkeUDatoteku();

        spyDao.dodajLaptopUFile(laptop);

        assertEquals(1, spyDao.getLaptopi().size());

        verify(spyDao, times(1)).sacuvajPodatkeUDatoteku();
    }

}
