package database.crud;

import database.dao.DaoBoss;
import org.hibernate.Transaction;
import database.model.Boss;
import org.hibernate.Session;
import org.hibernate.query.Query;
import database.utils.HibernateUtil;

import java.util.List;

public class CrudBoss implements DaoBoss {

    @Override
    public void create(Boss b) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(b);
            tx.commit();
            System.out.println("Boss created with id: "+b.getBoss_id());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Fatal Error creating the Boss");
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public Boss readById(int id) {
        Session session = HibernateUtil.getSession();
        Boss u = null;
        try{
            u = session.get(Boss.class, id);

            if(u == null){
                System.err.println("The Boss with that id, doesn't exist in the data base ");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }

        return u;
    }

    @Override
    public List<Boss> read() {
        Session session = HibernateUtil.getSession();
        List<Boss> lista = null;

        try{
            String hql = "FROM Boss";
            Query<Boss> query = session.createQuery(hql,Boss.class);
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
    public void update(Boss b) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(b);
            tx.commit();
            System.out.println("Boss updated correctly");
        }catch (Exception e){
            System.err.println("Fatal error updating the Boss");
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
            Boss u = readById(id);
            if(u != null){
                session.delete(readById(id));
                tx.commit();
                System.out.println("Boss deleted correctly");
            }else {
                System.err.println("The Boss with that id, doesn't exist in the data base ");
            }
        } catch (Exception e) {
            System.err.println("Fatal error deleting the Boss");
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
