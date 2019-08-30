package com.convention.app.api;

import java.net.URI;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
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


@RestController
@RequestMapping("/customers")
public class CustomerAPI {
	
	@Autowired
	CustomerRepository repo;
	
	@GetMapping
	public Iterable<Customer> getAll() {
		return repo.findAll();
	}
	
	@GetMapping("/{customerName}")
	public Customer getCustomerByName(@PathVariable("customerName") String customerName) {
		return getByNameHelper(customerName);
	}
	
	@PostMapping
	public ResponseEntity<?> addCustomer(@RequestBody Customer newCustomer, UriComponentsBuilder uri) {
		if (newCustomer.getId() != 0 || newCustomer.getName() == null || newCustomer.getEmail() == null) {
			return ResponseEntity.badRequest().build();
		}
		
		newCustomer = repo.save(newCustomer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{newName}").buildAndExpand(newCustomer.getName()).toUri();
		ResponseEntity<?> response = ResponseEntity.created(location).build();
		return response;
	}
	
	@PutMapping("/{newName}")
	public ResponseEntity<?> putCustomer(@RequestBody Customer newCustomer, @PathVariable("newName") String newName) {
		if (!newCustomer.getName().equals(newName) || newCustomer.getName() == null || newCustomer.getEmail() == null) {
			return ResponseEntity.badRequest().build();
		}
		
		Customer c = getByNameHelper(newName);
		c.setEmail(newCustomer.getEmail());
		c.setPassword(newCustomer.getPassword());
		
		newCustomer = repo.save(c);
		return ResponseEntity.ok().build();
	}
	
	public Customer getByNameHelper(String n) {
		Iterable<Customer> result = repo.findAll();
		Iterator<Customer> iter = result.iterator();
		Customer c = null;
		
		while(iter.hasNext()) {
			c = iter.next();
			
			if (c.getName().equals(n)) {
				break;
			}
		}
		
		return c;
	}
	
	
	/*
	 * public CustomerAPI(CustomerRepository repo) { this.repo = repo; }
	 * 
	 * 
	 * @GetMapping public Collection<Customer> getCustomers() { return
	 * repo.getAll(); }
	 * 
	 * @PostMapping("/add-customer/{customer}") public void
	 * addCustomer(@PathVariable Customer customer) {
	 * repo.addCustomer(customer.getName(), customer.getEmail(),
	 * customer.getPassword()); }
	 */
}
