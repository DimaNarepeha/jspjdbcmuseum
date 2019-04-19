package ua.home.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "exhibit")
public class Exhibit {
    @Id
    @Column(name = "id_exhibit")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "exhibit_name")
    String exhibit_name;
    @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @JoinTable(name = "author",
            joinColumns = @JoinColumn(name = "id_author"),
            inverseJoinColumns = @JoinColumn(name = "id_exhibit")
    )
    Set<Author> authors;
    @ManyToOne
    @JoinColumn(name = "id_material")
    Material material;
    @ManyToOne
    @JoinColumn(name = "id_technique")
    Technique technique;
    @ManyToOne
    @JoinColumn(name = "id_hall")
    Hall hall;

}
