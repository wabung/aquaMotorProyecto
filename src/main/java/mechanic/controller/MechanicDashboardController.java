package mechanic.controller;

import javafx.fxml.FXML;

public class MechanicDashboardController {

    // 1. Header
    @FXML private TitleCardController headerController;

    // 2. Tarjetas de Información (Nombres deben coincidir con FXML)
    @FXML private InformationCardController cardVehiclesController;
    @FXML private InformationCardController activeController;      // <--- Antes daba error aquí
    @FXML private InformationCardController cardPendingController;

    // 3. Botones Grandes
    @FXML private BigButtonController btnMyWorkDayController;      // <--- Y aquí
    @FXML private BigButtonController btnWorkInProgressController;

    @FXML
    public void initialize() {
        // Carga segura de la imagen (ruta absoluta desde resources)
        headerController.setContent("MECHANIC", "/aquaMotor/mechanic/images/engranaje.png");

        // Datos de ejemplo
        cardVehiclesController.setData("REPAIRS / MONTH", "15");
        activeController.setData("ACTIVE STATUS", "ONLINE");
        cardPendingController.setData("PENDING TASKS", "3");

        // Acciones de los botones
        btnMyWorkDayController.setButtonData("MY WORKDAY", () -> {
            System.out.println("LOG: Opening Workday View...");
        });

        btnWorkInProgressController.setButtonData("WORK IN PROGRESS", () -> {
            System.out.println("LOG: Opening Work In Progress View...");
        });
    }
}