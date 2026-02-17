package mechanic.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MoreDetailsController implements Initializable {

    @FXML
    private ImageView vehicleImage;
    @FXML
    private Label lblModel;
    @FXML
    private Label lblPlate;
    @FXML
    private Label lblNotes;
    @FXML
    private VBox historyContainer;
    @FXML
    private Button btnStartTask;

    @FXML
    private AppBarController appBarController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (appBarController != null) {
            appBarController.setTitle("TASK DETAILS");
            appBarController.setBackAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Volviendo a la lista de tareas...");
                    // Aquí iría tu lógica para cerrar ventana o cambiar escena
                }
            });
        }
    }

    /**
     * Método para recibir los datos desde la vista anterior.
     *
     * @param model   Modelo del coche
     * @param plate   Matrícula
     * @param notes   Descripción del problema
     * @param history Lista de reparaciones previas
     */
    public void setTaskData(String model, String plate, String notes, List<String> history) {
        lblModel.setText(model);
        lblPlate.setText(plate);
        lblNotes.setText(notes);

        // Rellenar historial dinámicamente
        populateHistory(history);
    }

    private void populateHistory(List<String> historyItems) {
        historyContainer.getChildren().clear();

        if (historyItems == null || historyItems.isEmpty()) {
            Label placeholder = new Label("No previous history.");
            placeholder.getStyleClass().add("label-secondary");
            historyContainer.getChildren().add(placeholder);
            return;
        }

        for (String item : historyItems) {
            Label itemLabel = new Label("• " + item);
            itemLabel.getStyleClass().add("repair-item");
            historyContainer.getChildren().add(itemLabel);
        }
    }

    private void handleStartTask() {
        System.out.println("Tarea iniciada para el vehículo: " + lblPlate.getText());
        // Lógica de negocio...
    }
}