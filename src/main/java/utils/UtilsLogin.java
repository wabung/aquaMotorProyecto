package utils;

import database.crud.CrudUser;
import database.model.User;
import javafx.event.ActionEvent;

public class UtilsLogin {

    private static CrudUser crudUser = new CrudUser();

    public static void login(String email, String password, ActionEvent event) {
        System.out.println("Intentando login con email: " + email);
        User user = crudUser.findByEmail(email);

        if (user == null) {
            System.out.println("Usuario no encontrado");
            UtilsProject.mostrarError("Authentication Error", "The email or password is incorrect.");
        } else if (!HashUtils.verificarPassword(password, user.getPassword())) {
            System.out.println("Contraseña incorrecta");
            UtilsProject.mostrarError("Authentication Error", "The email or password is incorrect.");
        } else {
            System.out.println("Login exitoso para: " + user.getName());
            cargarDashboard(user, event);
        }
    }

    private static void cargarDashboard(User user, ActionEvent event) {
        System.out.println("Cargando dashboard...");
        System.out.println("Boss: " + user.getBoss());
        System.out.println("Mechanic: " + user.getMechanic());
        System.out.println("SalesPerson: " + user.getSalesPerson());
        
        if (user.getBoss() != null) {
            System.out.println("Usuario es Boss");
            UtilsProject.openScreen(event, "/aquaMotor/sales/boss/views/bossHome.fxml", "BOSS HOME", user);
        } else if (user.getMechanic() != null) {
            System.out.println("Usuario es Mechanic");
            UtilsProject.openScreen(event, "/aquaMotor/sales/mechanic/views/MechanicDashboard.fxml", "Workshop - Mechanic", user);
        } else if (user.getSalesPerson() != null) {
            System.out.println("Usuario es SalesPerson - Navegando a Sales");
            UtilsProject.openScreen(event, "/aquaMotor/sales/sales/views/homeSales.fxml", "AquaMotor - Sales", user);
        } else {
            System.out.println("Usuario sin rol asignado");
            UtilsProject.mostrarError("No Role Assigned", "User registered but without assigned permissions.");
        }
    }
}