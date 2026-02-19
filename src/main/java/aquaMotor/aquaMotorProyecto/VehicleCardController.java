package aquaMotor.aquaMotorProyecto;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class VehicleCardController implements Initializable {

    @FXML
    private ImageView vehicleImg;

    @FXML
    private Label typeLbl;
    @FXML
    private Label modelLbl;
    @FXML
    private Label colorLbl;
    @FXML
    private Label yearLbl;
    @FXML
    private Label registrationLbl;
    @FXML
    private Label needrepairLbl;
    @FXML
    private Label priceLbl;

    @FXML
    private Label typeTxt;
    @FXML
    private Label modelTxt;
    @FXML
    private Label colorTxt;
    @FXML
    private Label yearTxt;
    @FXML
    private Label registrationTxt;
    @FXML
    private Label needrepairTxt;
    @FXML
    private Label priceTxt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Inicialización si es necesaria
    }

    // Métodos setter para actualizar los valores
    public void setType(String type) {
        if (typeTxt != null) typeTxt.setText(type);
    }

    public void setModel(String model) {
        if (modelTxt != null) modelTxt.setText(model);
    }

    public void setColor(String color) {
        if (colorTxt != null) colorTxt.setText(color);
    }

    public void setYear(String year) {
        if (yearTxt != null) yearTxt.setText(year);
    }

    public void setRegistration(String registration) {
        if (registrationTxt != null) registrationTxt.setText(registration);
    }

    public void setNeedRepair(String needRepair) {
        if (needrepairTxt != null) needrepairTxt.setText(needRepair);
    }

    public void setPrice(String price) {
        if (priceTxt != null) priceTxt.setText(price);
    }

    // Método para establecer todos los valores a la vez
    public void setVehicleData(String type, String model, String color, String year, 
                              String registration, String needRepair, String price) {
        setType(type);
        setModel(model);
        setColor(color);
        setYear(year);
        setRegistration(registration);
        setNeedRepair(needRepair);
        setPrice(price);
    }
}
