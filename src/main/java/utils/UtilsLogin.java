package utils;

import database.crud.CrudUser;
import database.model.User;
import javafx.event.ActionEvent;

public class UtilsLogin {

    private static CrudUser daoMaster = new CrudUser();

    public static void login(String email, String password, ActionEvent event) {
        User user = daoMaster.findByCredentials(email, password);
        if (user == null) {
            UtilsProject.mostrarError("Error de Autenticación", "El email o la contraseña no coinciden.");
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
            UtilsProject.openScreen(event, "/aquaMotor/sales/sales/views/homeSales.fxml", "Ventas", user);
        } else {
            UtilsProject.mostrarError("Sin Rol", "Usuario registrado pero sin permisos asignados.");
        }
    }
}
