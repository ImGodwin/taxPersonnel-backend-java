package Godwin.taxSolution.repository;

import Godwin.taxSolution.entities.TaxPersonnel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaxPersonneRepository extends JpaRepository<TaxPersonnel, UUID> {

    Optional<TaxPersonnel> findByEmail(String email);

    List<TaxPersonnel> findByNameStartingWithIgnoreCase(String name);
}
