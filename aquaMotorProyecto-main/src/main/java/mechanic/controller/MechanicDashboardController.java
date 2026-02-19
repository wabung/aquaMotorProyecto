package mechanic.controller;

import database.model.User;
import javafx.fxml.FXML;

public class MechanicDashboardController {

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

    public void initData(User user) {
        this.currentUser = user;
        headerController.setContent(
                "MECHANIC: " + user.getName().toUpperCase(),
                "/aquaMotor/mechanic/images/engranaje.png"
        );
        System.out.println("Dashboard cargado para: " + user.getName());
    }

    @FXML
    public void initialize() {
        cardVehiclesController.setData("REPAIRS / MONTH", "15");
        activeController.setData("ACTIVE STATUS", "ONLINE");
        cardPendingController.setData("PENDING TASKS", "3");

        btnMyWorkDayController.setButtonData("MY WORKDAY", () -> {
            System.out.println("LOG: Opening Workday View...");
        });

        btnWorkInProgressController.setButtonData("WORK IN PROGRESS", () -> {
            System.out.println("LOG: Opening Work In Progress View...");
        });
    }
}