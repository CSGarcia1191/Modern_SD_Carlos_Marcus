package com.convention.app.api;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.convention.app.domain.Registration;
import com.convention.app.service.RegistrationService;

@RestController
@RequestMapping("/registrations")
public class RegistrationAPI {
	@Autowired
	private RegistrationService repo;
	
	@GetMapping
	public Iterable<Registration> getAll() {
		return repo.findAll();
	}
	
	@GetMapping("/{regId}")
	public Registration getCustomerByName(@PathVariable("regId") long regId) {
		return repo.findOne(regId);
	}
	
	@PostMapping
	public ResponseEntity<?> addEvent(@RequestBody Registration newReg, UriComponentsBuilder uri) {
		if (newReg.getId() != 0 || newReg.getCustomerId() == 0 || newReg.getEventId() == 0) {
			return ResponseEntity.badRequest().build();
		}
		
		newReg = repo.addOne(newReg);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{regId}").buildAndExpand(newReg.getId()).toUri();
		ResponseEntity<?> response = ResponseEntity.created(location).build();
		return response;
	}
	
	@PutMapping("/{regId}")
	public ResponseEntity<?> putEvent(@RequestBody Registration newReg, @PathVariable("regId") long regId) {
		Registration original = repo.findOne(newReg.getId());
		
		if (original == null) {
			return ResponseEntity.badRequest().build();
		}
		
		original.setCustomerId(newReg.getCustomerId());
		original.setEventId(newReg.getEventId());
		
		newReg = repo.addOne(original);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{regId}")
	public ResponseEntity<?> delete(@PathVariable("regId") long regId){
		repo.delete(regId);
		return ResponseEntity.ok().build();
	}
}
