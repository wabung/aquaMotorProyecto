package database.crud;

import database.dao.DaoOffer;
import org.hibernate.Transaction;
import database.model.Offer;
import org.hibernate.Session;
import org.hibernate.query.Query;
import database.utils.HibernateUtil;

import java.util.List;

public class CrudOffer implements DaoOffer {

    @Override
    public void create(Offer o) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            System.out.println("Offer created with id: " + o.getOfferId());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Fatal Error creating the Offer");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Offer readById(int id) {
        Session session = HibernateUtil.getSession();
        Offer u = null;
        try {
            u = session.get(Offer.class, id);

            if (u == null) {
                System.err.println("The Offer with that id, doesn't exist in the data base ");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }

        return u;
    }

    @Override
    public List<Offer> read() {
        Session session = HibernateUtil.getSession();
        List<Offer> lista = null;

        try {
            String hql = "FROM Offer";
            Query<Offer> query = session.createQuery(hql, Offer.class);
            lista = query.list();
        } catch (Exception e) {
            System.err.println("Fatal error creating the query");
            e.printStackTrace();

        } finally {
            session.close();
        }
        return lista;
    }

    @Override
    public void update(Offer o) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            System.out.println("Offer updated correctly");
        } catch (Exception e) {
            System.err.println("Fatal error updating the Offer");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Offer u = readById(id);
            if (u != null) {
                session.delete(readById(id));
                tx.commit();
                System.out.println("Offer deleted correctly");
            } else {
                System.err.println("The Offer with that id, doesn't exist in the data base ");
            }
        } catch (Exception e) {
            System.err.println("Fatal error deleting the Offer");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
