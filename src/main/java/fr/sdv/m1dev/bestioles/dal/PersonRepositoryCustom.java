package fr.sdv.m1dev.bestioles.dal;

public interface PersonRepositoryCustom {
    void removeAllPersonsWithNoAnimal();

    void createXPersons (int x);
}
