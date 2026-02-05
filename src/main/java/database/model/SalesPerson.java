package database.model;

import jakarta.persistence.*;

@Entity
@Table(name = "salesperson")
public class SalesPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salesperson_id")
    private int salespersonId;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    public SalesPerson() {
    }

    public SalesPerson(int userId) {
        this.userId = userId;
    }

    public int getSalespersonId() {
        return salespersonId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}