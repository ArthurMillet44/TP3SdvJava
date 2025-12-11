package fr.sdv.m1dev.bestioles.dal;

import fr.sdv.m1dev.bestioles.domain.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {

    // TP4
    Species findFirstByCommonName(String commonName);
    List<Species> findAllByLatinNameContainingIgnoreCase(String latinNamePart);

    // TP5
    @Query("SELECT s FROM Species s ORDER BY s.commonName ASC")
    List<Species> findAllSpeciesOrderByCommonName();

    @Query("SELECT s FROM Species s WHERE s.commonName LIKE :commonName")
    List<Species> findAllSpeciesByCommonName(@Param("commonName") String commonName);



}