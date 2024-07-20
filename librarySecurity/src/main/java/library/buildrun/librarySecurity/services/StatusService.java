package library.buildrun.librarySecurity.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import library.buildrun.librarySecurity.entities.Status;
import library.buildrun.librarySecurity.repositories.StatusRepository;

@Service
public class StatusService {
	
private final StatusRepository statusRepository;
	
	public StatusService(StatusRepository statusRepository) {
		this.statusRepository = statusRepository;
	}
	
	public Status saveStatus(Status status) {
		return statusRepository.save(status);
	}
	
	public List<Status> getAllStatus(){
		return statusRepository.findAll();
	}
	
	public void deleteStatus(Long id) {
		if(!statusRepository.existsById(id)) {
			throw new Error("Status with ID " + id + " not found");
		}
		statusRepository.deleteById(id);
	}
	
	public Status getById(Long id) {
		return statusRepository.findById(id).orElse(null);
	}
	
	public Status updateStatus(Long id, Status status) {
		Optional<Status> optionalStatus = statusRepository.findById(id);
		if(optionalStatus.isPresent()) {
			Status existingStatus = optionalStatus.get();
			
			existingStatus.setStatus(status.getStatus());
			
			return statusRepository.save(existingStatus);
		}
		return null;
	}

}
