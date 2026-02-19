package aquaMotor.aquaMotorProyecto.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AppBarController {

    @FXML
    private Button backButton;

    @FXML
    private Label titleNav;

    @FXML
    private void handleBack() {
        try {
            // Regresar a la pantalla principal
            aquaMotor.aquaMotorProyecto.App.setRoot("mechanicBoss");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Permite que otros controladores puedan actualizar el título si lo desean
    public void setTitle(String title) {
        if (titleNav != null) titleNav.setText(title);
    }
}
