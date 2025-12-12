package fr.sdv.m1dev.bestioles.controller;

import fr.sdv.m1dev.bestioles.domain.Person;
import fr.sdv.m1dev.bestioles.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public Person findOne(@PathVariable("id") Integer id) {
        return this.personService.findById(id);
    }

    @PostMapping("/create")
    public Person create(@RequestBody @Valid Person personToCreate) {
        return this.personService.create(personToCreate);
    }

    @PutMapping("/update")
    public Person update(@RequestBody @Valid Person personToUpdate) {
        return this.personService.update(personToUpdate);
    }

    @DeleteMapping("/delete")
    public Person delete(@RequestBody @Valid Person personToDelete) {
        return this.personService.delete(personToDelete);
    }

    @GetMapping("/all")
    public List<Person> findAll(){
        return this.personService.findAll();
    }

    // 5- Pour aller plus loin : Listes paginées
    @GetMapping("/paged")
    public Page<Person> findAllPaged(Pageable pageable) {
        return personService.findAll(pageable);
    }



}
