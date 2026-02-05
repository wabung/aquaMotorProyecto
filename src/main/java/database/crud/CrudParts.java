package database.crud;

import database.dao.DaoParts;
import org.hibernate.Transaction;
import database.model.Parts;
import org.hibernate.Session;
import org.hibernate.query.Query;
import database.utils.HibernateUtil;

import java.util.List;

public class CrudParts implements DaoParts {

    @Override
    public void create(Parts u) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(u);
            tx.commit();
            System.out.println("Parts created with id: "+u.getPart_id());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Fatal Error creating the Parts");
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public Parts readById(int id) {
        Session session = HibernateUtil.getSession();
        Parts u = null;
        try{
            u = session.get(Parts.class, id);

            if(u == null){
                System.err.println("The Parts with that id, doesn't exist in the data base ");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }

        return u;
    }

    @Override
    public List<Parts> read() {
        Session session = HibernateUtil.getSession();
        List<Parts> lista = null;

        try{
            String hql = "FROM Parts";
            Query<Parts> query = session.createQuery(hql,Parts.class);
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
    public void update(Parts u) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(u);
            tx.commit();
            System.out.println("Parts updated correctly");
        }catch (Exception e){
            System.err.println("Fatal error updating the Parts");
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
            Parts u = readById(id);
            if(u != null){
                session.delete(readById(id));
                tx.commit();
                System.out.println("Parts deleted correctly");
            }else {
                System.err.println("The Parts with that id, doesn't exist in the data base ");
            }
        } catch (Exception e) {
            System.err.println("Fatal error deleting the Parts");
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
