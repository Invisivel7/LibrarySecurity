package library.buildrun.librarySecurity.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import library.buildrun.librarySecurity.entities.Costumer;
import library.buildrun.librarySecurity.repositories.CostumerRepository;

@Service
public class CostumerService {
	
private final CostumerRepository costumerRepository;
	
	public CostumerService(CostumerRepository costumerRepository) {
		this.costumerRepository = costumerRepository;
	}
	
	public Costumer saveCostumer(Costumer costumer) {
		Optional<Costumer> costumerOptional = costumerRepository.findCostumerByEmail(costumer.getEmail());
		if(costumerOptional.isPresent()) {
			throw new IllegalStateException("Email taken");
		}
		return costumerRepository.save(costumer);
	}
	
	public List<Costumer> getAllCostumer(){
		return costumerRepository.findAll();
	}
	
	public void deleteCostumer(Long id) {
		if(!costumerRepository.existsById(id)) {
			throw new Error("Costumer with ID " + id + " not found");
		}
		costumerRepository.deleteById(id);
	}
	
	public Costumer getById(Long id) {
		return costumerRepository.findById(id).orElse(null);
	}
	
	public Costumer updateCostumer(Long id, Costumer costumer) {
		Optional<Costumer> optionalCostumer = costumerRepository.findById(id);
		if(optionalCostumer.isPresent()) {
			Costumer existingCostumer = optionalCostumer.get();
			
			if(costumer.getName() != null && costumer.getName().length() > 0 && !Objects.equals(existingCostumer.getName(), costumer.getName())) {
				existingCostumer.setName(costumer.getName());
			}
			if(costumer.getEmail() != null && costumer.getEmail().length() > 0 && !Objects.equals(existingCostumer.getEmail(), costumer.getEmail())) {
				Optional<Costumer> costumerOptional = costumerRepository.findCostumerByEmail(costumer.getEmail());
				if(costumerOptional.isPresent()) {
					throw new IllegalStateException("email taken");
				}
				existingCostumer.setEmail(costumer.getEmail());
			}
			existingCostumer.setLast_name(costumer.getLast_name());
			existingCostumer.setPhone(costumer.getPhone());
			
			return costumerRepository.save(existingCostumer);
		}
		return null;
	}

}
