package ba.unsa.etf.rpr;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;

public class LaptopDaoXMLFile implements LaptopDao {
    final private File file;
    private ArrayList<Laptop> laptopi;

    public LaptopDaoXMLFile(File file) {
        this.file = file;
        this.laptopi = new ArrayList<Laptop>();
    }

    @Override
    public void dodajLaptopUListu(Laptop laptop) {
        laptopi.add(laptop);
    }

    @Override
    public void dodajLaptopUFile(Laptop laptop) {
        laptopi.add(laptop);
        sacuvajPodatkeUDatoteku();
    }

    @Override
    public Laptop getLaptop(String procesor) {
        for (Laptop laptop : laptopi) {
            if (laptop.getProcesor().equals(procesor)) {
                return laptop;
            }
        }
        return null;
    }

    @Override
    public void napuniListu(ArrayList<Laptop> laptopi) {
        this.laptopi = laptopi;
    }

    @Override
    public void vratiPodatkeIzDatoteke() {
        try {
            JAXBContext context = JAXBContext.newInstance(ArrayList.class); // Ovde treba biti ArrayList, ne LaptopDao
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ArrayList<Laptop> listaLaptopa = (ArrayList<Laptop>) unmarshaller.unmarshal(file);
            laptopi = listaLaptopa;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


    private void sacuvajPodatkeUDatoteku() {
        try {
            JAXBContext context = JAXBContext.newInstance(ArrayList.class); // Ovde treba biti ArrayList, ne LaptopDao
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(laptopi, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
