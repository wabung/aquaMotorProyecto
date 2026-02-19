package database.crud;

import database.utils.HibernateUtil;
import org.hibernate.Session;
import java.time.LocalDate;
import java.time.YearMonth;

public class CrudStatistics {

    /**
     * Obtiene la suma de presupuestos de reparaciones completadas del mes actual
     */
    public static double getProfitsCurrentMonth() {
        try (Session session = HibernateUtil.getSession()) {
            LocalDate today = LocalDate.now();
            int currentYear = today.getYear();
            int currentMonth = today.getMonthValue();
            
            String hql = "SELECT COALESCE(SUM(r.estimatedBudget), 0.0) FROM Repairment r " +
                    "WHERE YEAR(r.startDate) = :year " +
                    "AND MONTH(r.startDate) = :month " +
                    "AND r.status = 'Completed'";
            
            Double profit = session.createQuery(hql, Double.class)
                    .setParameter("year", currentYear)
                    .setParameter("month", currentMonth)
                    .getSingleResult();
            
            return profit != null ? profit : 0.0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    /**
     * Obtiene el número de reparaciones activas (en progreso)
     */
    public static long getActiveRepairsCount() {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "SELECT COUNT(r) FROM Repairment r WHERE r.status IN ('In Progress', 'Pending Parts')";
            
            return session.createQuery(hql, Long.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Obtiene el total de vehículos en la base de datos
     */
    public static long getTotalVehiclesCount() {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "SELECT COUNT(v) FROM Vehicle v";
            
            return session.createQuery(hql, Long.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
