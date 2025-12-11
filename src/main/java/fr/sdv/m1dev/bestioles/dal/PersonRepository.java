package fr.sdv.m1dev.bestioles.dal;

import fr.sdv.m1dev.bestioles.domain.Animal;
import fr.sdv.m1dev.bestioles.domain.Person;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>, PersonRepositoryCustom {
    List<Person> findAllByLastnameOrFirstname(String lastName, String firstName);

    List<Person> findAllByAgeGreaterThanEqual(Integer age);

    //TP5
    @Query("SELECT p FROM Person p WHERE p.age BETWEEN :ageMin AND :ageMax")
    List<Person> findAllPersonsByAgeBetween(@Param("ageMin") Integer ageMin, @Param("ageMax") Integer ageMax);

    @Query("SELECT p FROM Person p JOIN p.animals a WHERE a = :animal")
    List<Person> findAllByAnimal(@Param("animal") Animal animal);

    @Modifying(clearAutomatically = true)
    @Transactional
    void deleteAllByLastname(String lastname);
}
