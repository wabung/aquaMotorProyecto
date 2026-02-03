package login.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class LoginController {

    // These fields match the fx:id attributes we will add to the FXML
    @FXML
    private StackPane rootPane;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    // This method runs automatically when the FXML is loaded
    @FXML
    public void initialize() {
        System.out.println("Login View Initialized");
        // You can set focus to the username field here if desired
        // usernameField.setFocusTraversable(false);
    }

    // This method handles the button click
    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (isValidCredentials(username, password)) {
            System.out.println("Login Successful!");
            showAlert(Alert.AlertType.INFORMATION, "Success", "Welcome back, " + username + "!");

            // TODO: Load the main application scene (Dashboard) here
            // loadDashboard();

        } else {
            System.out.println("Login Failed");
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid Username or Password.");
        }
    }

    // Mock validation logic - Replace this with your actual Database check
    private boolean isValidCredentials(String username, String password) {
        // Example logic:
        return "admin".equals(username) && "1234".equals(password);
    }

    // Helper method to show pop-up messages
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}