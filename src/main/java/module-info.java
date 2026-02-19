module aquaMotor.aquaMotorProyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    
    // Hibernate and persistence requirements
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires transitive java.sql;
    requires java.naming;

    // Opens for JavaFX - solo el paquete que contiene los controladores
    opens aquaMotor.aquaMotorProyecto to javafx.fxml;
    exports aquaMotor.aquaMotorProyecto;
    
    // Opens for Hibernate - permite el acceso por reflexión a las entidades
    opens database.model to org.hibernate.orm.core, javafx.base;
    
    // Exports para permitir que otros paquetes accedan a database
    exports database.model;
    exports database.dao;
    exports database.crud;
    exports database.utils;
}
