package database.crud;

import database.dao.DaoVehicle;
import database.model.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import database.utils.HibernateUtil;

import java.util.List;

public class CrudVehicle implements DaoVehicle {
    @Override
    public void create(Vehicle vehicle) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            session.save(vehicle);
            tx.commit();
            System.out.println("Vehicle added correctly");
        } catch (Exception e) {
            System.out.println("Fatal error adding the vehicle");
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }

    @Override
    public Vehicle readById(int id) {
        Session session = HibernateUtil.getSession();
        Vehicle v = null;
        try {
            v = session.get(Vehicle.class, id);
            if(v == null){
                System.err.println("The vehicle with that id, doesn't exist in the data base ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return v;
    }

    @Override
    public List<Vehicle> read() {
        Session session = HibernateUtil.getSession();
        List<Vehicle> lista = null;

        try{
            String hql = "FROM Vehicle";
            Query<Vehicle> query = session.createQuery(hql,Vehicle.class);
            lista = query.list();
        } catch (Exception e) {
            System.err.println("Fatal error");
            e.printStackTrace();
        }finally {
            session.close();
        }

        return lista;
    }

    @Override
    public void update(Vehicle vehicle) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(vehicle);
            tx.commit();
            System.out.println("Vehicle updated correctly");
        }catch (Exception e){
            System.err.println("Fatal error updating the vehicle");
        }finally {
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Vehicle v = session.get(Vehicle.class,id);
            if(v != null){
                session.delete(v);
                System.out.println("Vehicle deleted correctly");
            }else {
                System.err.println("The vehicle with that id, doesn't exist in the data base ");
            }

        } catch (Exception e) {
            System.err.println("Fatal error deleting the vehicle");
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
