package database.crud;

import database.dao.GenericDaoImpl;
import database.model.Client;

public class CrudClients extends GenericDaoImpl<Client, Integer> {
    public CrudClients() {
        super(Client.class);
    }
}
