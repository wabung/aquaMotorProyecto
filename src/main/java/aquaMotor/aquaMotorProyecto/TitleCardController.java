package aquaMotor.aquaMotorProyecto;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class TitleCardController implements Initializable {

    @FXML
    private Label titleAppBar;

    @FXML
    private ImageView imageTitle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Inicialización si es necesaria
    }

    public void setTitle(String title) {
        if (titleAppBar != null) {
            titleAppBar.setText(title);
        }
    }

    public String getTitle() {
        return titleAppBar != null ? titleAppBar.getText() : "";
    }
}
