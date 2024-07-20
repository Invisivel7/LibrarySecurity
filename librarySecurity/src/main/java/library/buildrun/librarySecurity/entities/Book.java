package library.buildrun.librarySecurity.entities;

import java.time.LocalDate;
import java.util.List;

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
@Table(name = "tb_book")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long id;
	
	private String title;
	
	//private String resume;
	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private Author author;
	
	private Integer page_number;
	
	@ManyToOne
	@JoinColumn(name = "gender_id", nullable = false)
	private Gender gender;
	
	private LocalDate publish_date;
	
	private String editora;
	
	@OneToMany(mappedBy = "book")
	private List<Loan> loans;
	
	private Long amount;
	
	public Book() {
		super();
	}

	public Book(Long id, String title, Author author, Integer page_number, Gender gender, LocalDate publish_date,
			String editora, List<Loan> loans, Long amount) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.page_number = page_number;
		this.gender = gender;
		this.publish_date = publish_date;
		this.editora = editora;
		this.loans = loans;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Integer getPage_number() {
		return page_number;
	}

	public void setPage_number(Integer page_number) {
		this.page_number = page_number;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getPublish_date() {
		return publish_date;
	}

	public void setPublish_date(LocalDate publish_date) {
		this.publish_date = publish_date;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
}
