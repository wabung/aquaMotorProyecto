package mechanic.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class WorkInProgressController implements Initializable {

    // --- Inyección del AppBar ---
    @FXML
    private AppBarController appBarController;

    @FXML
    private Label lblVehicleModel;
    @FXML
    private Label lblTaskType;
    @FXML
    private Label lblTaskName;
    @FXML
    private Label lblWorkshop;
    @FXML
    private Label lblEstimatedTime;

    @FXML
    private GridPane piecesGrid;

    @FXML
    private Button btnFinishTask;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (appBarController != null) {
            appBarController.setTitle("WORK IN PROGRESS");
            appBarController.setOnBackButtonAction(() -> {
                System.out.println("Regresando sin terminar...");
                // Tu lógica de navegación aquí
            });
        }

    }

    /**
     * Método público para inyectar los datos desde el controlador anterior.
     *
     * @param model    Modelo del vehículo
     * @param type     Tipo de tarea
     * @param name     Nombre de la tarea
     * @param workshop Taller asignado
     * @param time     Tiempo estimado
     * @param pieces   Mapa de piezas donde Key=Nombre y Value=Estado
     */
    public void setWorkData(String model, String type, String name, String workshop, String time, Map<String, String> pieces) {
        lblVehicleModel.setText(model);
        lblTaskType.setText(type);
        lblTaskName.setText(name);
        lblWorkshop.setText(workshop);
        lblEstimatedTime.setText(time);

        populatePiecesList(pieces);
    }

    private void populatePiecesList(Map<String, String> pieces) {
        piecesGrid.getChildren().clear(); // Limpiar lista anterior

        int row = 0;
        for (Map.Entry<String, String> entry : pieces.entrySet()) {
            String pieceName = entry.getKey();
            String status = entry.getValue();

            // Crear Labels
            Label nameLabel = new Label(pieceName);
            nameLabel.getStyleClass().add("label-value"); // Estilo CSS

            Label statusLabel = new Label(status);
            statusLabel.getStyleClass().add("label-status"); // Estilo CSS

            // Añadir al Grid (Columna 0 = Nombre, Columna 1 = Estado)
            piecesGrid.add(nameLabel, 0, row);
            piecesGrid.add(statusLabel, 1, row);

            row++;
        }
    }

    private void handleFinishTask() {
        System.out.println("Tarea finalizada: " + lblTaskName.getText());
    }
}