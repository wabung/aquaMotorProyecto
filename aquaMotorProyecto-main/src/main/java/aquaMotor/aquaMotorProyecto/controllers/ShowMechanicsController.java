package aquaMotor.aquaMotorProyecto.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.TilePane;

public class ShowMechanicsController {

    @FXML private ComboBox<String> idFilterCombo; // Filtro central por ID
    @FXML private TilePane mechanicsContainer; // Contenedor de las fichas

    @FXML
    public void initialize() {
        // Llenar el combo de filtrado
        idFilterCombo.getItems().addAll("Todos", "Mecánico Senior", "Mecánico Junior");
    }

    @FXML
    private void handleBack() {
        try {
            aquaMotor.aquaMotorProyecto.App.setRoot("bossHome");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
