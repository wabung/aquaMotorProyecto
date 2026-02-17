package mechanic.controller;

import database.model.Repairment;
import database.model.RepairmentStatus;
import database.model.User;
import database.service.DealershipService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import mechanic.controller.AppBarController;
import utilsProject.UtilsProject;

import java.net.URL;
import java.util.ResourceBundle;

public class WorkInProgressController implements Initializable {

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

    private User currentUser;
    private Repairment currentRepairment;
    private DealershipService dealershipService = new DealershipService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (btnFinishTask != null) {
            btnFinishTask.setOnAction(event -> handleFinishTask());
        }
    }

    public void initData(User user, Repairment repairment) {
        this.currentUser = user;
        this.currentRepairment = repairment;

        if (appBarController != null) {
            appBarController.setTitle("WORK IN PROGRESS");
            appBarController.setBackButtonVisible(true);
            appBarController.setBackAction(event -> {
                UtilsProject.openScreen(event, "/aquaMotor/mechanic/views/MechanicDashboard.fxml", "MECHANIC DASHBOARD", currentUser);
            });
        }

        if (currentRepairment != null) {
            lblVehicleModel.setText(currentRepairment.getVehicle().getModel());
            lblTaskType.setText(currentRepairment.getVehicle().getType().toUpperCase());
            lblTaskName.setText(currentRepairment.getDescription());
            lblWorkshop.setText("Taller Central");
            lblEstimatedTime.setText(currentRepairment.getEstimatedBudget() + " €");

            loadRepairmentParts();
        }
    }

    private void loadRepairmentParts() {
        piecesGrid.getChildren().clear();
    }

    private void handleFinishTask() {
        if (currentRepairment != null) {
            currentRepairment.setStatus(RepairmentStatus.valueOf("finished"));
            dealershipService.repairment.update(currentRepairment);

            ActionEvent event = new ActionEvent(btnFinishTask, null);
            UtilsProject.openScreen(event, "/aquaMotor/mechanic/views/MechanicDashboard.fxml", "MECHANIC DASHBOARD", currentUser);
        }
    }
}