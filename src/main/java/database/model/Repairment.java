package database.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "repairment")
public class Repairment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repairment_id")
    private int repairmentId;

    @Column(name = "vehicle_id", nullable = false)
    private int vehicleId;

    @Column(name = "mechanic_id", nullable = false)
    private int mechanicId;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "estimated_budget", precision = 10, scale = 2)
    private BigDecimal estimatedBudget;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false, insertable = false, updatable = false)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mechanic_id", nullable = false, insertable = false, updatable = false)
    private Mechanic mechanic;

    @OneToMany(mappedBy = "repairment", cascade = CascadeType.ALL)
    private List<RepairmentRequires> repairmentRequires;

    public Repairment() {
    }

    public Repairment(int vehicleId, int mechanicId, LocalDate startDate, String status, BigDecimal estimatedBudget) {
        this.vehicleId = vehicleId;
        this.mechanicId = mechanicId;
        this.startDate = startDate;
        this.status = status;
        this.estimatedBudget = estimatedBudget;
    }

    public int getRepairmentId() {
        return repairmentId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getMechanicId() {
        return mechanicId;
    }

    public void setMechanicId(int mechanicId) {
        this.mechanicId = mechanicId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getEstimatedBudget() {
        return estimatedBudget;
    }

    public void setEstimatedBudget(BigDecimal estimatedBudget) {
        this.estimatedBudget = estimatedBudget;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public List<RepairmentRequires> getRepairmentRequires() {
        return repairmentRequires;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRepairmentRequires(List<RepairmentRequires> repairmentRequires) {
        this.repairmentRequires = repairmentRequires;
    }
}