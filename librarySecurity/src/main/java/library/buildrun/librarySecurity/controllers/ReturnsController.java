package library.buildrun.librarySecurity.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import library.buildrun.librarySecurity.entities.Returns;
import library.buildrun.librarySecurity.services.ReturnsService;

@RestController
@RequestMapping("/library")
@CrossOrigin("*")
public class ReturnsController {
	
	private final ReturnsService returnsService;
	
	public ReturnsController(ReturnsService returnsService) {
		this.returnsService = returnsService;
	}

	@PostMapping("/to_returns")
	public Returns saveReturns(@RequestBody Returns returns, JwtAuthenticationToken token) {
		return returnsService.saveReturns(returns, token);
	}
	
	@GetMapping("/returnss")
	public List<Returns> getAllReturns(){
		return returnsService.getAllReturns();
	}
	
	@DeleteMapping("/to_returns/{id}")
	public ResponseEntity<?> deleteReturns(@PathVariable Long id){
		try {
			returnsService.deleteReturns(id);
			return new ResponseEntity<>("Returns with ID " + id + "deleted successfully", HttpStatus.OK);
		} catch (Error e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/to_returns/{id}")
	public ResponseEntity<?> getReturnsById(@PathVariable Long id){
		Returns returns = returnsService.getById(id);
		if(returns == null)return ResponseEntity.notFound().build();
		return ResponseEntity.ok(returns);
	}
	
	@PatchMapping("/to_returns/{id}")
	public ResponseEntity<?> updateReturns(@PathVariable Long id, @RequestBody Returns returns){
		Returns updateReturns = returnsService.updateReturns(id, returns);
		if(updateReturns == null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		return ResponseEntity.ok(updateReturns);
	}

}
