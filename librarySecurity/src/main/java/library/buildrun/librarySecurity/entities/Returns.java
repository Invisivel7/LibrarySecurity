package library.buildrun.librarySecurity.entities;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_return")
public class Returns {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "return_id")
	private Long returnId;
	
	@ManyToOne
	@JoinColumn(name = "user_recive_id", nullable = false)
	private User userRecive;
	
	@ManyToOne
	@JoinColumn(name = "loan_id", nullable = false)
	private Loan loan;
	
	@Column(name = "fine")
	@Value("${fine.float: 0.00}")
	private float fine;
	
	@CreationTimestamp
	private Instant return_date;

	
	public Returns() {
		super();
	}

	public Returns(Long returnId, User userRecive, Loan loan, float fine, Instant return_date) {
		super();
		this.returnId = returnId;
		this.userRecive = userRecive;
		this.loan = loan;
		this.fine = fine;
		this.return_date = return_date;
	}

	public Long getReturnId() {
		return returnId;
	}

	public void setReturnId(Long returnId) {
		this.returnId = returnId;
	}

	public User getUserRecive() {
		return userRecive;
	}

	public void setUserRecive(User userRecive) {
		this.userRecive = userRecive;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	public float getFine() {
		return fine;
	}

	public void setFine(float fine) {
		this.fine = fine;
	}

	public Instant getReturn_date() {
		return return_date;
	}

	public void setReturn_date(Instant return_date) {
		this.return_date = return_date;
	}

}
