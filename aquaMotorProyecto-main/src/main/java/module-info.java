module aquaMotor.aquaMotorProyecto {
    // JavaFX
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;

    // Hibernate y JPA
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.sql;
    requires java.naming;

    // Exportar y abrir paquetes de la aplicación
    opens aquaMotor.aquaMotorProyecto to javafx.fxml;
    opens aquaMotor.aquaMotorProyecto.controllers to javafx.fxml;
    exports aquaMotor.aquaMotorProyecto;
    
    // Abrir paquetes de database para Hibernate (necesario para reflexión)
    opens database.model to org.hibernate.orm.core;
    opens database.crud to org.hibernate.orm.core;
    opens database.utils to org.hibernate.orm.core;
    
    exports database.model;
    exports database.crud;
    exports database.utils;
    exports utils;
    exports utilsProject;
}
