package fr.sdv.m1dev.bestioles.boot;

import fr.sdv.m1dev.bestioles.dal.AnimalRepository;
import fr.sdv.m1dev.bestioles.dal.PersonRepository;
import fr.sdv.m1dev.bestioles.dal.SpeciesRepository;
import fr.sdv.m1dev.bestioles.domain.Animal;
import fr.sdv.m1dev.bestioles.domain.Person;
import fr.sdv.m1dev.bestioles.domain.Species;
import jakarta.transaction.Transactional;
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
        System.out.println("\u001B[32mNb animaux:\u001B[0m " + animalRepository.findAll().size());
        animalRepository.findAll().forEach(animal ->
                System.out.println(animal.getId() + " - " + animal.getName() + " - " + animal.getSpecies().getCommonName())
        );
        personRepository.delete(p);
        animalRepository.delete(a);
        speciesRepository.delete(s);

        // TP4
        // Ex1
        System.out.println("\u001B[32mPremière espèce ayant pour nom chat :\u001B[0m " + this.speciesRepository.findFirstByCommonName("Chat"));
        System.out.println("\u001B[32mEspèces ayant pour nom latin felis silvestris catus :\u001B[0m " + this.speciesRepository.findAllByLatinNameContainingIgnoreCase("felis silvestris catus"));

        // Ex2
        System.out.println("\u001B[32mPersonnes ayant pour nom larmarque ou prénom Paul :\u001B[0m " + this.personRepository.findAllByLastnameOrFirstname("Lamarque","Paul"));
        System.out.println("\u001B[32mPersonnes dont l'âge >= 45 :\u001B[0m " + this.personRepository.findAllByAgeGreaterThanEqual(45));

        // Ex3
        Species catSpecies = this.speciesRepository.findFirstByCommonName("Chat");
        System.out.println("\u001B[32mAnimaux ayant pour espèce chat :\u001B[0m " + this.animalRepository.findAllBySpecies(catSpecies));
        List<String> colors = List.of("Blanc", "Noir");
        System.out.println("\u001B[32mAnimaux ayant pour couleur noir ou blanc :\u001B[0m " + this.animalRepository.findAllByColorIn(colors));

        // TP5
        // Ex1
        System.out.println("\u001B[32mEspèces ordonnées par nom commun asc :\u001B[0m " + this.speciesRepository.findAllSpeciesOrderByCommonName());
        System.out.println("\u001B[32mEspèces avec un nom comme C :\u001B[0m " + this.speciesRepository.findAllSpeciesByCommonName("%C%"));

        //Ex2
        System.out.println("\u001B[32mPersonnes dont l'age est entre 20 et 30 ans\u001B[0m " + this.personRepository.findAllPersonsByAgeBetween(20,30));
        Animal max = this.animalRepository.findById(1).orElseThrow();
        System.out.println("\u001B[32mPersonnes possédant max :\u001B[0m " + this.personRepository.findAllByAnimal(max));

        // Ex3
        System.out.println("\u001B[32mNombre animaux ayant pour sex F :\u001B[0m " + this.animalRepository.countBySex("F"));
        System.out.println("\u001B[32mAppartient à quelqu'un ?\u001B[0m " + this.animalRepository.isAPet(max));
        Animal rouge = this.animalRepository.findById(7).orElseThrow();
        System.out.println("\u001B[32mAppartient à quelqu'un ?\u001B[0m " + this.animalRepository.isAPet(rouge));

        // TP6
        // Méthode 1
        this.personRepository.removeAllPersonsWithNoAnimal();

        // Méthode 2
        this.personRepository.createXPersons(3);
    }
}
