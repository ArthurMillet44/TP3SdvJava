package fr.sdv.m1dev.bestioles.dal;

import fr.sdv.m1dev.bestioles.domain.Animal;
import fr.sdv.m1dev.bestioles.domain.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    List<Animal> findAllBySpecies(Species species);

    List<Animal> findAllByColorIn(List<String> colors);

    // TP5
    @Query("SELECT COUNT(a) FROM Animal a WHERE a.sex = :sex")
    long countBySex(@Param("sex") String sex);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Person p JOIN p.animals a WHERE a = :animal")
    boolean isAPet(@Param("animal") Animal animal);

}