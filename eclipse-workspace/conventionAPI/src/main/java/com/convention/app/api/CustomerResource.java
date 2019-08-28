package com.convention.app.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.convention.app.domain.Customer;
import com.convention.app.domain.CustomerRepository;


@RestController
public class CustomerResource {
	
	@Autowired
	CustomerRepository repo;
	
	public CustomerResource(CustomerRepository repo) {
		this.repo = repo;
	}
	
	
	@GetMapping("/customers")
	public Collection<Customer> getCustomers() {
		return repo.getAll();
	}
	
	
	//@RequestMapping("/customers/{id}", method=GET)
	@GetMapping("/customers/{id}")
	public Customer getCustomer (@PathVariable Integer id) {
		return repo.getCustomer(id);
	}
}
