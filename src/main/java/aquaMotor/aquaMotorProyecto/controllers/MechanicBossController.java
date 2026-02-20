package aquaMotor.aquaMotorProyecto.controllers;

import aquaMotor.aquaMotorProyecto.AppBarController;
import aquaMotor.aquaMotorProyecto.App;
import database.model.User;
import database.service.DealershipService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import utils.InitializableWithUser;
import utils.UtilsProject;

import java.util.List;

public class MechanicBossController implements InitializableWithUser {

    @FXML private VBox rootPane;
    @FXML private AppBarController appBarMechanicBossController;
    @FXML private BarChart<String, Number> repairChart;
    @FXML private Label profitsLabel;
    @FXML private Label activeRepairsLabel;
    @FXML private Label vehiclesLabel;

    private User currentUser;
    private final DealershipService service = new DealershipService();

    @Override
    public void initData(User user) {
        this.currentUser = user;
        App.setCurrentUser(user);

        if (appBarMechanicBossController != null) {
            appBarMechanicBossController.setTitle("MECHANIC BOSS");
            appBarMechanicBossController.setBackButtonVisible(true);
            appBarMechanicBossController.setOnBackAction(() -> {
                ActionEvent fakeEvent = new ActionEvent(rootPane, null);
                UtilsProject.openScreen(fakeEvent,
                        "/aquaMotor/sales/boss/views/bossHome.fxml",
                        "BOSS HOME", currentUser);
            });
        }

        loadStatistics();
        loadRepairChart();
    }

    private void loadStatistics() {
        try {
            double profits = service.getProfitsCurrentMonth();
            profitsLabel.setText("$" + String.format("%.2f", profits));

            long active = service.getActiveRepairsCount();
            activeRepairsLabel.setText(String.valueOf(active));

            long vehicles = service.getTotalVehiclesCount();
            vehiclesLabel.setText(String.valueOf(vehicles));
        } catch (Exception e) {
            e.printStackTrace();
            profitsLabel.setText("$0.00");
            activeRepairsLabel.setText("0");
            vehiclesLabel.setText("0");
        }
    }

    private void loadRepairChart() {
        try {
            repairChart.getData().clear();
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            List<Object[]> rows = service.getRepairsPerMechanic();
            for (Object[] row : rows) {
                String name  = (String) row[0];
                long   count = ((Number) row[1]).longValue();
                series.getData().add(new XYChart.Data<>(name, count));
            }
            if (!rows.isEmpty()) {
                repairChart.getData().add(series);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ── Navigation called from the FXML buttons ──────────────────────

    @FXML
    private void handleRepairationRegister(ActionEvent event) {
        UtilsProject.openScreen(event,
                "/aquaMotor/sales/boss/views/reparationRegister.fxml",
                "REPARATION REGISTER", App.getCurrentUser());
    }

    @FXML
    private void handleShowRepairs(ActionEvent event) {
        UtilsProject.openScreen(event,
                "/aquaMotor/sales/boss/views/showRepairs.fxml",
                "SHOW REPAIRS", App.getCurrentUser());
    }

    @FXML
    private void handleShowMechanics(ActionEvent event) {
        UtilsProject.openScreen(event,
                "/aquaMotor/sales/boss/views/showMechanics.fxml",
                "SHOW MECHANICS", App.getCurrentUser());
    }
}
