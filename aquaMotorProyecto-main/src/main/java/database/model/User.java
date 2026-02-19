package database.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 16)
    private String password;

    // Relaciones para poder acceder desde el Usuario a su rol específico
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Boss boss;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Mechanic mechanic;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private SalesPerson salesPerson;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boss getBoss() {
        return boss;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public SalesPerson getSalesPerson() {
        return salesPerson;
    }
}