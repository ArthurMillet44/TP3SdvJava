package fr.sdv.m1dev.bestioles;

import fr.sdv.m1dev.bestioles.dal.AnimalRepository;
import fr.sdv.m1dev.bestioles.dal.PersonRepository;
import fr.sdv.m1dev.bestioles.dal.SpeciesRepository;
import fr.sdv.m1dev.bestioles.domain.Animal;
import fr.sdv.m1dev.bestioles.domain.Person;
import fr.sdv.m1dev.bestioles.domain.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BestiolesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BestiolesApplication.class, args);
    }


}
