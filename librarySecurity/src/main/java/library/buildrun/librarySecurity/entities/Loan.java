package library.buildrun.librarySecurity.entities;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_loan")
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loan_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "livro_id", nullable = false)
	private Book book;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private Costumer client;
	
	@CreationTimestamp
	private Instant loan_begin;
	
	private LocalDate loan_expiration;
	
	//@ManyToOne
	//@JoinColumn(name = "status_id")
	private String status;
	
	@OneToMany(mappedBy = "loan")
	private List<Returns> returns;
	
	public Loan() {
		super();
	}

	public Loan(Long id, Book book, User user, Costumer client, Instant loan_begin, LocalDate loan_expiration,
			String status) {
		super();
		this.id = id;
		this.book = book;
		this.user = user;
		this.client = client;
		this.loan_begin = loan_begin;
		this.loan_expiration = loan_expiration;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Costumer getClient() {
		return client;
	}

	public void setClient(Costumer client) {
		this.client = client;
	}

	public Instant getLoan_begin() {
		return loan_begin;
	}

	public void setLoan_begin(Instant loan_begin) {
		this.loan_begin = loan_begin;
	}

	public LocalDate getLoan_expiration() {
		return loan_expiration;
	}

	public void setLoan_expiration(LocalDate loan_expiration) {
		this.loan_expiration = loan_expiration;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
		
}
