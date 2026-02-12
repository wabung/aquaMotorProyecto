package mechanic.controller;

import database.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WorkdayController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private VBox tasksContainer;
    @FXML
    private Label assigned;
    @FXML
    private Label inProgress;
    @FXML
    private Label finished;
    @FXML
    private Button endWorkdayBtn;

    @FXML
    private AppBarController appBarController;

    private User currentUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (appBarController != null) {
            appBarController.setTitle("MY WORKDAY");
            appBarController.setBackButtonVisible(true);
        }

        loadSimulatedTasks();
        updateStats(5, 2, 3);
    }

    public void initData(User user) {
        this.currentUser = user;
        System.out.println("Workday loaded for: " + (user != null ? user.getName() : "Unknown"));
    }

    private void loadSimulatedTasks() {
        try {
            addTaskCard("V-001", "Ford Focus", "Oil Change");
            addTaskCard("V-002", "Toyota Prius", "Battery Check");
            addTaskCard("V-003", "Audi A4", "Brake Replacement");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addTaskCard(String id, String model, String desc) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/aquaMotor/mechanic/components/ListWorkday.fxml"));
        Node taskNode = loader.load();
        tasksContainer.getChildren().add(taskNode);
    }

    private void updateStats(int assignedCount, int progressCount, int finishedCount) {
        assigned.setText(String.valueOf(assignedCount));
        inProgress.setText(String.valueOf(progressCount));
        finished.setText(String.valueOf(finishedCount));
    }

    private void handleEndWorkday() {
        System.out.println("Finalizando jornada laboral...");
    }
}