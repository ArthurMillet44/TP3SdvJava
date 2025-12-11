package fr.sdv.m1dev.bestioles.dal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import fr.sdv.m1dev.bestioles.domain.Person;

@Transactional
public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public void removeAllPersonsWithNoAnimal() {
        String jpql = "DELETE FROM Person p WHERE p.animals IS EMPTY";
        entityManager.createQuery(jpql).executeUpdate();
    }

    public void createXPersons(int x) {
        for (int i = 1; i <= x; i++) {
            Person person = new Person();
            person.setFirstname("FirstName" + i);
            person.setLastname("LastName" + i);
            person.setAge(20 + (i % 30));
            person.setLogin("user" + i);
            person.setMdp("password" + i);
            entityManager.persist(person);
        }
    }
}
