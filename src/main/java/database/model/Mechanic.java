package database.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mechanic")
public class Mechanic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mechanic_id")
    private int mechanic_id;

    @Column(name = "user_id", nullable = false)
    private int user_id;

    @Column(name="speciality_type", length = 100)
    private String speciality_type;

    @Column(name = "isBoss")
    private boolean isBoss = false;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    public Mechanic(int user_id, String speciality_type, boolean isBoss) {
        this.user_id = user_id;
        this.speciality_type = speciality_type;
        this.isBoss = isBoss;
    }

    public Mechanic( int user_id, boolean isBoss) {
        this.user_id = user_id;
        this.isBoss = isBoss;
    }

    public Mechanic() {

    }

    public int getMechanic_id() {
        return mechanic_id;
    }

    public void setMechanic_id(int mechanic_id) {
        this.mechanic_id = mechanic_id;
    }

    public String getSpeciality_type() {
        return speciality_type;
    }

    public void setSpeciality_type(String speciality_type) {
        this.speciality_type = speciality_type;
    }

    public int getUser_id() {
        return user_id;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public void setBoss(boolean boss) {
        isBoss = boss;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}