package aquaMotor.aquaMotorProyecto.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UserSignupController {

    // Campos del formulario
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private ComboBox<String> roleCombo;

    @FXML
    public void initialize() {
        // Llenamos el combo con los roles disponibles
        roleCombo.getItems().addAll("BOSS", "MECHANIC", "SALES", "ADMIN");

        System.out.println("User Signup Controller cargado correctamente.");
    }

    @FXML
    private void handleBack() {
        try {
            aquaMotor.aquaMotorProyecto.App.setRoot("bossHome");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para el botón SAVE (vincular en el componente button.fxml si es necesario
     * o mediante código si el botón está dentro del controlador).
     */
    @FXML
    private void handleSaveUser() {
        String name = nameField.getText();
        String email = emailField.getText();
        String role = roleCombo.getValue();

        if (name.isEmpty() || email.isEmpty() || role == null) {
            System.out.println("Error: Por favor rellena todos los campos.");
        } else {
            System.out.println("Guardando usuario: " + name + " [" + role + "]");
            // Aquí iría la llamada a tu base de datos o servicio
        }
    }

    /**
     * Método para el botón CLEAR (limpiar formulario).
     */
    @FXML
    private void handleClearFields() {
        nameField.clear();
        emailField.clear();
        passwordField.clear();
        roleCombo.getSelectionModel().clearSelection();
    }
}
