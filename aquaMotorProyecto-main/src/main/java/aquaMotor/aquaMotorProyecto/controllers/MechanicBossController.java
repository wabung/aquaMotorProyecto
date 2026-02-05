package aquaMotor.aquaMotorProyecto.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class MechanicBossController {

    @FXML private BarChart<String, Number> repairChart; // Estadísticas del departamento
    @FXML private Label profitsLabel; // REPARATION PROFITS MONTH
    @FXML private Label activeRepairsLabel; // ACTIVE REPARATIONS

    @FXML
    public void initialize() {
        // Configuración de datos de ejemplo para el gráfico
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Mecánico A", 25));
        series.getData().add(new XYChart.Data<>("Mecánico B", 18));
        series.getData().add(new XYChart.Data<>("Mecánico C", 12));

        repairChart.getData().add(series);
    }

    @FXML
    private void handleBack() {
        try {
            aquaMotor.aquaMotorProyecto.App.setRoot("bossHome");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
