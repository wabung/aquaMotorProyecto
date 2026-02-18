package login.utils;

import database.model.User;
import database.service.DealershipService;
import javafx.event.ActionEvent;
import utilsProject.HashUtils;
import utilsProject.UtilsProject;

public class UtilsLogin {

    private static DealershipService service = new DealershipService();

    public static void login(String email, String password, ActionEvent event) {
        User user = service.user.findByEmail(email);

        if (user == null || !HashUtils.verificarPassword(password, user.getPassword())) {
            UtilsProject.mostrarError("Authentication Error", "The email or password is incorrect.");
        } else {
            cargarDashboard(user, event);
        }
    }

    private static void cargarDashboard(User user, ActionEvent event) {
        if (user.getBoss() != null) {
            //UtilsProject.openScreen("/views/BossDashboard.fxml", "Panel de Control - Jefe");
        } else if (user.getMechanic() != null) {
            UtilsProject.openScreen(event, "/aquaMotor/mechanic/views/MechanicDashboard.fxml", "Workshop - Mechanic", user);
        } else if (user.getSalesPerson() != null) {
            // UtilsProject.openScreen("/views/SalesView.fxml", "Ventas");
        } else {
            UtilsProject.mostrarError("No Role Assigned", "User registered but without assigned permissions.");
        }
    }
}