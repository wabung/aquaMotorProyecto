package mechanic.controller;

import database.crud.CrudMechanic;
import database.model.Repairment;
import database.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import utilsProject.UtilsProject;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class WorkdayController implements Initializable {

    @FXML
    private VBox tasksContainer;
    @FXML
    private Label assigned;
    @FXML
    private Label inProgress;
    @FXML
    private Label finished;

    // Al poner id="appBar" en FXML, JavaFX inyecta el controlador AQUI automáticamente.
    @FXML
    private AppBarController appBarController;

    private User currentUser;
    private final CrudMechanic crudMechanic = new CrudMechanic();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (appBarController != null) {
            appBarController.setBackButtonVisible(true);
        } else {
            System.err.println("AVISO: appBarController es NULL en initialize (normal si initData aún no se ha llamado)");
        }
    }

    public void initData(User user) {
        this.currentUser = user;

        if (this.currentUser != null) {
            if (appBarController != null) {
                // AHORA DEBERÍA FUNCIONAR
                System.out.println("Cambiando título para: " + currentUser.getName());
                appBarController.setTitle("MY WORKDAY : " + currentUser.getName().toUpperCase());
                appBarController.setBackButtonVisible(true);

                appBarController.setBackAction((ActionEvent event) -> {
                    UtilsProject.openScreen(event, "/aquaMotor/mechanic/views/MechanicDashboard.fxml", "MECHANIC DASHBOARD", currentUser);
                });
            } else {
                System.err.println("ERROR CRÍTICO: appBarController sigue siendo NULL.");
            }
            loadTasks();
        }
    }

    private void loadTasks() {
        if (tasksContainer == null) return;
        tasksContainer.getChildren().clear();
        int total = 0, prog = 0, done = 0;

        List<Repairment> list = crudMechanic.getMechanicWorkdayTasks(currentUser);

        for (Repairment r : list) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/aquaMotor/mechanic/components/ListCard.fxml"));
                Node node = loader.load();

                ListCardController item = loader.getController();
                String vehicleInfo = r.getVehicle().getModel() + " " + r.getVehicle().getRegistration();

                item.setVehicleData(
                        String.valueOf(r.getRepairmentId()),
                        vehicleInfo,
                        r.getDescription()
                );

                tasksContainer.getChildren().add(node);

                String status = String.valueOf(r.getStatus());
                if (status != null) {
                    if (status.equalsIgnoreCase("finished") || status.equalsIgnoreCase("Completed")) done++;
                    else if (status.equalsIgnoreCase("active") || status.equalsIgnoreCase("In Progress")) prog++;
                    else total++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        updateStats(total + prog + done, prog, done);
    }

    private void updateStats(int t, int p, int f) {
        if (assigned != null) assigned.setText(String.valueOf(t));
        if (inProgress != null) inProgress.setText(String.valueOf(p));
        if (finished != null) finished.setText(String.valueOf(f));
    }
}