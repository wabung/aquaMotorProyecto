package mechanic.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

// Ya no extiende BorderPane
public class BigButtonController {

    @FXML private Button mainButton;

    // Constructor eliminado

    public void setButtonData(String text, Runnable action) {
        if (mainButton != null) {
            mainButton.setText(text);
            mainButton.setOnAction(event -> {
                if (action != null) {
                    action.run();
                }
            });
        }
    }
}