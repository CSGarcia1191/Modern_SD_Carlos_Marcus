package com.convention.app.api;

import java.net.URI;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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

import com.convention.app.domain.Customer;
import com.convention.app.repository.CustomerRepository;
import com.convention.app.service.CustomerService;


@RestController
@RequestMapping("/customers")
public class CustomerAPI {
	
	@Autowired
	private CustomerService repo;
	
	@GetMapping
	public Iterable<Customer> getAll() {
		return repo.findAll();
	}
	
	@GetMapping("/{customerName}")
	public Customer getCustomerByName(@PathVariable("customerName") String customerName) {
		return repo.findOne(customerName);
	}
	
	@PostMapping
	public ResponseEntity<?> addCustomer(@RequestBody Customer newCustomer, UriComponentsBuilder uri) {
		if (newCustomer.getId() != 0 || newCustomer.getName() == null || newCustomer.getEmail() == null) {
			return ResponseEntity.badRequest().build();
		}
		
		newCustomer = repo.addOne(newCustomer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{newName}").buildAndExpand(newCustomer.getName()).toUri();
		ResponseEntity<?> response = ResponseEntity.created(location).build();
		return response;
	}
	
	@PutMapping("/{newName}")
	public ResponseEntity<?> putCustomer(@RequestBody Customer newCustomer, @PathVariable("newName") String newName) {
		Customer original = repo.findOne(newCustomer.getName());
		
		if (original == null) {
			return ResponseEntity.badRequest().build();
		}
		
		original.setEmail(newCustomer.getEmail());
		original.setPassword(newCustomer.getPassword());
		
		newCustomer = repo.addOne(original);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{customerName}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("customerName") String customerName){
		repo.delete(customerName);
		return ResponseEntity.ok().build();
	}
}
