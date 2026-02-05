package database.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    static {
        try {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure() 
                    .build();
            
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
                    
        } catch (Exception e) {
            System.err.println("Error al iniciar Hibernate: " + e);
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * Método que devuelve UNA NUEVA Session cada vez que se llama.
     * El programador es responsable de cerrar esta sesión (session.close())
     * cuando termine de usarla (como ya haces en tu DAO).
     */
    public static Session getSession() {
        return sessionFactory.openSession();
    }

    /**
     * Cierra la fábrica de sesiones. 
     * Se usa solo al cerrar la aplicación completa.
     */
    public static void closeSessionFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}