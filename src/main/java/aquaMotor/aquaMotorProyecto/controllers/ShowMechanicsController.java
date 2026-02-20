package aquaMotor.aquaMotorProyecto.controllers;

import aquaMotor.aquaMotorProyecto.AppBarController;
import aquaMotor.aquaMotorProyecto.App;
import database.model.User;
import database.service.DealershipService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import utils.InitializableWithUser;
import utils.UtilsProject;

public class ShowMechanicsController implements InitializableWithUser {

    @FXML private AnchorPane rootPane;
    @FXML private AppBarController appBarController;

    private User currentUser;
    private final DealershipService service = new DealershipService();

    @Override
    public void initData(User user) {
        this.currentUser = user;
        App.setCurrentUser(user);

        if (appBarController != null) {
            appBarController.setTitle("SHOW MECHANICS");
            appBarController.setBackButtonVisible(true);
            appBarController.setOnBackAction(() -> {
                ActionEvent fakeEvent = new ActionEvent(rootPane, null);
                UtilsProject.openScreen(fakeEvent,
                        "/aquaMotor/sales/boss/views/mechanicBoss.fxml",
                        "MECHANIC BOSS", currentUser);
            });
        }
    }
}
