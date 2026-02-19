package aquaMotor.aquaMotorProyecto;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class InformationCardController implements Initializable {

    @FXML
    private Label title;

    @FXML
    private Label result;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Inicialización si es necesaria
    }

    public void setTitle(String titleText) {
        if (title != null) {
            title.setText(titleText);
        }
    }

    public void setResult(String resultText) {
        if (result != null) {
            result.setText(resultText);
        }
    }

    public String getTitle() {
        return title != null ? title.getText() : "";
    }

    public String getResult() {
        return result != null ? result.getText() : "";
    }
}
