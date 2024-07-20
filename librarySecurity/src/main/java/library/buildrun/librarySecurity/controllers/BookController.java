package library.buildrun.librarySecurity.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import library.buildrun.librarySecurity.entities.Book;
import library.buildrun.librarySecurity.services.BookService;


@RestController
@RequestMapping("/library")
@CrossOrigin("*")
public class BookController {
	
	private final BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@PostMapping("/book")
	public Book saveBook(@RequestBody Book book) {
		return bookService.saveBook(book);
	}
	
	@GetMapping("/books")
	public List<Book> getAllBooks(){
		return bookService.getAllBooks();
	}
	
	@DeleteMapping("/book/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable Long id){
		try {
			bookService.deleteBook(id);
			return new ResponseEntity<>("book with ID " + id + "deleted successfully", HttpStatus.OK);
		} catch (Error e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<?> getBookById(@PathVariable Long id){
		Book book = bookService.getById(id);
		if(book == null)return ResponseEntity.notFound().build();
		return ResponseEntity.ok(book);
	}
	
	@PatchMapping("/book/{id}")
	public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody Book book){
		Book updateBook = bookService.updateBook(id, book);
		if(updateBook == null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		return ResponseEntity.ok(updateBook);
	}

}
