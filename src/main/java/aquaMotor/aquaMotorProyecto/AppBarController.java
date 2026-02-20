package aquaMotor.aquaMotorProyecto;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class AppBarController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private Label titleNav;

    private Runnable onBackAction;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar acción del botón de retroceso si es necesario
        if (backButton != null) {
            backButton.setOnAction(event -> handleBack());
        }
    }

    public void setTitle(String title) {
        if (titleNav != null) {
            titleNav.setText(title);
        }
    }

    public void setOnBackAction(Runnable action) {
        this.onBackAction = action;
    }

    /** Alias for setOnBackAction, used by mechanic controllers */
    public void setBackAction(Runnable action) {
        this.onBackAction = action;
    }

    public void setBackButtonVisible(boolean visible) {
        if (backButton != null) {
            backButton.setVisible(visible);
            backButton.setManaged(visible);
        }
    }

    @FXML
    private void handleBack() {
        if (onBackAction != null) {
            onBackAction.run();
        } else {
            // Acción por defecto: volver a home
            try {
                App.setRoot("sales/views/homeSales");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getTitle() {
        return titleNav != null ? titleNav.getText() : "";
    }
}
