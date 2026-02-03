package mechanic.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class ListCardController implements Initializable {

    // Elementos de la UI inyectados desde el FXML
    @FXML
    private Label vehicle_id;

    @FXML
    private Label vehicle_name;

    @FXML
    private Label description;

    @FXML
    private Button start_button;

    @FXML
    private Button details_button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configuración inicial de los botones
        //start_button.setOnAction(event -> handleStart());
        //details_button.setOnAction(event -> handleDetails());
    }

    /**
     * Método para poblar la tarjeta con datos desde fuera (ej. desde una lista).
     * @param id El ID del vehículo
     * @param model El modelo o nombre
     * @param desc La descripción
     */
    public void setVehicleData(String id, String model, String desc) {
        vehicle_id.setText(id);
        vehicle_name.setText(model);
        description.setText(desc);
    }

    // Lógica para el botón START
    private void handleStart() {
        System.out.println("Iniciando proceso para el vehículo: " + vehicle_id.getText());
        // Aquí iría tu lógica real
    }

    // Lógica para el botón DETAILS
    private void handleDetails() {
        System.out.println("Mostrando detalles de: " + vehicle_name.getText());
        // Aquí abrirías una nueva ventana o diálogo
    }
}