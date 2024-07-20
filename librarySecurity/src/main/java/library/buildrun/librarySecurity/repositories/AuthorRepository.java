package library.buildrun.librarySecurity.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import library.buildrun.librarySecurity.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	
	Optional<Author> findAuthorByEmail(String email);

}
