package database.crud;

import database.dao.DaoUser;
import org.hibernate.Transaction;
import database.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import database.utils.HibernateUtil;

import java.util.List;

public class CrudUser implements DaoUser {

    @Override
    public void create(User u) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(u);
            tx.commit();
            System.out.println("User created with id: "+u.getId());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Fatal Error creating the user");
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public User readById(int id) {
        Session session = HibernateUtil.getSession();
        User u = null;
        try{
            u = session.get(User.class, id);

            if(u == null){
                System.err.println("The user with that id, doesn't exist in the data base ");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }

        return u;
    }

    @Override
    public List<User> read() {
        Session session = HibernateUtil.getSession();
        List<User> lista = null;

        try{
            String hql = "FROM User";
            Query<User> query = session.createQuery(hql,User.class);
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
    public void update(User u) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(u);
            tx.commit();
            System.out.println("User updated correctly");
        }catch (Exception e){
            System.err.println("Fatal error updating the user");
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
            User u = readById(id);
            if(u != null){
                session.delete(readById(id));
                tx.commit();
                System.out.println("User deleted correctly");
            }else {
                System.err.println("The user with that id, doesn't exist in the data base ");
            }
        } catch (Exception e) {
            System.err.println("Fatal error deleting the user");
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
