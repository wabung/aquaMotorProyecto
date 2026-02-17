package mechanic.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AppBarController {

    @FXML
    private Label titleLabel;

    @FXML
    private Button backButton;

    public void setTitle(String title) {
        if (titleLabel != null) {
            titleLabel.setText(title.toUpperCase());
        }
    }

    public void setBackButtonVisible(boolean visible) {
        if (backButton != null) {
            backButton.setVisible(visible);
            backButton.setManaged(visible);
        }
    }

    public void setBackAction(EventHandler<ActionEvent> action) {
        if (backButton != null) {
            backButton.setOnAction(action);
        }
    }
}