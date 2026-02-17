package database.crud;

import database.dao.GenericDaoImpl;
import database.model.*;
import database.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CrudMechanic extends GenericDaoImpl<Mechanic, Integer> {

    public CrudMechanic() {
        super(Mechanic.class);
    }

    public List<Repairment> getMechanicWorkdayTasks(User user) {
        if (user == null) return new ArrayList<>();
        String hql = "FROM Repairment r JOIN FETCH r.vehicle JOIN FETCH r.mechanic m JOIN FETCH m.user WHERE m.user.userId = :uid";
        try (Session session = HibernateUtil.getSession()) {
            Query<Repairment> query = session.createQuery(hql, Repairment.class);
            query.setParameter("uid", user.getUserId());
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public int getReparationsPerMonth(User user) {
        if (user == null || user.getUserId() == 0) return 0;
        String hql = "SELECT count(r) FROM Repairment r WHERE r.mechanic.user.userId = :userId AND month(r.startDate) = 2 AND year(r.startDate) = 2026";
        try (Session session = HibernateUtil.getSession()) {
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("userId", user.getUserId());
            return query.uniqueResult().intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public int getTotalPendingAssignments(User user) {
        if (user == null || user.getUserId() == 0) return 0;
        String hql = "SELECT count(r) FROM Repairment r WHERE r.mechanic.user.userId = :userId AND r.endDate IS NULL";
        try (Session session = HibernateUtil.getSession()) {
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("userId", user.getUserId());
            return query.uniqueResult().intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isActive(User currentUser) {
        return getTotalPendingAssignments(currentUser) > 0;
    }
}