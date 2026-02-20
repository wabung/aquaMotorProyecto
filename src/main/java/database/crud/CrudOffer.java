package database.crud;

import database.dao.GenericDaoImpl;
import database.model.Offer;

public class CrudOffer extends GenericDaoImpl<Offer, Integer> {
    public CrudOffer() {
        super(Offer.class);
    }
}
