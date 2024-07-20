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

import library.buildrun.librarySecurity.entities.Status;
import library.buildrun.librarySecurity.services.StatusService;

@RestController
@RequestMapping("/library")
@CrossOrigin("*")
public class StatusController {
	
private final StatusService statusService;
	
	public StatusController(StatusService statusService) {
		this.statusService = statusService;
	}
	
	@PostMapping("/status")
	public Status saveStatus(@RequestBody Status status) {
		return statusService.saveStatus(status);
	}
	
	@GetMapping("/statuss")
	public List<Status> getAllStatuss(){
		return statusService.getAllStatus();
	}
	
	@DeleteMapping("/status/{id}")
	public ResponseEntity<?> deleteLivro(@PathVariable Long id){
		try {
			statusService.deleteStatus(id);
			return new ResponseEntity<>("status with ID " + id + "deleted successfully", HttpStatus.OK);
		} catch (Error e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/status/{id}")
	public ResponseEntity<?> getLivroById(@PathVariable Long id){
		Status status = statusService.getById(id);
		if(status == null)return ResponseEntity.notFound().build();
		return ResponseEntity.ok(status);
	}
	
	@PatchMapping("/status/{id}")
	public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody Status status){
		Status updateStatus = statusService.updateStatus(id, status);
		if(updateStatus == null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		return ResponseEntity.ok(updateStatus);
	}

}
