package database.dao;

import database.model.Client;
import java.util.List;

public interface DaoClients {

    public void create(Client c);
    public Client readById(int id);
    public List<Client> read();
    public void update(Client c);
    public void delete(int id);

}
