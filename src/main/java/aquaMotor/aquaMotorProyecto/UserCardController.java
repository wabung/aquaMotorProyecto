package aquaMotor.aquaMotorProyecto;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class UserCardController implements Initializable {

    @FXML
    private HBox userCard;

    @FXML
    private Label namelabel;

    @FXML
    private Label emaillabel;

    @FXML
    private Label phonelabel;

    @FXML
    private ImageView userImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Inicialización si es necesaria
    }

    public void setName(String name) {
        if (namelabel != null) {
            namelabel.setText(name);
        }
    }

    public void setEmail(String email) {
        if (emaillabel != null) {
            emaillabel.setText(email);
        }
    }

    public void setPhone(String phone) {
        if (phonelabel != null) {
            phonelabel.setText(phone);
        }
    }

    public void setUserData(String name, String email, String phone) {
        setName(name);
        setEmail(email);
        setPhone(phone);
    }

    public String getName() {
        return namelabel != null ? namelabel.getText() : "";
    }

    public String getEmail() {
        return emaillabel != null ? emaillabel.getText() : "";
    }

    public String getPhone() {
        return phonelabel != null ? phonelabel.getText() : "";
    }
}
