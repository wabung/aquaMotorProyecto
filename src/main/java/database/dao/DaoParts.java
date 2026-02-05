package database.dao;

import database.model.Parts;
import java.util.List;

public interface DaoParts {

    public void create(Parts p);
    public Parts readById(int id);
    public List<Parts> read();
    public void update(Parts p);
    public void delete(int id);

}