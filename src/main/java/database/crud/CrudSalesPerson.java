package database.crud;

import database.dao.DaoSalesPerson;
import org.hibernate.Transaction;
import database.model.SalesPerson;
import org.hibernate.Session;
import org.hibernate.query.Query;
import database.utils.HibernateUtil;

import java.util.List;

public class CrudSalesPerson implements DaoSalesPerson {

    @Override
    public void create(SalesPerson u) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(u);
            tx.commit();
            System.out.println("SalesPerson created with id: "+u.getSalespersonId());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Fatal Error creating the SalesPerson");
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public SalesPerson readById(int id) {
        Session session = HibernateUtil.getSession();
        SalesPerson u = null;
        try{
            u = session.get(SalesPerson.class, id);

            if(u == null){
                System.err.println("The SalesPerson with that id, doesn't exist in the data base ");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }

        return u;
    }

    @Override
    public List<SalesPerson> read() {
        Session session = HibernateUtil.getSession();
        List<SalesPerson> lista = null;

        try{
            String hql = "FROM SalesPerson";
            Query<SalesPerson> query = session.createQuery(hql,SalesPerson.class);
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
    public void update(SalesPerson u) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(u);
            tx.commit();
            System.out.println("SalesPerson updated correctly");
        }catch (Exception e){
            System.err.println("Fatal error updating the SalesPerson");
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
            SalesPerson u = readById(id);
            if(u != null){
                session.delete(readById(id));
                tx.commit();
                System.out.println("SalesPerson deleted correctly");
            }else {
                System.err.println("The SalesPerson with that id, doesn't exist in the data base ");
            }
        } catch (Exception e) {
            System.err.println("Fatal error deleting the SalesPerson");
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
