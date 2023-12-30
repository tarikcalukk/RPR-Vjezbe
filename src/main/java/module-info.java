module ba.unsa.etf.rpr.lv10z1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ba.unsa.etf.rpr.lv10z1 to javafx.fxml;
    exports ba.unsa.etf.rpr.lv10z1;
    exports ba.unsa.etf.rpr.lv10z1.Controllers;
    opens ba.unsa.etf.rpr.lv10z1.Controllers to javafx.fxml;
}