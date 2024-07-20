package library.buildrun.librarySecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import library.buildrun.librarySecurity.entities.Gender;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Long> {

}
