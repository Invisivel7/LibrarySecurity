package library.buildrun.librarySecurity.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import library.buildrun.librarySecurity.entities.Costumer;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer, Long> {

	Optional<Costumer> findCostumerByEmail(String email);
}
