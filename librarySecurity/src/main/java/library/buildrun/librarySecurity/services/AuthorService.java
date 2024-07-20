package library.buildrun.librarySecurity.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import library.buildrun.librarySecurity.entities.Author;
import library.buildrun.librarySecurity.repositories.AuthorRepository;

@Service
public class AuthorService {
	
private final AuthorRepository authorRepository;
	
	public AuthorService(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}
	
	public Author saveAuthor(Author author) {
		Optional<Author> authorOptional = authorRepository.findAuthorByEmail(author.getEmail());
		if(authorOptional.isPresent()) {
			throw new IllegalStateException("Email taken");
		}
		return authorRepository.save(author);
	}
	
	public List<Author> getAllAuthor(){
		return authorRepository.findAll();
	}
	
	public void deleteAuthor(Long id) {
		if(!authorRepository.existsById(id)) {
			throw new Error("Author with ID " + id + " not found");
		}
		authorRepository.deleteById(id);
	}
	
	public Author getById(Long id) {
		return authorRepository.findById(id).orElse(null);
	}
	
	public Author updateAuthor(Long id, Author author) {
		Optional<Author> optionalAuthor = authorRepository.findById(id);
		if(optionalAuthor.isPresent()) {
			Author existingAuthor = optionalAuthor.get();
			
			if(author.getName() != null && author.getName().length() > 0 && !Objects.equals(existingAuthor.getName(), author.getName())) {
				existingAuthor.setName(author.getName());
			}
			if(author.getEmail() != null && author.getEmail().length() > 0 && !Objects.equals(existingAuthor.getEmail(), author.getEmail())) {
				Optional<Author> userOptional = authorRepository.findAuthorByEmail(author.getEmail());
				if(userOptional.isPresent()) {
					throw new IllegalStateException("email taken");
				}
				existingAuthor.setEmail(author.getEmail());
			}
			existingAuthor.setLast_name(author.getLast_name());
			existingAuthor.setPhone(author.getPhone());
			
			return authorRepository.save(existingAuthor);
		}
		return null;
	}

}
