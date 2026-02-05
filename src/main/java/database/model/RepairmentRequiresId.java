package database.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class RepairmentRequiresId implements Serializable {

    @Column(name = "part_id")
    private int partId;

    @Column(name = "repairment_id")
    private int repairmentId;

    public RepairmentRequiresId() {
    }

    public RepairmentRequiresId(int partId, int repairmentId) {
        this.partId = partId;
        this.repairmentId = repairmentId;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public int getRepairmentId() {
        return repairmentId;
    }

    public void setRepairmentId(int repairmentId) {
        this.repairmentId = repairmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RepairmentRequiresId that = (RepairmentRequiresId) o;

        if (partId != that.partId) return false;
        return repairmentId == that.repairmentId;
    }

    @Override
    public int hashCode() {
        int result = partId;
        result = 31 * result + repairmentId;
        return result;

    }
}
