package database.service;

import database.crud.*;
import database.model.Mechanic;
import database.model.Vehicle;

import java.util.List;

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

    // ── Statistics ─────────────────────────────────────────────────────────────

    /** Sum of repair budgets for the current month. */
    public double getProfitsCurrentMonth() {
        return repairment.getProfitsCurrentMonth();
    }

    /** Number of repairments without an end-date (still active). */
    public long getActiveRepairsCount() {
        return repairment.getActiveRepairsCount();
    }

    /** Total vehicles in the system. */
    public long getTotalVehiclesCount() {
        return vehicle.countAll();
    }

    /** Vehicles filtered by type (case-insensitive). */
    public long getVehicleCountByType(String type) {
        return vehicle.countByType(type);
    }

    /** All mechanics in the system. */
    public List<Mechanic> getAllMechanics() {
        return mechanic.readAll();
    }

    /** All vehicles in the system. */
    public List<Vehicle> getAllVehicles() {
        return vehicle.readAll();
    }

    /** [mechanicName, repairCount] rows for the repair bar-chart. */
    public List<Object[]> getRepairsPerMechanic() {
        return repairment.getRepairsPerMechanic();
    }
}