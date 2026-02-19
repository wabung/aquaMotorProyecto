package database.dao;

import database.model.Repairment;
import java.util.List;

public interface DaoRepairment {

    public void create(Repairment r);
    public Repairment readById(int id);
    public List<Repairment> read();
    public void update(Repairment r);
    public void delete(int id);

}
