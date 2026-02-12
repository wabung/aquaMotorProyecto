package database.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import database.utils.HibernateUtil;

import java.util.List;

public abstract class GenericDaoImpl<T, K> implements GenericDao<T, K> {

    private final Class<T> type;

    public GenericDaoImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public void create(T o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();
            session.persist(o);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
        System.out.println("creado exitosamente");
    }

    @Override
    public T readById(K id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.find(type, id);
        }
    }

    @Override
    public List<T> readAll() {
        try (Session session = HibernateUtil.getSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(type);
            Root<T> root = query.from(type);
            query.select(root);
            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public void update(T o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();
            session.merge(o);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
        System.out.println("actualizado");
    }

    @Override
    public void delete(T o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();
            Object managed = session.contains(o) ? o : session.merge(o);
            session.remove(managed);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
        System.out.println("Eliminado");
    }
}