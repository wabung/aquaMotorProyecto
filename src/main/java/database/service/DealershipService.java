package database.service;

import database.crud.*;

public class DealershipService {

    public final CrudBoss boss;
    public final CrudClients clients;
    public final CrudMechanic mechanic;
    public final CrudOffer offer;
    public final CrudParts parts;
    public final CrudRepairment repairment;
    public final CrudSalesPerson salesPerson;
    public final CrudUser user;
    public final CrudVehicle vehicle;

    public DealershipService() {
        this.boss = new CrudBoss();
        this.clients = new CrudClients();
        this.mechanic = new CrudMechanic();
        this.offer = new CrudOffer();
        this.parts = new CrudParts();
        this.repairment = new CrudRepairment();
        this.salesPerson = new CrudSalesPerson();
        this.user = new CrudUser();
        this.vehicle = new CrudVehicle();
    }


}