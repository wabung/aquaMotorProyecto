package database.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id") // Antes era "OfferId"
    private int offerId;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "salesperson_id", nullable = false)
    private SalesPerson salesperson;

    @Column(name = "deadline", nullable = false)
    private Date deadline;

    @Column(name = "price_offer", nullable = false) // Antes era "PriceOffer"
    private BigDecimal priceOffer;

    public Offer() {
    }

    // Getters y Setters
    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public SalesPerson getSalesperson() {
        return salesperson;
    }

    public void setSalesperson(SalesPerson salesperson) {
        this.salesperson = salesperson;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public BigDecimal getPriceOffer() {
        return priceOffer;
    }

    public void setPriceOffer(BigDecimal priceOffer) {
        this.priceOffer = priceOffer;
    }
}