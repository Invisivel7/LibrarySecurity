package library.buildrun.librarySecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import library.buildrun.librarySecurity.entities.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

}
