package fr.sdv.m1dev.bestioles.dal;

import fr.sdv.m1dev.bestioles.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {}
