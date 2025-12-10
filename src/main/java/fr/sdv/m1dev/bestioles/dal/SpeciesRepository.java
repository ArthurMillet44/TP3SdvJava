package fr.sdv.m1dev.bestioles.dal;

import fr.sdv.m1dev.bestioles.domain.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {}