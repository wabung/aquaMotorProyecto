package mechanic.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ListCardController implements Initializable {

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
        if (start_button != null) start_button.setOnAction(event -> handleStart());
        if (details_button != null) details_button.setOnAction(event -> handleDetails());
    }

    public void setVehicleData(String id, String model, String desc) {
        vehicle_id.setText(id);
        vehicle_name.setText(model);
        description.setText(desc);
    }

    private void handleStart() {
        System.out.println("Iniciando proceso para el vehículo: " + vehicle_id.getText());
    }

    private void handleDetails() {
        System.out.println("Mostrando detalles de: " + vehicle_name.getText());
    }
}