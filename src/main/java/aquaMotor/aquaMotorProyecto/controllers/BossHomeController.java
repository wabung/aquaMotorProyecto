package aquaMotor.aquaMotorProyecto.controllers;

import aquaMotor.aquaMotorProyecto.AppBarController;
import aquaMotor.aquaMotorProyecto.App;
import aquaMotor.aquaMotorProyecto.ButtonController;
import database.model.User;
import database.service.DealershipService;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import utils.InitializableWithUser;
import utils.UtilsProject;

public class BossHomeController implements InitializableWithUser {

    @FXML private VBox rootPane;
    @FXML private AppBarController appBarMechanicBossController;
    @FXML private ButtonController navButtonController;
    @FXML private BarChart<String, Number> salesChart;

    // Stock state panel (right side of bossHome.fxml)
    @FXML private Label newCarsLabel;
    @FXML private Label usedCarsLabel;
    @FXML private Label motorbikesLabel;
    @FXML private Label mopedLabel;

    // Top summary cards (injected from stockCard/salesCard/proposalCard includes – no fx:id on include)
    @FXML private Label totalStockLabel;
    @FXML private Label monthlySalesLabel;
    @FXML private Label activeRepairsCardLabel;

    private User currentUser;
    private final DealershipService service = new DealershipService();

    @Override
    public void initData(User user) {
        this.currentUser = user;
        App.setCurrentUser(user);

        if (appBarMechanicBossController != null) {
            appBarMechanicBossController.setTitle("BOSS HOME");
            appBarMechanicBossController.setBackButtonVisible(false);
        }

        loadStockState();
        loadTopCards();
        loadSalesChart();

        if (navButtonController != null) {
            navButtonController.setOnAction(e -> UtilsProject.openScreen(e,
                    "/aquaMotor/sales/boss/components/userSignup.fxml",
                    "USER SIGNUP", App.getCurrentUser()));
        }
    }

    private void loadStockState() {
        try {
            setLabelSafe(newCarsLabel,    String.valueOf(service.getVehicleCountByType("new")));
            setLabelSafe(usedCarsLabel,   String.valueOf(service.getVehicleCountByType("used")));
            setLabelSafe(motorbikesLabel, String.valueOf(service.getVehicleCountByType("motorbike")));
            setLabelSafe(mopedLabel,      String.valueOf(service.getVehicleCountByType("moped")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTopCards() {
        try {
            setLabelSafe(totalStockLabel,        String.valueOf(service.getTotalVehiclesCount()));
            setLabelSafe(monthlySalesLabel,      String.format("$%.2f", service.getProfitsCurrentMonth()));
            setLabelSafe(activeRepairsCardLabel, String.valueOf(service.getActiveRepairsCount()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSalesChart() {
        try {
            if (salesChart == null) return;
            salesChart.getData().clear();
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Repairs 2026");
            for (Object[] row : service.getRepairsPerMechanic()) {
                series.getData().add(new XYChart.Data<>((String) row[0], ((Number) row[1]).longValue()));
            }
            if (!series.getData().isEmpty()) {
                salesChart.getData().add(series);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setLabelSafe(Label label, String text) {
        if (label != null) label.setText(text);
    }


}
