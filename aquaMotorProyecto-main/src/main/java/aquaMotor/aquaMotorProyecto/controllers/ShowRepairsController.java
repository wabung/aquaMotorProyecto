package aquaMotor.aquaMotorProyecto.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;

public class ShowRepairsController {

    @FXML
    private ComboBox<String> statusCombo; // Selector de estado (PENDING, COMPLETED, etc.)

    @FXML
    private TextField searchField; // Campo de búsqueda "Search repair..."

    @FXML
    private TilePane repairsContainer; // El contenedor donde se muestran las tarjetas

    @FXML
    public void initialize() {
        // 1. Llenar el ComboBox con los estados posibles
        statusCombo.getItems().addAll("ALL", "PENDING", "IN PROGRESS", "COMPLETED");

        // 2. Escuchar cambios en la búsqueda (opcional)
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Buscando reparación: " + newValue);
            // Aquí filtrarías la lista de tarjetas
        });
    }

    @FXML
    private void handleFilter() {
        String selectedStatus = statusCombo.getValue();
        System.out.println("Filtrando por estado: " + selectedStatus);
    }

    @FXML
    private void handleBack() {
        try {
            aquaMotor.aquaMotorProyecto.App.setRoot("bossHome");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDetails() {
        try {
            aquaMotor.aquaMotorProyecto.App.setRoot("showMechanics");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
