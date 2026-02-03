module aquaMotorProyecto {
    // Módulos necesarios de JavaFX
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    // ABRIR PAQUETES:
    // Permite a javafx.fxml acceder a tus controladores para inyectar @FXML e inicializar componentes
    opens mechanic.controller to javafx.fxml;
    opens login.controller to javafx.fxml;

    // Permite a javafx.graphics acceder a tu MainApp para lanzarla (start method)
    opens mainApp to javafx.graphics, javafx.fxml;

    // EXPORTAR PAQUETES:
    // Hace visible tu clase principal al resto del sistema
    exports mainApp;
}