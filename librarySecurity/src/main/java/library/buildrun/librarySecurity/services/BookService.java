package library.buildrun.librarySecurity.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import library.buildrun.librarySecurity.entities.Book;
import library.buildrun.librarySecurity.repositories.BookRepository;

@Service
public class BookService {
	
	private final BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}
	
	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	}
	
	public void deleteBook(Long id) {
		if(!bookRepository.existsById(id)) {
			throw new Error("Livro with ID " + id + " not found");
		}
		bookRepository.deleteById(id);
	}
	
	public Book getById(Long id) {
		return bookRepository.findById(id).orElse(null);
	}
	
	public Book updateBook(Long id, Book book) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if(optionalBook.isPresent()) {
			Book existingBook = optionalBook.get();
			
			existingBook.setTitle(book.getTitle());
			existingBook.setAuthor(book.getAuthor());
			existingBook.setGender(book.getGender());
			existingBook.setPage_number(book.getPage_number());
			existingBook.setPublish_date(book.getPublish_date());
			existingBook.setEditora(book.getEditora());
			
			return bookRepository.save(existingBook);
		}
		return null;
	}

}
