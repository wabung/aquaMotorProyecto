package utils;

import aquaMotor.aquaMotorProyecto.HomeSalesController;
import database.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import utils.UtilsLogin;


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
            FXMLLoader loader = new FXMLLoader(UtilsLogin.class.getResource(fxmlPath));
            Parent root = loader.load();

            // --- LLAMADA 2: Inyección de datos al controlador de destino ---
            Object controller = loader.getController();
            if (controller instanceof HomeSalesController) {
                ((HomeSalesController) controller).initData(user);
                // Guardar el usuario en App para mantener la sesión
                aquaMotor.aquaMotorProyecto.App.setCurrentUser(user);
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            mostrarError("Error de Carga", "No se pudo abrir la ventana: " + e.getMessage());
            e.printStackTrace();
        }
    }
}