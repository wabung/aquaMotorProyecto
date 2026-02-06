package database.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "sold")
public class Sold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sold_id")
    private int soldId;

    @OneToOne
    @JoinColumn(name = "offer_id", nullable = false, unique = true)
    private Offer offer;

    @Column(name = "final_price", nullable = false)
    private BigDecimal finalPrice;

    @Column(name = "final_date", nullable = false)
    private Date finalDate;

    public Sold() {
    }

    // Getters y Setters...
    public int getSoldId() {
        return soldId;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }
}