package fr.sdv.m1dev.bestioles.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer age;

    @Column(length = 50, nullable = false)
    private String firstname;

    @Column(length = 50, nullable = false)
    private String lastname;

    @Column(length = 50, nullable = false, unique = true)
    private String login;

    @Column(length = 100, nullable = false)
    private String mdp;

    @Column(nullable = false)
    private Boolean active = true;

    @ManyToMany
    @JoinTable(
            name = "person_role",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @ManyToMany
    @JoinTable(
            name = "person_animals",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "animals_id")
    )
    private List<Animal> animals;

    public Person() {}

    public Person(Integer age, String firstname, String lastname, String login, String mdp, Boolean active, List<Role> roles, List<Animal> animals) {
        this.age = age;
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.mdp = mdp;
        this.active = active;
        this.roles = roles;
        this.animals = animals;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public String getLogin() {

        return login;
    }

    public void setLogin(String login) {

        this.login = login;
    }

    public String getMdp() {

        return mdp;
    }

    public void setMdp(String mdp) {

        this.mdp = mdp;
    }

    public Boolean getActive() {

        return active;
    }

    public void setActive(Boolean active) {

        this.active = active;
    }

    public List<Role> getRoles() {

        return roles;
    }

    public void setRoles(List<Role> roles) {

        this.roles = roles;
    }

    public List<Animal> getAnimals() {

        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
}
