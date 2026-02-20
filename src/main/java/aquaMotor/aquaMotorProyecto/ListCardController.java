package aquaMotor.aquaMotorProyecto;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ListCardController {

    @FXML
    private Label lblId;

    @FXML
    private Label lblVehicle;

    @FXML
    private Label lblDescription;

    public void setVehicleData(String id, String vehicle, String description) {
        if (lblId != null) lblId.setText("#" + id);
        if (lblVehicle != null) lblVehicle.setText(vehicle);
        if (lblDescription != null) lblDescription.setText(description);
    }
}
