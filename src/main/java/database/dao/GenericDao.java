package database.dao;

import java.util.List;

public interface GenericDao<T, K> {

    public void create(T o);

    public T readById(K id);

    public List<T> readAll();

    public void update(T o);

    public void delete(T o);
}