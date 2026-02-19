package database.dao;

import database.model.Boss;
import java.util.List;

public interface DaoBoss {

    public void create(Boss b);
    public Boss readById(int id);
    public List<Boss> read();
    public void update(Boss b);
    public void delete(int id);

}
