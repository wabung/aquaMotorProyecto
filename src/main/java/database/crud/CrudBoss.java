package database.crud;

import database.dao.GenericDaoImpl;
import database.model.Boss;

public class CrudBoss extends GenericDaoImpl<Boss, Integer> {
    public CrudBoss() {
        super(Boss.class);
    }
}
