package database.crud;

import database.dao.GenericDaoImpl;
import database.model.Repairment;

public class CrudRepairment extends GenericDaoImpl<Repairment, Integer> {
    public CrudRepairment() {
        super(Repairment.class);
    }
}
