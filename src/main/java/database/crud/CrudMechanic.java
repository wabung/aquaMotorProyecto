package database.crud;

import database.dao.GenericDaoImpl;
import database.model.Mechanic;

public class CrudMechanic extends GenericDaoImpl<Mechanic, Integer> {
    public CrudMechanic() {
        super(Mechanic.class);
    }
}
