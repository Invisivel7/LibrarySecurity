package library.buildrun.librarySecurity.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import library.buildrun.librarySecurity.entities.Gender;
import library.buildrun.librarySecurity.repositories.GenderRepository;

@Service
public class GenderService {
	
private final GenderRepository genderRepository;
	
	public GenderService(GenderRepository genderRepository) {
		this.genderRepository = genderRepository;
	}
	
	public Gender saveGender(Gender gender) {
		return genderRepository.save(gender);
	}
	
	public List<Gender> getAllGender(){
		return genderRepository.findAll();
	}
	
	public void deleteGender(Long id) {
		if(!genderRepository.existsById(id)) {
			throw new Error("Gender with ID " + id + " not found");
		}
		genderRepository.deleteById(id);
	}
	
	public Gender getById(Long id) {
		return genderRepository.findById(id).orElse(null);
	}
	
	public Gender updateGender(Long id, Gender gender) {
		Optional<Gender> optionalGender = genderRepository.findById(id);
		if(optionalGender.isPresent()) {
			Gender existingGender = optionalGender.get();
			
			existingGender.setName(gender.getName());
			
			return genderRepository.save(existingGender);
		}
		return null;
	}

}
