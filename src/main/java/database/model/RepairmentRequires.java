package database.model;

import jakarta.persistence.*;

@Entity
@Table(name = "repairmentrequires")
public class RepairmentRequires {

    @EmbeddedId
    private RepairmentRequiresId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("partId")
    @JoinColumn(name = "part_id", nullable = false)
    private Parts part;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("repairmentId")
    @JoinColumn(name = "repairment_id", nullable = false)
    private Repairment repairment;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public RepairmentRequires() {
    }

    public RepairmentRequires(Parts part, Repairment repairment, int quantity) {
        this.part = part;
        this.repairment = repairment;
        this.quantity = quantity;
        this.id = new RepairmentRequiresId(part.getPart_id(), repairment.getRepairmentId());
    }

    public RepairmentRequiresId getId() {
        return id;
    }

    public void setId(RepairmentRequiresId id) {
        this.id = id;
    }

    public Parts getPart() {
        return part;
    }

    public void setPart(Parts part) {
        this.part = part;
    }

    public Repairment getRepairment() {
        return repairment;
    }

    public void setRepairment(Repairment repairment) {
        this.repairment = repairment;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}