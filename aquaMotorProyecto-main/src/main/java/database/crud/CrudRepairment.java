package database.crud;

import database.dao.DaoRepairment;
import org.hibernate.Transaction;
import database.model.Repairment;
import org.hibernate.Session;
import org.hibernate.query.Query;
import database.utils.HibernateUtil;

import java.util.List;

public class CrudRepairment implements DaoRepairment {

    @Override
    public void create(Repairment u) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(u);
            tx.commit();
            System.out.println("Repairment created with id: "+u.getRepairmentId());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Fatal Error creating the Repairment");
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public Repairment readById(int id) {
        Session session = HibernateUtil.getSession();
        Repairment u = null;
        try{
            u = session.get(Repairment.class, id);

            if(u == null){
                System.err.println("The Repairment with that id, doesn't exist in the data base ");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }

        return u;
    }

    @Override
    public List<Repairment> read() {
        Session session = HibernateUtil.getSession();
        List<Repairment> lista = null;

        try{
            String hql = "FROM Repairment";
            Query<Repairment> query = session.createQuery(hql,Repairment.class);
            lista = query.list();
        }catch (Exception e){
            System.err.println("Fatal error creating the query");
            e.printStackTrace();

        }finally {
            session.close();
        }
        return lista;
    }

    @Override
    public void update(Repairment u) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(u);
            tx.commit();
            System.out.println("Repairment updated correctly");
        }catch (Exception e){
            System.err.println("Fatal error updating the Repairment");
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Repairment u = readById(id);
            if(u != null){
                session.delete(readById(id));
                tx.commit();
                System.out.println("Repairment deleted correctly");
            }else {
                System.err.println("The Repairment with that id, doesn't exist in the data base ");
            }
        } catch (Exception e) {
            System.err.println("Fatal error deleting the Repairment");
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
