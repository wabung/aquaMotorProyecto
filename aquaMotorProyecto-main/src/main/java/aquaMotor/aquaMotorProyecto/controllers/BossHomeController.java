package aquaMotor.aquaMotorProyecto.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class BossHomeController {

    // Vinculamos el gráfico de barras
    @FXML
    private BarChart<String, Number> salesChart;

    // Vinculamos las etiquetas de Stock State (opcional, si quieres actualizarlas dinámicamente)
    @FXML private Label newCarsLabel;
    @FXML private Label usedCarsLabel;
    @FXML private Label motorbikesLabel;
    @FXML private Label mopedLabel;

    @FXML
    public void initialize() {
        setupSalesChart();
    }

    /**
     * Configura y llena el gráfico de estadísticas del departamento de ventas.
     */
    private void setupSalesChart() {
        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName("Sales 2026");

        // Datos de ejemplo para las barras
        dataSeries.getData().add(new XYChart.Data<>("JAN", 15));
        dataSeries.getData().add(new XYChart.Data<>("FEB", 22));
        dataSeries.getData().add(new XYChart.Data<>("MAR", 18));
        dataSeries.getData().add(new XYChart.Data<>("APR", 25));

        salesChart.getData().add(dataSeries);
    }

    // --- Navegación entre pantallas ---
    @FXML
    private void openShowMechanics() {
        try {
            aquaMotor.aquaMotorProyecto.App.setRoot("showMechanics");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openShowRepairs() {
        try {
            aquaMotor.aquaMotorProyecto.App.setRoot("showRepairs");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openReparationRegister() {
        try {
            aquaMotor.aquaMotorProyecto.App.setRoot("reparationRegister");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openMechanicBoss() {
        try {
            aquaMotor.aquaMotorProyecto.App.setRoot("mechanicBoss");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openUserSignup() {
        try {
            aquaMotor.aquaMotorProyecto.App.setRoot("UserSignup");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
