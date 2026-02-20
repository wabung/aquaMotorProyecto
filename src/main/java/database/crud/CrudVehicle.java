package database.crud;

import database.dao.GenericDaoImpl;
import database.model.Vehicle;
import database.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CrudVehicle extends GenericDaoImpl<Vehicle, Integer> {
    public CrudVehicle() {
        super(Vehicle.class);
    }

    public long countAll() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("SELECT COUNT(v) FROM Vehicle v", Long.class).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public long countByType(String type) {
        String hql = "SELECT COUNT(v) FROM Vehicle v WHERE LOWER(v.type) = LOWER(:type)";
        try (Session session = HibernateUtil.getSession()) {
            Query<Long> q = session.createQuery(hql, Long.class);
            q.setParameter("type", type);
            return q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }
}
