package aquaMotor.aquaMotorProyecto;

import java.io.IOException;

import database.model.User;
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
    private static Stage primaryStage;
    private static User currentUser;

    @Override
    public void start(Stage stage) throws IOException {
        // Cargar la vista principal del proyecto (bossHome.fxml)
        primaryStage = stage;
        scene = new Scene(loadFXML("loginScreen"), 986, 683);

        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setResizable(true);

        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        if (primaryStage == null) {
            throw new IllegalStateException("Stage no inicializado. Llama a App.initStage(...) antes de App.setRoot(...).");
        }

        Parent newRoot = loadFXML(fxml);
        double width = scene != null ? scene.getWidth() : 986;
        double height = scene != null ? scene.getHeight() : 683;

        Scene newScene = new Scene(newRoot, width, height);
        primaryStage.setScene(newScene);
        scene = newScene;
    }

    // Inicializa la referencia a la Scene principal para que otros controladores
    // puedan usar App.setRoot(...) incluso si la aplicación fue arrancada por
    // otra clase como MainApp.
    public static void initScene(Scene s) {
        scene = s;
    }

    // Inicializa la referencia al Stage para que App.setRoot(...) pueda
    // crear y establecer una Scene completa.
    public static void initStage(Stage s) {
        primaryStage = s;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
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