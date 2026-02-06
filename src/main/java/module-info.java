module aquaMotorProyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;

    opens mechanic.controller to javafx.fxml;
    opens login.controller to javafx.fxml;
    opens mainApp to javafx.graphics, javafx.fxml;

    opens database.model to org.hibernate.orm.core, javafx.base;

    exports mainApp;
}