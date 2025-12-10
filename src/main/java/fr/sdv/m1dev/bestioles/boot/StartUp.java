package fr.sdv.m1dev.bestioles.boot;

import fr.sdv.m1dev.bestioles.dal.AnimalRepository;
import fr.sdv.m1dev.bestioles.dal.PersonRepository;
import fr.sdv.m1dev.bestioles.dal.SpeciesRepository;
import fr.sdv.m1dev.bestioles.domain.Animal;
import fr.sdv.m1dev.bestioles.domain.Person;
import fr.sdv.m1dev.bestioles.domain.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class StartUp implements CommandLineRunner {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private SpeciesRepository speciesRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {
        animalRepository.findAll().forEach(System.out::println);

        Species s = new Species("Triceratops", "Triceratops horridus");
        speciesRepository.save(s);
        Animal a = new Animal("Noir", "Tritri", "M", s);
        animalRepository.save(a);
        animalRepository.findById(a.getId()).ifPresent(System.out::println);
        Person p = new Person(25,"Doe", "John","jean","test",true, List.of(),List.of(a));
        personRepository.save(p);
        System.out.println("Nb animaux: " + animalRepository.findAll().size());
        animalRepository.findAll().forEach(animal ->
                System.out.println(animal.getId() + " - " + animal.getName() + " - " + animal.getSpecies().getCommonName())
        );
        personRepository.delete(p);
        animalRepository.delete(a);
        speciesRepository.delete(s);

        System.out.println(this.speciesRepository.findFirstByCommonName("Chat"));
        System.out.println(this.speciesRepository.findAllByLatinNameContainingIgnoreCase("felis silvestris catus"));
        System.out.println(this.personRepository.findAllByLastnameOrFirstname("Lamarque","Paul"));
        System.out.println(this.personRepository.findAllByAgeGreaterThanEqual(45));

    }
}
