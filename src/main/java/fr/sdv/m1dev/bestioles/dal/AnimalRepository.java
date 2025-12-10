package fr.sdv.m1dev.bestioles.dal;

import fr.sdv.m1dev.bestioles.domain.Animal;
import fr.sdv.m1dev.bestioles.domain.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    List<Animal> findAllBySpecies(Species species);

    List<Animal> findAllByColorIn(List<String> colors);

}