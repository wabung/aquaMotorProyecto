package database.dao;

import database.model.Vehicle;
import java.util.List;

public interface DaoVehicle {

    public void create(Vehicle vehicle);
    public Vehicle readById(int id);
    public List<Vehicle> read();
    public void update(Vehicle vehicle);
    public void delete(int id);

}
