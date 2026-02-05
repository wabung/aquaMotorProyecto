package aquaMotor.aquaMotorProyecto;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // Cargar la vista principal del proyecto (bossHome.fxml)
        scene = new Scene(loadFXML("bossHome"), 986, 683);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    // Inicializa la referencia a la Scene principal para que otros controladores
    // puedan usar App.setRoot(...) incluso si la aplicación fue arrancada por
    // otra clase como MainApp.
    public static void initScene(Scene s) {
        scene = s;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        // Usar ruta absoluta en classpath: /aquaMotor/aquaMotorProyecto/<fxml>.fxml
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/aquaMotor/aquaMotorProyecto/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}