package aquaMotor.aquaMotorProyecto;

import database.crud.CrudMechanic;
import database.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import utils.UtilsProject;

import utils.InitializableWithUser;

public class MechanicDashboardController implements InitializableWithUser {

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
    private final CrudMechanic crudMechanic = new CrudMechanic();

    public void initData(User user) {
        this.currentUser = user;

        if (currentUser != null) {
            headerController.setTitle(
                    "MECHANIC: " + user.getName().toUpperCase(),
                    "/aquaMotor/mechanic/images/engranaje.png"
            );
            loadDashboardStatistics();
        }
    }

    private void loadDashboardStatistics() {
        try {
            int repairsMonth = crudMechanic.getReparationsPerMonth(currentUser);
            int totalAssigments = crudMechanic.getTotalPendingAssignments(currentUser);

            cardVehiclesController.setData("REPAIRS / MONTH", String.valueOf(repairsMonth));
            activeController.setData("ACTIVE WORK", totalAssigments > 0 ? "YES" : "NO");
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
            navigate("/aquaMotor/sales/mechanic/views/MyWorkday.fxml", "My Workday");
        });

        btnWorkInProgressController.setButtonData("WORK IN PROGRESS", () -> {
            navigate("/aquaMotor/sales/mechanic/views/WorkInProgress.fxml", "Work in Progress");
        });
    }

    private void navigate(String fxmlPath, String title) {
        if (rootPane != null) {
            ActionEvent event = new ActionEvent(rootPane, null);
            UtilsProject.openScreen(event, fxmlPath, title, currentUser);
        }
    }
}