package aquaMotor.aquaMotorProyecto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import utils.UtilsLogin;

public class LoginController {

    @FXML
    private StackPane rootPane;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    @FXML
    public void initialize() {
        System.out.println("Login View Initialized");
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        // --- LLAMADA 1: Inicia validación en UtilsLogin ---
        UtilsLogin.login(usernameField.getText().trim(), passwordField.getText().trim(), event);
    }
}