package fr.sdv.m1dev.bestioles.service;

import fr.sdv.m1dev.bestioles.dal.AnimalRepository;
import fr.sdv.m1dev.bestioles.dal.PersonRepository;
import fr.sdv.m1dev.bestioles.dal.RoleRepository;
import fr.sdv.m1dev.bestioles.domain.Animal;
import fr.sdv.m1dev.bestioles.domain.Person;
import fr.sdv.m1dev.bestioles.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final AnimalRepository animalRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, RoleRepository roleRepository, AnimalRepository animalRepository) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.animalRepository = animalRepository;
    }

    // CREATE
    public Person create(Person person) {
        loadRolesAndAnimalsForPerson(person);

        person.setId(null);
        return personRepository.save(person);
    }

    private void loadRolesAndAnimalsForPerson(Person person) {
        if (person.getRoles() != null) {
            List<Role> roles = person.getRoles().stream()
                    .map(r -> roleRepository.findById(r.getId())
                            .orElseThrow(() -> new RuntimeException("Role not found")))
                    .toList();
            person.setRoles(roles);
        }

        if (person.getAnimals() != null) {
            List<Animal> animals = person.getAnimals().stream()
                    .map(a -> animalRepository.findById(a.getId())
                            .orElseThrow(() -> new RuntimeException("Animal not found")))
                    .toList();
            person.setAnimals(animals);
        }
    }

    // UPDATE
    public Person update(Person person) {
        if (!personRepository.existsById(person.getId())) {
            throw new RuntimeException("Person not found");
        }
        loadRolesAndAnimalsForPerson(person);

        return personRepository.save(person);
    }

    public Person findById(Integer id){
        return personRepository.findById(id).orElse(null);
    }

    public Person delete(Person person){
        personRepository.delete(person);
        return person;
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    // 5- Pour aller plus loin : Listes paginées
    public Page<Person> findAll(Pageable pageable) {
        return personRepository.findAll(pageable);
    }
}
