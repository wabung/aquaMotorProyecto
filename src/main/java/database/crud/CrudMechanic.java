package database.crud;

import database.dao.DaoMechanic;
import org.hibernate.Transaction;
import database.model.Mechanic;
import org.hibernate.Session;
import org.hibernate.query.Query;
import database.utils.HibernateUtil;

import java.util.List;

public class CrudMechanic implements DaoMechanic {

    @Override
    public void create(Mechanic u) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(u);
            tx.commit();
            System.out.println("Mechanic created with id: "+u.getMechanic_id());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Fatal Error creating the Mechanic");
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public Mechanic readById(int id) {
        Session session = HibernateUtil.getSession();
        Mechanic u = null;
        try{
            u = session.get(Mechanic.class, id);

            if(u == null){
                System.err.println("The Mechanic with that id, doesn't exist in the data base ");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }

        return u;
    }

    @Override
    public List<Mechanic> read() {
        Session session = HibernateUtil.getSession();
        List<Mechanic> lista = null;

        try{
            String hql = "FROM Mechanic";
            Query<Mechanic> query = session.createQuery(hql,Mechanic.class);
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
    public void update(Mechanic u) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(u);
            tx.commit();
            System.out.println("Mechanic updated correctly");
        }catch (Exception e){
            System.err.println("Fatal error updating the Mechanic");
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
            Mechanic u = readById(id);
            if(u != null){
                session.delete(readById(id));
                tx.commit();
                System.out.println("Mechanic deleted correctly");
            }else {
                System.err.println("The Mechanic with that id, doesn't exist in the data base ");
            }
        } catch (Exception e) {
            System.err.println("Fatal error deleting the Mechanic");
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
