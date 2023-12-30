package ba.unsa.etf.rpr.lv10z1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GeografijaDAO {
    private static GeografijaDAO instance;
    private Connection conn;
    private PreparedStatement glavniGradUpit, dajDrzavuUpit, dajGradUpit, obrisiDrzavuUpit, obrisiGradoveZaDrzavuUpit, nadjiDrzavuUpit,
            dajGradoveUpit, dodajGradUpit, odrediIdGradaUpit, dodajDrzavuUpit, odrediIdDrzaveUpit, promijeniGradUpit;
    public static GeografijaDAO getInstance() {
        if (instance == null)
            instance = new GeografijaDAO();
        return instance;
    }

    public static void removeInstance() {
        if (instance == null) return;
        instance.close();
        instance = null;
    }

    public void close() {
        try {
            conn.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private GeografijaDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            glavniGradUpit = conn.prepareStatement("SELECT grad.id, grad.naziv, grad.broj_stanovnika, grad.drzava FROM grad, drzava WHERE grad.drzava=drzava.id AND drzava.naziv=?");
        }
        catch (SQLException e) {
            regenerisiBazu();
            try {
                glavniGradUpit = conn.prepareStatement("SELECT grad.id, grad.naziv, grad.broj_stanovnika, grad.drzava FROM grad, drzava WHERE grad.drzava=drzava.id AND drzava.naziv=?");
            }
            catch(SQLException el) {
                el.printStackTrace();
            }
        }

        try {
            dajDrzavuUpit = conn.prepareStatement("SELECT * FROM drzava WHERE id=?");
            dajGradUpit = conn.prepareStatement("SELECT * FROM grad WHERE id=?");
            obrisiGradoveZaDrzavuUpit = conn.prepareStatement("DELETE FROM grad WHERE drzava=?");
            obrisiDrzavuUpit = conn.prepareStatement("DELETE FROM drzava WHERE id=?");
            nadjiDrzavuUpit = conn.prepareStatement("SELECT * FROM drzava WHERE naziv=?");
            dajGradoveUpit = conn.prepareStatement("SELECT * FROM grad ORDER BY broj_stanovnika DESC");
            dodajGradUpit = conn.prepareStatement("INSERT INTO grad VALUES(?,?,?,?)");
            odrediIdGradaUpit = conn.prepareStatement("SELECT MAX(id)+1 FROM grad");
            dodajDrzavuUpit = conn.prepareStatement("INSERT INTO drzava VALUES(?,?,?)");
            odrediIdDrzaveUpit = conn.prepareStatement("SELECT MAX(id)+1 FROM drzava");
            promijeniGradUpit = conn.prepareStatement("UPDATE grad SET naziv=?, broj_stanovnika=?, drzava=? WHERE id=?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void regenerisiBazu() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("baza.db.sql"));
            String sqlUpit = "";
            while(ulaz.hasNext()) {
                sqlUpit += ulaz.nextLine();
                if(sqlUpit.charAt(sqlUpit.length() - 1) == ';') {
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public Grad glavniGrad(String drzava) {
        try {
            glavniGradUpit.setString(1, drzava);
            ResultSet rs = glavniGradUpit.executeQuery();
            if (!rs.next()) return null;
            return dajGradIzResultSeta(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Grad dajGradIzResultSeta(ResultSet rs) throws SQLException {
        Grad grad = new Grad(rs.getInt(1), rs.getString(2), rs.getInt(3), null);
        grad.setDrzava(dajDrzavu(rs.getInt(4), grad));
        return grad;
    }

    private Drzava dajDrzavu(int id, Grad grad) {
        try {
            dajDrzavuUpit.setInt(1, id);
            ResultSet rs = dajDrzavuUpit.executeQuery();
            if (!rs.next()) return null;
            return dajDrzavuIzResultSeta(rs, grad);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Grad dajGrad(int id) {
        try {
            dajGradUpit.setInt(1, id);
            ResultSet rs = dajGradUpit.executeQuery();
            if (!rs.next()) return null;
            return dajGradIzResultSeta(rs);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Drzava dajDrzavuIzResultSeta(ResultSet rs, Grad grad) throws SQLException {
        return new Drzava(rs.getInt(1), rs.getString(2), grad);
    }

    public void obrisiDrzavu(String drzava) {
        try {
            nadjiDrzavuUpit.setString(1, drzava);
            ResultSet rs = nadjiDrzavuUpit.executeQuery();
            if(!rs.next()) return;
            Drzava temp = dajDrzavuIzResultSeta(rs, null);

            obrisiGradoveZaDrzavuUpit.setInt(1, temp.getId());
            obrisiGradoveZaDrzavuUpit.executeUpdate();

            obrisiDrzavuUpit.setInt(1, temp.getId());
            obrisiDrzavuUpit.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Grad> gradovi() {
        ArrayList<Grad> rezultat = new ArrayList<Grad>();
        try {
            ResultSet rs = dajGradoveUpit.executeQuery();
            while(rs.next()) {
                Grad grad = dajGradIzResultSeta(rs);
                rezultat.add(grad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rezultat;
    }

    public void dodajGrad(Grad grad) {
        try {
            ResultSet rs = odrediIdGradaUpit.executeQuery();
            int id = 1;
            if(rs.next())
                id = rs.getInt(1);
            dodajGradUpit.setInt(1, id);
            dodajGradUpit.setString(2, grad.getNaziv());
            dodajGradUpit.setInt(3, grad.getBrojStanovnika());
            dodajGradUpit.setInt(4, grad.getDrzava().getId());
            dodajGradUpit.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajDrzavu(Drzava drzava) {
        try {
            ResultSet rs = odrediIdDrzaveUpit.executeQuery();
            int id = 1;
            if(rs.next())
                id = rs.getInt(1);

            dodajDrzavuUpit.setInt(1, id);
            dodajDrzavuUpit.setString(2, drzava.getNaziv());
            dodajDrzavuUpit.setInt(3, drzava.getGlavniGrad().getId());
            dodajDrzavuUpit.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void izmijeniGrad(Grad grad) {
        try {
            promijeniGradUpit.setString(1, grad.getNaziv());
            promijeniGradUpit.setInt(2, grad.getBrojStanovnika());
            promijeniGradUpit.setInt(3, grad.getDrzava().getId());
            promijeniGradUpit.setInt(4, grad.getId());
            promijeniGradUpit.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public Drzava nadjiDrzavu(String nazivDrzave) {
        try {
            nadjiDrzavuUpit.setString(1, nazivDrzave);
            ResultSet rs = nadjiDrzavuUpit.executeQuery();
            if(!rs.next()) return null;
            return dajDrzavuIzResultSeta(rs, dajGrad(rs.getInt(3)));
        }
        catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
