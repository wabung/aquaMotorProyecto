package database.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, updatable = false)
    private int id;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 16, nullable = false)
    private String password;

    @OneToOne(mappedBy = "boss")
    private Boss boss;

    @OneToOne(mappedBy = "mechanic")
    private Mechanic mechanic;

    @OneToOne(mappedBy = "salesPerson")
    private SalesPerson salesPerson;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "\nemail='" + email + '\'' +
                ", id=" + id + '\'' +
                ", name=" + name + '\'' +
                '}';
    }
}
