module aquaMotor.aquaMotorProyecto {
    requires javafx.controls;
    requires javafx.fxml;

    opens aquaMotor.aquaMotorProyecto to javafx.fxml;
    exports aquaMotor.aquaMotorProyecto;
}
