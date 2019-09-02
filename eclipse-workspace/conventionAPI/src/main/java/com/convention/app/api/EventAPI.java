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

import com.convention.app.domain.Event;
import com.convention.app.service.EventService;

@RestController
@RequestMapping("/events")
public class EventAPI {
	
	@Autowired
	private EventService repo;
	
	@GetMapping
	public Iterable<Event> getAll() {
		return repo.findAll();
	}
	
	@GetMapping("/{eventName}")
	public Event getCustomerByName(@PathVariable("eventName") String eventName) {
		return repo.findOne(eventName);
	}
	
	@PostMapping
	public ResponseEntity<?> addEvent(@RequestBody Event newEvent, UriComponentsBuilder uri) {
		if (newEvent.getId() != 0 || newEvent.getName() == null || newEvent.getDate() == null) {
			return ResponseEntity.badRequest().build();
		}
		
		newEvent = repo.addOne(newEvent);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{newName}").buildAndExpand(newEvent.getName()).toUri();
		ResponseEntity<?> response = ResponseEntity.created(location).build();
		return response;
	}
	
	@PutMapping("/{newName}")
	public ResponseEntity<?> putEvent(@RequestBody Event newEvent, @PathVariable("newName") String newName) {
		Event original = repo.findOne(newEvent.getName());
		
		if (original == null) {
			return ResponseEntity.badRequest().build();
		}
		
		original.setCustomerId(newEvent.getCustomerId());
		original.setName(newEvent.getName());
		original.setDate(newEvent.getDate());
		
		newEvent = repo.addOne(original);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{eventName}")
	public ResponseEntity<?> delete(@PathVariable("eventName") String eventName){
		repo.delete(eventName);
		return ResponseEntity.ok().build();
	}
}
