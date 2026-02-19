package database.dao;

import database.model.Offer;

import java.util.List;

public interface DaoOffer {

    public void create(Offer o);

    public Offer readById(int id);

    public List<Offer> read();

    public void update(Offer o);

    public void delete(int id);

}
