package database.dao;

import database.model.SalesPerson;
import java.util.List;

public interface DaoSalesPerson {

    public void create(SalesPerson s);
    public SalesPerson readById(int id);
    public List<SalesPerson> read();
    public void update(SalesPerson s);
    public void delete(int id);

}
