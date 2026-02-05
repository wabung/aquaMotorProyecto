module aquaMotor.aquaMotorProyecto {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;

    opens aquaMotor.aquaMotorProyecto to javafx.fxml;
    opens aquaMotor.aquaMotorProyecto.controllers to javafx.fxml; // Abrir paquete controllers para que FXMLLoader pueda instanciar controladores
    exports aquaMotor.aquaMotorProyecto;
}
