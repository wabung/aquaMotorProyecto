package aquaMotor.aquaMotorProyecto;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ButtonController implements Initializable {

    @FXML
    private Button btn;

    private Runnable onClickAction;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Inicialización si es necesaria
    }

    public void setText(String text) {
        if (btn != null) {
            btn.setText(text);
        }
    }

    public void setOnClickAction(Runnable action) {
        this.onClickAction = action;
    }

    public void setOnAction(EventHandler<ActionEvent> handler) {
        if (btn != null) btn.setOnAction(handler);
    }

    @FXML
    private void handleClick(ActionEvent event) {
        if (onClickAction != null) {
            onClickAction.run();
        }
    }

    public String getText() {
        return btn != null ? btn.getText() : "";
    }
}
