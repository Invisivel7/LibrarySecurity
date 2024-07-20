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

import library.buildrun.librarySecurity.entities.Costumer;
import library.buildrun.librarySecurity.services.CostumerService;

@RestController
@RequestMapping("/library")
@CrossOrigin("*")
public class CostumerController {

private final CostumerService costumerService;
	
	public CostumerController(CostumerService costumerService) {
		this.costumerService = costumerService;
	}
	
	@PostMapping("/costumer")
	public Costumer saveUser(@RequestBody Costumer costumer) {
		return costumerService.saveCostumer(costumer);
	}
	
	@GetMapping("/costumers")
	public List<Costumer> getAllCostumers(){
		return costumerService.getAllCostumer();
	}
	
	@DeleteMapping("/costumer/{id}")
	public ResponseEntity<?> deleteCostumer(@PathVariable Long id){
		try {
			costumerService.deleteCostumer(id);
			return new ResponseEntity<>("Costumer with ID " + id + "deleted successfully", HttpStatus.OK);
		} catch (Error e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/costumer/{id}")
	public ResponseEntity<?> getCostumerById(@PathVariable Long id){
		Costumer costumer = costumerService.getById(id);
		if(costumer == null)return ResponseEntity.notFound().build();
		return ResponseEntity.ok(costumer);
	}
	
	@PatchMapping("/costumer/{id}")
	public ResponseEntity<?> updateCostumer(@PathVariable Long id, @RequestBody Costumer costumer){
		Costumer updateCostumer = costumerService.updateCostumer(id, costumer);
		if(updateCostumer == null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		return ResponseEntity.ok(updateCostumer);
	}
}
