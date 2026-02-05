package database.model;

import jakarta.persistence.*;

@Entity
@Table(name = "boss")
public class Boss {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boss_id")
    private int boss_id;

    @Column(name = "user_id", nullable = false)
    private int user_id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id", nullable = false, unique = true)
    private User user;

    public Boss(int user_id) {
        this.user_id = user_id;
    }

    public Boss() {}

    public int getUser_id() {
        return user_id;
    }

    public int getBoss_id() {
        return boss_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}