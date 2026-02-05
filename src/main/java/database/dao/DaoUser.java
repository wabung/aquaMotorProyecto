package database.dao;

import database.model.User;

import java.util.List;

public interface DaoUser {

    public void create(User u);
    public User readById(int id);
    public List<User> read();
    public void update(User u);
    public void delete(int id);

}
