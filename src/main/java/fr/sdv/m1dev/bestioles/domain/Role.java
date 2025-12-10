package fr.sdv.m1dev.bestioles.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String label;

    @ManyToMany(mappedBy = "roles")
    private List<Person> persons;

    public Role() {}

    public Role(String label) {

        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getLabel() {

        return label;
    }

    public void setLabel(String label) {

        this.label = label;
    }

    public List<Person> getPersons() {

        return persons;
    }

    public void setPersons(List<Person> persons) {

        this.persons = persons;
    }
}
