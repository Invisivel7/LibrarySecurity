package library.buildrun.librarySecurity.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import library.buildrun.librarySecurity.entities.Loan;
import library.buildrun.librarySecurity.entities.Returns;
import library.buildrun.librarySecurity.repositories.LoanRepository;
import library.buildrun.librarySecurity.repositories.ReturnsRepository;
import library.buildrun.librarySecurity.repositories.UserRepository;

@Service
public class ReturnsService {
	
	private final ReturnsRepository returnsRepository;
	private final UserRepository userRepository;
	private final LoanRepository loanRepository;
	
	public ReturnsService(ReturnsRepository returnsRepository, UserRepository userRepository,
							LoanRepository loanRepository) {
		this.returnsRepository = returnsRepository;
		this.userRepository = userRepository;
		this.loanRepository = loanRepository;
	}
	
	public Returns saveReturns(Returns returns, JwtAuthenticationToken token) {
		var user = userRepository.findById(UUID.fromString(token.getName()));
		Optional<Loan>loan = loanRepository.findById(returns.getLoan().getId()); 
		
		returns.setUserRecive(user.get());
		
		if(loan.isPresent()) {
			Loan existingLoan = loan.get();			
			existingLoan.getBook().setAmount(existingLoan.getBook().getAmount() + 1);;
			
			var date = LocalDate.now();
			String status = "Return";
			existingLoan.setUser(user.get());
			
			if(existingLoan.getLoan_expiration().isBefore(date)) {
				existingLoan.setStatus(status);
				returns.setFine(10000);
				return returnsRepository.save(returns);
			}
			return returnsRepository.save(returns);
		}
		return null;
	}
	
	public List<Returns> getAllReturns(){
		return returnsRepository.findAll();
	}
	
	public void deleteReturns(Long id) {
		if(!returnsRepository.existsById(id)) {
			throw new Error("Returns with ID " + id + " not found");
		}
		returnsRepository.deleteById(id);
	}
	
	public Returns getById(Long id) {
		return returnsRepository.findById(id).orElse(null);
	}
	
	public Returns updateReturns(Long id, Returns returns) {
		Optional<Returns> optionalReturns = returnsRepository.findById(id);
		if(optionalReturns.isPresent()) {
			Returns existingReturns = optionalReturns.get();
			
			existingReturns.setFine(returns.getFine());
			existingReturns.setUserRecive(returns.getUserRecive());
			existingReturns.setLoan(returns.getLoan());
			existingReturns.setReturn_date(returns.getReturn_date());
			
			return returnsRepository.save(existingReturns);
		}
		return null;
	}

}
