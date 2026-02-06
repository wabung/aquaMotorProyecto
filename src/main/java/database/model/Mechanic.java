package database.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mechanic")
public class Mechanic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mechanic_id")
    private int mechanicId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "speciality_type", length = 100)
    private String specialityType;

    @Column(name = "isBoss") // En la DB lo dejaste como isBoss (camelCase en columna)
    private boolean isBoss = false;

    public Mechanic() {
    }

    public Mechanic(User user, String specialityType, boolean isBoss) {
        this.user = user;
        this.specialityType = specialityType;
        this.isBoss = isBoss;
    }

    public int getMechanicId() {
        return mechanicId;
    }

    public void setMechanicId(int mechanicId) {
        this.mechanicId = mechanicId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSpecialityType() {
        return specialityType;
    }

    public void setSpecialityType(String specialityType) {
        this.specialityType = specialityType;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public void setBoss(boolean boss) {
        isBoss = boss;
    }
}