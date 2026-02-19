package mechanic.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WorkdayController implements Initializable {

    // --- Componentes UI ---
    @FXML private VBox tasksContainer; // El contenedor dentro del ScrollPane
    @FXML private Label assigned;
    @FXML private Label inProgress;
    @FXML private Label finished;
    @FXML private Button endWorkdayBtn;

    // --- Inyección del AppBar ---
    @FXML private AppBarController appBarController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 1. Configurar AppBar
        if (appBarController != null) {
            appBarController.setTitle("MY WORKDAY");
            appBarController.setBackButtonVisible(false); // Ocultar botón atrás si es la pantalla principal
        }

        // 2. Configurar Botón
        //endWorkdayBtn.setOnAction(e -> handleEndWorkday());

        // 3. Cargar datos simulados
        loadSimulatedTasks();
        updateStats(5, 2, 3);
    }

    /**
     * Carga dinámicamente las tarjetas de tareas.
     * En una app real, esto iteraría sobre una lista de objetos de Base de Datos.
     */
    private void loadSimulatedTasks() {
        try {
            // Ejemplo: Cargar 3 tareas distintas
            addTaskCard("V-001", "Ford Focus", "Oil Change");
            addTaskCard("V-002", "Toyota Prius", "Battery Check");
            addTaskCard("V-003", "Audi A4", "Brake Replacement");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper para cargar un FXML individual y añadirlo a la lista.
     */
    private void addTaskCard(String id, String model, String desc) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/aquaMotor/mechanic/components/ListWorkday.fxml"));

        // Asumiendo que ListWorkday.fxml es un BorderPane o AnchorPane
        Node taskNode = loader.load();

        // Opcional: Si tuvieras un mechanic.login.controller para ListWorkday, le pasarías los datos aquí:
        // VehicleCardController cardController = loader.getController();
        // cardController.setVehicleData(id, database.model, desc);

        tasksContainer.getChildren().add(taskNode);
    }

    private void updateStats(int assignedCount, int progressCount, int finishedCount) {
        assigned.setText(String.valueOf(assignedCount));
        inProgress.setText(String.valueOf(progressCount));
        finished.setText(String.valueOf(finishedCount));
    }

    private void handleEndWorkday() {
        System.out.println("Finalizando jornada laboral...");
        // Lógica para guardar tiempos, cerrar sesión, etc.
    }
}