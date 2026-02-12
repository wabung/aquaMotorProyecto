package database.crud;

import database.dao.GenericDaoImpl;
import database.model.Vehicle;

public class CrudVehicle extends GenericDaoImpl<Vehicle, Integer> {
    public CrudVehicle() {
        super(Vehicle.class);
    }
}
