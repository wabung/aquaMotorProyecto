package database.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private int vehicle_id;

    @Column(name = "registration", length = 10, nullable = false, updatable = false)
    private String regitration;

    @Column(name = "database.model", length = 100, nullable = false)
    private String model;

    @Column(name = "type", length = 100, nullable = false)
    private String type;

    @Column(name = "entry_date", nullable = false)
    private LocalDate entry_date;

    @Column(name = "estimated_price", nullable = false, precision = 15, scale = 2)
    private double estimated_price;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Offer> offers = new ArrayList<>();

    @OneToMany(mappedBy = "repairment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Repairment> repairments = new ArrayList<>();

    public Vehicle(LocalDate entry_date, double estimated_price, String type, String model, String regitration) {
        this.entry_date = entry_date;
        this.estimated_price = estimated_price;
        this.type = type;
        this.model = model;
        this.regitration = regitration;
    }

    public Vehicle() {

    }

    public int getVehicle_id() {
        return vehicle_id;
    }


    public String getRegitration() {
        return regitration;
    }

    public void setRegitration(String regitration) {
        this.regitration = regitration;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(LocalDate entry_date) {
        this.entry_date = entry_date;
    }

    public double getEstimated_price() {
        return estimated_price;
    }

    public void setEstimated_price(double estimated_price) {
        this.estimated_price = estimated_price;
    }
}
