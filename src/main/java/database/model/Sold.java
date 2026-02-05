package database.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "sold")
public class Sold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sold_id")
    private int soldId;

    @Column(name = "offer_id", nullable = false, unique = true)
    private int offerId;

    @Column(name = "final_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal finalPrice;

    @Column(name = "final_date", nullable = false)
    private LocalDate finalDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id", nullable = false, unique = true, insertable = false, updatable = false)
    private Offer offer;

    public Sold() {
    }

    public Sold(int offerId, BigDecimal finalPrice, LocalDate finalDate) {
        this.offerId = offerId;
        this.finalPrice = finalPrice;
        this.finalDate = finalDate;
    }

    public int getSoldId() {
        return soldId;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }
}