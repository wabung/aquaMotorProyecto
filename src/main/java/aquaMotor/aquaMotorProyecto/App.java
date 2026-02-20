package aquaMotor.aquaMotorProyecto;

import database.model.User;
import javafx.application.Application;
import utils.InitializableWithUser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage primaryStage;
    private static User currentUser; // Usuario actual en sesión

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        scene = new Scene(loadFXML("sales/views/loginScreen"), 980, 683);
        
        // Configurar ventana redimensionable
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setResizable(true);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        System.out.println("setRoot llamado con: " + fxml);
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/aquaMotor/sales/" + fxml + ".fxml"));
        Parent newRoot = loader.load();
        
        // Si el controlador implementa InitializableWithUser, pasarle el usuario en sesión
        Object controller = loader.getController();
        if (controller instanceof InitializableWithUser && currentUser != null) {
            ((InitializableWithUser) controller).initData(currentUser);
        }
        
        // Crear una nueva Scene y establecerla en el Stage
        Scene newScene = new Scene(newRoot, 980, 683);
        primaryStage.setScene(newScene);
        scene = newScene; // Actualizar la referencia estática
        
        System.out.println("Scene completamente actualizada");
    }
    
    // Método para establecer el usuario actual
    public static void setCurrentUser(User user) {
        currentUser = user;
        System.out.println("Usuario en sesión: " + (user != null ? user.getName() : "null"));
    }
    
    // Método para obtener el usuario actual
    public static User getCurrentUser() {
        return currentUser;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/aquaMotor/sales/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static void main(String[] args) {
        launch();
    }

}