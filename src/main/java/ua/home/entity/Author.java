package ua.home.entity;

import javax.persistence.*;

@Entity
@Table(name = "author", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"FIRSTNAME", "LASTNAME"})
})
public class Author {
    @Id
    @Column(name = "id_author")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "FIRSTNAME")
    String firstname;
    @Column(name = "LASTNAME")
    String lastname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
