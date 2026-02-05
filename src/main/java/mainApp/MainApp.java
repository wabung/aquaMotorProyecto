package mainApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/aquaMotor/login/views/loginScreen.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("AquaMotor - Login");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println("❌ FATAL ERROR: Could not start application.");
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}