package database.dao;

import database.model.Mechanic;
import java.util.List;

public interface DaoMechanic {

    public void create(Mechanic m);
    public Mechanic readById(int id);
    public List<Mechanic> read();
    public void update(Mechanic m);
    public void delete(int id);

}
