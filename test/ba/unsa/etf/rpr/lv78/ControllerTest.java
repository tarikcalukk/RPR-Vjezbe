package ba.unsa.etf.rpr.lv78;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.api.FxRobot;

import java.util.Objects;
import java.util.concurrent.TimeoutException;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

@ExtendWith(ApplicationExtension.class)
public class ControllerTest {

    @Start
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testDodavanjeNovogKorisnika1(FxRobot robot) {
        robot.clickOn("#listKorisnici").clickOn("Tarik Konjevic");
        robot.clickOn("#Dodaj");
        robot.clickOn("#fldIme").write("Ahmed");
        robot.clickOn("#fldPrezime").write("Ahmic");
        robot.clickOn("#fldMail").write("ahmed.ahmic@gmail.com");
        robot.clickOn("#fldKorIme").write("ahmed_ahmic");
        robot.clickOn("#fldLozinka").write("12345");

        robot.clickOn("#listKorisnici").clickOn("Ahmed Ahmic");

        verifyThat("#fldIme", hasText("Ahmed"));
        verifyThat("#fldPrezime", hasText("Ahmic"));
        verifyThat("#fldMail", hasText("ahmed.ahmic@gmail.com"));
        verifyThat("#fldKorIme", hasText("ahmed_ahmic"));
        verifyThat("#fldLozinka", hasText("12345"));
    }

    @Test
    public void testDodavanjeNovogKorisnika2(FxRobot robot) {
        robot.clickOn("#listKorisnici").clickOn("Tarik Konjevic");
        robot.clickOn("#Dodaj");
        robot.clickOn("#fldIme").write("Ismar");
        robot.clickOn("#fldPrezime").write("Ismic");
        robot.clickOn("#fldMail").write("ismar.ismic@gmail.com");
        robot.clickOn("#fldKorIme").write("ismar_ismic");
        robot.clickOn("#fldLozinka").write("54321");

        robot.clickOn("#listKorisnici").clickOn("Ismar Ismic");

        verifyThat("#fldIme", hasText("Ismar"));
        verifyThat("#fldPrezime", hasText("Ismic"));
        verifyThat("#fldMail", hasText("ismar.ismic@gmail.com"));
        verifyThat("#fldKorIme", hasText("ismar_ismic"));
        verifyThat("#fldLozinka", hasText("54321"));
    }

    @Test
    public void testZavrsiMetodu() {
        Platform.runLater(() -> {
            Stage stage = new Stage();
            stage.show();
            stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, (Event event) -> {
            });
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        });

        try {
            FxToolkit.setupStage(Stage::show);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
