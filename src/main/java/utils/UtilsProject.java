package utils;

import database.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class UtilsProject {

    public static void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void openScreen(ActionEvent event, String fxmlPath, String titulo, User user) {
        try {
            System.out.println("Intentando abrir pantalla: " + fxmlPath);
            FXMLLoader loader = new FXMLLoader(UtilsProject.class.getResource(fxmlPath));
            Parent root = loader.load();
            System.out.println("FXML cargado exitosamente");

            Object controller = loader.getController();
            System.out.println("Controlador: " + (controller != null ? controller.getClass().getName() : "null"));

            if (controller instanceof InitializableWithUser) {
                ((InitializableWithUser) controller).initData(user);
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.centerOnScreen();
            stage.show();
            System.out.println("Pantalla mostrada exitosamente");

        } catch (IOException e) {
            mostrarError("Error de Carga", "No se pudo abrir la ventana: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
