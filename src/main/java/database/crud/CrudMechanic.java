package database.crud;

import database.dao.GenericDaoImpl;
import database.model.*;
import database.utils.HibernateUtil;
import org.hibernate.Session;

public class CrudMechanic extends GenericDaoImpl<Mechanic, Integer> {

    public CrudMechanic() {
        super(Mechanic.class);
    }

    public int getReparationsPerMonth(User user) {
        if (user == null || user.getUserId() == 0) return 0;

        String hql = "SELECT count(r) FROM Repairment r " +
                "WHERE r.mechanic.user.userId = :userId " +
                "AND month(r.startDate) = :targetMonth " +
                "AND year(r.startDate) = :targetYear";

        try {
            Session session = HibernateUtil.getSession();

            int month = 2;
            int year = 2026;

            Long count = (Long) session.createQuery(hql)
                    .setParameter("userId", user.getUserId())
                    .setParameter("targetMonth", month)
                    .setParameter("targetYear", year)
                    .uniqueResult();

            return count != null ? count.intValue() : 0;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean isActive(User user) {
        if (user == null || user.getUserId() == 0) return false;

        String hql = "SELECT count(r) FROM Repairment r " +
                "WHERE r.mechanic.user.userId = :userId " +
                "AND r.endDate IS NULL";

        try {
            Session session = HibernateUtil.getSession();

            Long count = (Long) session.createQuery(hql)
                    .setParameter("userId", user.getUserId())
                    .uniqueResult();

            return count != null && count > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getTotalPendingAssignments(User user) {
        if (user == null || user.getUserId() == 0) return 0;

        String hql = "SELECT count(r) FROM Repairment r " +
                "WHERE r.mechanic.user.userId = :userId " +
                "AND r.endDate IS NULL";

        try {
            Session session = HibernateUtil.getSession();

            Long count = (Long) session.createQuery(hql)
                    .setParameter("userId", user.getUserId())
                    .uniqueResult();

            return count != null ? count.intValue() : 0;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}