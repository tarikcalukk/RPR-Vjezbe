package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.ArrayList;

public class GeografijaDAO {
    private static GeografijaDAO instance;
    private PreparedStatement gradovi, dajDrzavuUpit;
    private Connection conn;

    public static GeografijaDAO getInstance() {
        if (instance == null)
            instance = new GeografijaDAO();
        return instance;
    }

    private GeografijaDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
            gradovi = conn.prepareStatement("SELECT * FROM grad ORDER BY broj_stanovnika DESC");
            dajDrzavuUpit = conn.prepareStatement("SELECT * FROM drzava WHERE id=?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Grad dajGradizResultSeta(ResultSet rs) throws SQLException {
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
        catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Drzava dajDrzavuIzResultSeta(ResultSet rs, Grad grad) throws SQLException {
        return new Drzava(rs.getInt(1), rs.getString(2), grad);
    }

    public ArrayList<Grad> gradovi() {
        ArrayList<Grad> rezultat = new ArrayList<Grad>();
        try {
            ResultSet rs = gradovi.executeQuery();
            while(rs.next()) {
                Grad grad = dajGradizResultSeta(rs);
                rezultat.add(grad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rezultat;
    }
}
