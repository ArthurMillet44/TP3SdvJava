package fr.sdv.m1dev.bestioles.controller;

import fr.sdv.m1dev.bestioles.domain.Animal;
import fr.sdv.m1dev.bestioles.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/animal")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/{id}")
    public Animal findOne(@PathVariable("id") Integer id) {
        return this.animalService.findById(id);
    }

    @PostMapping("/create")
    public Animal create(@RequestBody @Valid Animal animalToCreate) {
        return this.animalService.create(animalToCreate);
    }

    @PutMapping("/update")
    public Animal update(@RequestBody @Valid Animal animalToUpdate) {
        return this.animalService.update(animalToUpdate);
    }

    @DeleteMapping("/delete")
    public Animal delete(@RequestBody @Valid Animal animalToDelete) {
        return this.animalService.delete(animalToDelete);
    }

    @GetMapping("/all")
    public List<Animal> findAll(){
        return this.animalService.findAll();
    }



}
