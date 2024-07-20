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

import library.buildrun.librarySecurity.entities.Gender;
import library.buildrun.librarySecurity.services.GenderService;

@RestController
@RequestMapping("/library")
@CrossOrigin("*")
public class GenderController {
	
private final GenderService genderService;
	
	public GenderController(GenderService genderService) {
		this.genderService = genderService;
	}
	
	@PostMapping("/gender")
	public Gender saveGender(@RequestBody Gender gender) {
		return genderService.saveGender(gender);
	}
	
	@GetMapping("/genders")
	public List<Gender> getAllGenders(){
		return genderService.getAllGender();
	}
	
	@DeleteMapping("/gender/{id}")
	public ResponseEntity<?> deleteLivro(@PathVariable Long id){
		try {
			genderService.deleteGender(id);
			return new ResponseEntity<>("gender with ID " + id + "deleted successfully", HttpStatus.OK);
		} catch (Error e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/gender/{id}")
	public ResponseEntity<?> getLivroById(@PathVariable Long id){
		Gender gender = genderService.getById(id);
		if(gender == null)return ResponseEntity.notFound().build();
		return ResponseEntity.ok(gender);
	}
	
	@PatchMapping("/gender/{id}")
	public ResponseEntity<?> updateGender(@PathVariable Long id, @RequestBody Gender gender){
		Gender updateGender = genderService.updateGender(id, gender);
		if(updateGender == null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		return ResponseEntity.ok(updateGender);
	}

}
