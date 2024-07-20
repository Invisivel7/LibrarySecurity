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

import library.buildrun.librarySecurity.entities.Author;
import library.buildrun.librarySecurity.services.AuthorService;

@RestController
@RequestMapping("/library")
@CrossOrigin("*")
public class AuthorController {
	

	private AuthorService authorService;
	
	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}
	
	@PostMapping("/author")
	public Author saveAuthor(@RequestBody Author author) {
		return authorService.saveAuthor(author);
	}
	
	@GetMapping("/authors")
	public List<Author> getAllAuthors(){
		return authorService.getAllAuthor();
	}
	
	@DeleteMapping("/author/{id}")
	public ResponseEntity<?> deleteAuthor(@PathVariable Long id){
		try {
			authorService.deleteAuthor(id);
			return new ResponseEntity<>("Author with ID " + id + "deleted successfully", HttpStatus.OK);
		} catch (Error e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/author/{id}")
	public ResponseEntity<?> getAuthorById(@PathVariable Long id){
		Author author = authorService.getById(id);
		if(author == null)return ResponseEntity.notFound().build();
		return ResponseEntity.ok(author);
	}
	
	@PatchMapping("/author/{id}")
	public ResponseEntity<?> updateAuthor(@PathVariable Long id, @RequestBody Author author){
		Author updateAuthor = authorService.updateAuthor(id, author);
		if(updateAuthor == null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		return ResponseEntity.ok(updateAuthor);
	}

}
