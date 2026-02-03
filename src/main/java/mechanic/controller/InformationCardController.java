package mechanic.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

// Ya no extiende BorderPane
public class InformationCardController {

    @FXML private Label titleLabel;
    @FXML private Label valueLabel;

    // Constructor eliminado para evitar doble carga

    public void setData(String title, String value) {
        if (titleLabel != null) titleLabel.setText(title);
        if (valueLabel != null) valueLabel.setText(value);
    }
}