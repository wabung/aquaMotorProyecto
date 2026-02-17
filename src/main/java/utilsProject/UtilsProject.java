package utilsProject;

import database.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import login.utils.UtilsLogin;
import mechanic.controller.MechanicDashboardController;
import mechanic.controller.WorkInProgressController;
import mechanic.controller.WorkdayController;

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

            Object controller = loader.getController();

            if (controller instanceof MechanicDashboardController) {
                ((MechanicDashboardController) controller).initData(user);
            } else if (controller instanceof WorkdayController) {
                ((WorkdayController) controller).initData(user);
            } else if (controller instanceof WorkInProgressController) {
                ((WorkInProgressController) controller).initData(user, null);
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