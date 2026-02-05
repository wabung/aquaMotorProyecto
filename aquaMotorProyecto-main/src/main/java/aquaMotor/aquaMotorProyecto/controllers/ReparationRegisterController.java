package aquaMotor.aquaMotorProyecto.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ReparationRegisterController {

    @FXML private javafx.scene.layout.TilePane vehicleCards; // Contenedor de tarjetas de vehículos
    @FXML private javafx.scene.layout.TilePane mechanicCards; // Contenedor de tarjetas de mecánicos

    @FXML private TextField vehicleIdField;
    @FXML private TextField mechanicIdField;
    @FXML private TextField budgetField;
    @FXML private TextField deadlineField;

    @FXML
    public void initialize() {
        // Rellenar con tarjetas de ejemplo y añadir eventos de selección
        try {
            // Vehículos de ejemplo
            for (int i = 1; i <= 6; i++) {
                final int vid = i;
                javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/aquaMotor/aquaMotorProyecto/boss/components/vehicleCard.fxml"));
                javafx.scene.Node card = loader.load();
                // Mostrar un identificador simple dentro de la tarjeta (registrationTxt)
                javafx.scene.control.Label reg = (javafx.scene.control.Label) card.lookup("#registrationTxt");
                if (reg != null) reg.setText("V-" + vid);

                card.setOnMouseClicked(e -> {
                    if (reg != null) vehicleIdField.setText(reg.getText());
                    else vehicleIdField.setText("V-" + vid);
                });
                vehicleCards.getChildren().add(card);
            }

            // Mecánicos de ejemplo
            for (int i = 1; i <= 6; i++) {
                final int mid = i;
                javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/aquaMotor/aquaMotorProyecto/boss/components/mechanicCard.fxml"));
                javafx.scene.Node card = loader.load();
                javafx.scene.control.Label name = (javafx.scene.control.Label) card.lookup("#namelabel");
                if (name != null) name.setText("Mechanic " + mid);

                card.setOnMouseClicked(e -> mechanicIdField.setText("M-" + mid));
                mechanicCards.getChildren().add(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRegisterRepair() {
        // Lógica para el botón "REGISTER REPAIR"
        System.out.println("Registrando: " + vehicleIdField.getText());
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
