package mechanic.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AppBarController {

    @FXML
    private Button backButton;

    @FXML
    private Label titleNav;

    /**
     * Se llama automáticamente cuando el FXML termina de cargar.
     */
    @FXML
    public void initialize() {
        // Inicialización por defecto si fuera necesaria
    }

    /**
     * Permite cambiar el título de la barra desde otra clase.
     * @param title El nuevo título a mostrar.
     */
    public void setTitle(String title) {
        if (titleNav != null) {
            titleNav.setText(title);
        }
    }

    /**
     * Define qué acción ejecutar al pulsar el botón de atrás.
     * Esto permite que la navegación la controle la clase principal.
     * * @param action Una función lambda o Runnable.
     */
    public void setOnBackButtonAction(Runnable action) {
        if (backButton != null) {
            backButton.setOnAction(e -> action.run());
        }
    }

    /**
     * Opcional: Ocultar el botón de atrás si estamos en la pantalla de inicio.
     */
    public void setBackButtonVisible(boolean visible) {
        if (backButton != null) {
            backButton.setVisible(visible);
        }
    }
}