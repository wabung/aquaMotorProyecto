package mechanic.controller;

import database.model.User;
import database.service.DealershipService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import utilsProject.UtilsProject;

import java.net.URL;

public class MechanicDashboardController {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TitleCardController headerController;
    @FXML
    private InformationCardController cardVehiclesController;
    @FXML
    private InformationCardController activeController;
    @FXML
    private InformationCardController cardPendingController;
    @FXML
    private BigButtonController btnMyWorkDayController;
    @FXML
    private BigButtonController btnWorkInProgressController;

    private User currentUser;
    private final DealershipService service = new DealershipService();

    public void initData(User user) {
        this.currentUser = user;

        if (currentUser != null) {
            headerController.setContent(
                    "MECHANIC: " + user.getName().toUpperCase(),
                    "/aquaMotor/mechanic/images/engranaje.png"
            );
            loadDashboardStatistics();
        }
    }

    private void loadDashboardStatistics() {
        try {
            int repairsMonth = service.mechanic.getReparationsPerMonth(currentUser);
            boolean isActive = service.mechanic.isActive(currentUser);
            int totalAssigments = service.mechanic.getTotalPendingAssignments(currentUser);

            cardVehiclesController.setData("REPAIRS / MONTH", String.valueOf(repairsMonth));
            activeController.setData("ACTIVE WORK", String.valueOf(isActive).toUpperCase());
            cardPendingController.setData("TOTAL ASSIGNMENTS", String.valueOf(totalAssigments));

            System.out.println("Statistics loaded for user ID: " + currentUser.getUserId());

        } catch (Exception e) {
            e.printStackTrace();
            cardVehiclesController.setData("REPAIRS / MONTH", "Error");
        }
    }

    @FXML
    public void initialize() {
        btnMyWorkDayController.setButtonData("MY WORKDAY", () -> {
            URL path = getClass().getResource("/aquaMotor/mechanic/views/MyWorkday.fxml");
            if (path != null) {
                navigate("/aquaMotor/mechanic/views/MyWorkday.fxml", "My Workday");
            } else {
                System.err.println("ERROR: No encuentro MyWorkday.fxml en /aquaMotor/mechanic/views/");
            }
        });

        btnWorkInProgressController.setButtonData("WORK IN PROGRESS", () -> {
            URL path = getClass().getResource("/aquaMotor/mechanic/views/WorkInProgress.fxml");
            if (path != null) {
                navigate("/aquaMotor/mechanic/views/WorkInProgress.fxml", "Work in Progress");
            } else {
                System.err.println("ERROR: No encuentro WorkInProgress.fxml en /aquaMotor/mechanic/views/");
            }
        });
    }

    private void navigate(String fxmlPath, String title) {
        if (rootPane != null) {
            ActionEvent event = new ActionEvent(rootPane, null);
            UtilsProject.openScreen(event, fxmlPath, title, currentUser);
        }
    }
}