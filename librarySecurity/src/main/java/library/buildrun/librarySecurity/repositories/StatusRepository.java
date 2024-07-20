package library.buildrun.librarySecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import library.buildrun.librarySecurity.entities.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

}
