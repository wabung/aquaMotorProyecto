package aquaMotor.aquaMotorProyecto;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Buscar bossHome.fxml en el classpath en: /aquaMotor/aquaMotorProyecto/bossHome.fxml
        URL fxmlLocation = getClass().getResource("/aquaMotor/aquaMotorProyecto/bossHome.fxml");

        if (fxmlLocation == null) {
            System.err.println("ERROR: No se encontró bossHome.fxml.");
            System.err.println("Ruta intentada: /aquaMotor/aquaMotorProyecto/bossHome.fxml");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/aquaMotor/aquaMotorProyecto/loginScreen.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("AquaMotor - Management System");
        Scene scene = new Scene(root, 986, 683);
        primaryStage.setScene(scene);

        // Registrar el stage en App para que App.setRoot(...) cree una Scene
        // completa y la aplique al Stage.
        aquaMotor.aquaMotorProyecto.App.initStage(primaryStage);

        // TEST: abrir la pantalla de showMechanics para verificar que la
        // navegación mediante App.setRoot("showMechanics") funciona.
        try {
            aquaMotor.aquaMotorProyecto.App.setRoot("loginScreen");
        } catch (Exception e) {
            e.printStackTrace();
        }

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}