package database.crud;

import database.dao.GenericDaoImpl;
import database.model.SalesPerson;

public class CrudSalesPerson extends GenericDaoImpl<SalesPerson, Integer> {
    public CrudSalesPerson() {
        super(SalesPerson.class);
    }
}
