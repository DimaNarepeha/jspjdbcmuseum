package ua.home.entity;

import javax.persistence.*;
import java.util.IdentityHashMap;

@Entity
@Table(name = "hall")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "hall_name", unique = true)
    String hall_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHall_name() {
        return hall_name;
    }

    public void setHall_name(String hall_name) {
        this.hall_name = hall_name;
    }
}
