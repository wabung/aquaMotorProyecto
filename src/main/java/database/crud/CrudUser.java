package database.crud;

import database.dao.GenericDaoImpl;
import database.model.User;
import database.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CrudUser extends GenericDaoImpl<User, Integer> {
    public CrudUser() {
        super(User.class);
    }

    public User findByEmail(String email) {
        String hql = "FROM User u WHERE u.email = :email";
        Session session = HibernateUtil.getSession();
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("email", email);
        return query.uniqueResult();
    }

    public User findByCredentials(String email, String password) {
        String hql = "FROM User u WHERE u.email = :email AND u.password = :password";
        Session session = HibernateUtil.getSession();
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        return query.uniqueResult();
    }
}