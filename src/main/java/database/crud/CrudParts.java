package database.crud;

import database.dao.GenericDaoImpl;
import database.model.Parts;

public class CrudParts extends GenericDaoImpl<Parts, Integer> {
    public CrudParts() {
        super(Parts.class);
    }
}
