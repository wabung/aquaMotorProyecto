package database.crud;

import database.dao.GenericDaoImpl;
import database.model.Repairment;
import database.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDate;

public class CrudRepairment extends GenericDaoImpl<Repairment, Integer> {
    public CrudRepairment() {
        super(Repairment.class);
    }

    /** Sum of estimatedBudget for repairments started in the current month/year. */
    public double getProfitsCurrentMonth() {
        int month = LocalDate.now().getMonthValue();
        int year  = LocalDate.now().getYear();
        String hql = "SELECT COALESCE(SUM(r.estimatedBudget), 0) FROM Repairment r "
                   + "WHERE MONTH(r.startDate) = :month AND YEAR(r.startDate) = :year";
        try (Session session = HibernateUtil.getSession()) {
            Query<Double> q = session.createQuery(hql, Double.class);
            q.setParameter("month", month);
            q.setParameter("year",  year);
            Double result = q.uniqueResult();
            return result != null ? result : 0.0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    /** Count of repairments where endDate is null (still active). */
    public long getActiveRepairsCount() {
        String hql = "SELECT COUNT(r) FROM Repairment r WHERE r.endDate IS NULL";
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery(hql, Long.class).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /** Returns rows of [mechanicName, repairCount] for the bar-chart. */
    public java.util.List<Object[]> getRepairsPerMechanic() {
        String hql = "SELECT m.user.name, COUNT(r.repairmentId) "
                   + "FROM Repairment r JOIN r.mechanic m "
                   + "GROUP BY m.mechanicId, m.user.name "
                   + "ORDER BY COUNT(r.repairmentId) DESC";
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery(hql, Object[].class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new java.util.ArrayList<>();
        }
    }
}
