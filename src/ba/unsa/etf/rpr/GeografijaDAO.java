package ba.unsa.etf.rpr;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            gradovi = conn.prepareStatement("SELECT * FROM grad ORDER BY broj_stanovnika DESC");
            dajDrzavuUpit = conn.prepareStatement("SELECT * FROM drzava WHERE id=?");
        } catch (SQLException e) {
            regenerisiBazu();
            try {
                gradovi = conn.prepareStatement("SELECT * FROM grad ORDER BY broj_stanovnika DESC");
                dajDrzavuUpit = conn.prepareStatement("SELECT * FROM drzava WHERE id=?");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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
