package database.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OfferId")
    private Integer offerId;

    @Column(name = "VehicleId", nullable = false)
    private Integer vehicleId;

    @Column(name = "ClientId", nullable = false)
    private Integer clientId;

    @Column(name = "SalespersonId", nullable = false)
    private Integer salespersonId;

    @Column(name = "Deadline", nullable = false)
    private LocalDate deadline;

    @Column(name = "PriceOffer", nullable = false, precision = 15, scale = 2)
    private BigDecimal priceOffer;

    public Offer() {
    }

    public Offer(Integer vehicleId, Integer clientId, Integer salespersonId,
                 LocalDate deadline, BigDecimal priceOffer) {
        this.vehicleId = vehicleId;
        this.clientId = clientId;
        this.salespersonId = salespersonId;
        this.deadline = deadline;
        this.priceOffer = priceOffer;
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getSalespersonId() {
        return salespersonId;
    }

    public void setSalespersonId(Integer salespersonId) {
        this.salespersonId = salespersonId;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public BigDecimal getPriceOffer() {
        return priceOffer;
    }

    public void setPriceOffer(BigDecimal priceOffer) {
        this.priceOffer = priceOffer;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "offerId=" + offerId +
                ", vehicleId=" + vehicleId +
                ", clientId=" + clientId +
                ", salespersonId=" + salespersonId +
                ", deadline=" + deadline +
                ", priceOffer=" + priceOffer +
                '}';
    }
}