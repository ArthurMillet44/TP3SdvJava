package fr.sdv.m1dev.bestioles.service;

import fr.sdv.m1dev.bestioles.dal.AnimalRepository;
import fr.sdv.m1dev.bestioles.dal.SpeciesRepository;
import fr.sdv.m1dev.bestioles.domain.Animal;
import fr.sdv.m1dev.bestioles.domain.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final SpeciesRepository speciesRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository, SpeciesRepository speciesRepository) {
        this.animalRepository = animalRepository;
        this.speciesRepository = speciesRepository;
    }

    public Animal create(Animal animal) {
        Species species = speciesRepository.findById(animal.getSpecies().getId())
                .orElseThrow(() -> new RuntimeException("Species not found"));
        animal.setSpecies(species);
        return animalRepository.save(animal);
    }


    public Animal update(Animal animal){
        animalRepository.save(animal);
        return animal;
    }

    public Animal findById(Integer id){
        return animalRepository.findById(id).orElse(null);
    }

    public Animal delete(Animal animal){
        animalRepository.delete(animal);
        return animal;
    }

    public List<Animal> findAll(){
        return animalRepository.findAll();
    }
}
