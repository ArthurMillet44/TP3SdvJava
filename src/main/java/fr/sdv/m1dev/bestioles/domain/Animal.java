package fr.sdv.m1dev.bestioles.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String color;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private String sex;

    @ManyToOne
    @JoinColumn(name = "species_id", nullable = false)
    private Species species;

    @ManyToMany(mappedBy = "animals")
    private List<Person> persons;

    public Animal() {}

    public Animal(String color, String name, String sex, Species species) {
        this.color = color;
        this.name = name;
        this.sex = sex;
        this.species = species;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getColor() {

        return color;
    }

    public void setColor(String color) {

        this.color = color;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getSex() {

        return sex;
    }

    public void setSex(String sex) {

        this.sex = sex;
    }

    public Species getSpecies() {

        return species;
    }

    public void setSpecies(Species species) {

        this.species = species;
    }

    public List<Person> getPersons() {

        return persons;
    }

    public void setPersons(List<Person> persons) {

        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Animal{id=" + id + ", name='" + name + "', species='" + species + "'}";
    }



}
