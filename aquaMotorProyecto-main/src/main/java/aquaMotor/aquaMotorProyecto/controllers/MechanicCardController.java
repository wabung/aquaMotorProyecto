package aquaMotor.aquaMotorProyecto.controllers;

import javafx.fxml.FXML;

public class MechanicCardController {

    @FXML
    private void handleDetails() {
        try {
            aquaMotor.aquaMotorProyecto.App.setRoot("showMechanics");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
