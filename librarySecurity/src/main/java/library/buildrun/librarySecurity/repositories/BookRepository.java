package library.buildrun.librarySecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import library.buildrun.librarySecurity.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
