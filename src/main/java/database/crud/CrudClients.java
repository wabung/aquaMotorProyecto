package database.crud;

import database.dao.DaoClients;
import org.hibernate.Transaction;
import database.model.Client;
import org.hibernate.Session;
import org.hibernate.query.Query;
import database.utils.HibernateUtil;

import java.util.List;

public class CrudClients implements DaoClients {

    @Override
    public void create(Client c) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(c);
            tx.commit();
            System.out.println("Clients created with id: "+c.getClientId());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Fatal Error creating the Clients");
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public Client readById(int id) {
        Session session = HibernateUtil.getSession();
        Client u = null;
        try{
            u = session.get(Client.class, id);

            if(u == null){
                System.err.println("The Clients with that id, doesn't exist in the data base ");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }

        return u;
    }

    @Override
    public List<Client> read() {
        Session session = HibernateUtil.getSession();
        List<Client> lista = null;

        try{
            String hql = "FROM Client";
            Query<Client> query = session.createQuery(hql,Client.class);
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
    public void update(Client u) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(u);
            tx.commit();
            System.out.println("Clients updated correctly");
        }catch (Exception e){
            System.err.println("Fatal error updating the Clients");
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
            Client c = readById(id);
            if(c != null){
                session.delete(readById(id));
                tx.commit();
                System.out.println("Clients deleted correctly");
            }else {
                System.err.println("The Clients with that id, doesn't exist in the data base ");
            }
        } catch (Exception e) {
            System.err.println("Fatal error deleting the Clients");
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
