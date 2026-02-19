package aquaMotor.aquaMotorProyecto.controllers;

import database.crud.CrudStatistics;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MechanicBossController {

    @FXML private BarChart<String, Number> repairChart;
    @FXML private Label profitsLabel;
    @FXML private Label activeRepairsLabel;
    @FXML private Label vehiclesLabel;
    @FXML private Button repairationRegisterBtn;
    @FXML private Button showRepairsBtn;
    @FXML private Button showMechanicsBtn;

    @FXML
    public void initialize() {
        System.out.println("MechanicBossController initialized");
        
        // Cargar datos de la base de datos
        loadStatistics();
        
        // Configuración de datos de ejemplo para el gráfico
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("NAME", 25));
        series.getData().add(new XYChart.Data<>("NAME", 17));
        series.getData().add(new XYChart.Data<>("NAME", 6));

        repairChart.getData().add(series);
    }

    /**
     * Carga las estadísticas desde la base de datos
     */
    private void loadStatistics() {
        try {
            // Obtener ganancias del mes actual
            double profits = CrudStatistics.getProfitsCurrentMonth();
            profitsLabel.setText("$" + String.format("%.2f", profits));
            
            // Obtener reparaciones activas
            long activeRepairs = CrudStatistics.getActiveRepairsCount();
            activeRepairsLabel.setText(String.valueOf(activeRepairs));
            
            // Obtener total de vehículos
            long totalVehicles = CrudStatistics.getTotalVehiclesCount();
            vehiclesLabel.setText(String.valueOf(totalVehicles));
            
            System.out.println("Statistics loaded: Profits=$" + profits + ", Active=" + activeRepairs + ", Vehicles=" + totalVehicles);
        } catch (Exception e) {
            System.err.println("Error loading statistics: " + e.getMessage());
            e.printStackTrace();
            
            // Valores por defecto en caso de error
            profitsLabel.setText("$0.00");
            activeRepairsLabel.setText("0");
            vehiclesLabel.setText("0");
        }
    }

    @FXML
    private void handleRepairationRegister(ActionEvent event) {
        System.out.println("Navigate to reparationRegister");
        try {
            aquaMotor.aquaMotorProyecto.App.setRoot("reparationRegister");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleShowRepairs(ActionEvent event) {
        System.out.println("Navigate to showRepairs");
        try {
            aquaMotor.aquaMotorProyecto.App.setRoot("showRepairs");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleShowMechanics(ActionEvent event) {
        System.out.println("Navigate to showMechanics");
        try {
            aquaMotor.aquaMotorProyecto.App.setRoot("showMechanics");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
