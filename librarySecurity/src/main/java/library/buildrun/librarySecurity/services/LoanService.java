package library.buildrun.librarySecurity.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import library.buildrun.librarySecurity.entities.Book;
import library.buildrun.librarySecurity.entities.Loan;
import library.buildrun.librarySecurity.repositories.BookRepository;
import library.buildrun.librarySecurity.repositories.LoanRepository;
import library.buildrun.librarySecurity.repositories.UserRepository;

@Service
public class LoanService {
	
	private final LoanRepository loanRepository;
	private final UserRepository userRepository;
	private final BookRepository bookRepository;
	
	public LoanService(LoanRepository loanRepository, UserRepository userRepository,
						BookRepository bookRepository) {
		this.loanRepository = loanRepository;
		this.userRepository = userRepository;
		this.bookRepository = bookRepository;
	}
	
	public Loan saveLoan(Loan loan, JwtAuthenticationToken token) {
		var user = userRepository.findById(UUID.fromString(token.getName()));
		Optional<Book> bookOptional = bookRepository.findById(loan.getBook().getId());
		
		loan.setUser(user.get());
		
		if(bookOptional.isPresent()) {
			Book bookExist = bookOptional.get();
			
			if(bookExist.getAmount() >= 1) {
				bookExist.setAmount(bookExist.getAmount() - 1);
				return loanRepository.save(loan);
			}
		}
		return null;
	}
	
	public Loan returnLoan(Long id, JwtAuthenticationToken token) {
		if(!loanRepository.existsById(id)) {
			throw new Error("Loan with ID " + id + " not found");
		}
		var user = userRepository.findById(UUID.fromString(token.getName()));
		Optional<Loan> optionalLoan = loanRepository.findById(id);
		
		if(optionalLoan.isPresent()) {
			Loan existingLoan = optionalLoan.get();
			
			var date = LocalDate.now();
			String status = "Return";
			existingLoan.setUser(user.get());
			
			if(existingLoan.getLoan_expiration().isBefore(date)) {
				existingLoan.getBook().setAmount(existingLoan.getBook().getAmount() + 1);
				existingLoan.setStatus(status);
				return loanRepository.save(existingLoan);
			}
			return null;
		}
		return null;
	}
	
	public List<Loan> getAllLoan(){
		return loanRepository.findAll();
	}
	
	public void deleteLoan(Long id) {
		if(!loanRepository.existsById(id)) {
			throw new Error("Loan with ID " + id + " not found");
		}
		loanRepository.deleteById(id);
	}
	
	public Loan getById(Long id) {
		return loanRepository.findById(id).orElse(null);
	}
	
	public Loan updateLoan(Long id, Loan loan) {
		Optional<Loan> optionalLoan = loanRepository.findById(id);
		if(optionalLoan.isPresent()) {
			Loan existingLoan = optionalLoan.get();
			
			existingLoan.setBook(loan.getBook());
			existingLoan.setUser(loan.getUser());
			existingLoan.setClient(loan.getClient());
			existingLoan.setLoan_begin(loan.getLoan_begin());
			existingLoan.setLoan_expiration(loan.getLoan_expiration());
			existingLoan.setStatus(loan.getStatus());
			
			
			return loanRepository.save(existingLoan);
		}
		return null;
	}

}
