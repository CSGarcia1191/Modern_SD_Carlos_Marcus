package com.convention.customerresources;

import org.springframework.web.bind.annotation.PathVariable;

import com.convention.customers.CustomerRepository;

@RestController
public class CustomerResource {
	
	@Autowired
	CustomerRepository repo;
	
	public CustomerResource(CustomerRepository repo) {
		this.repo = repo;
	}
	
	
	@RequestMapping("/customers", method=GET)
	public Collection<Customer> getCustomers() {
		return repo.getAll();
	}
	
	
	@RequestMapping("/customers/{id}", method=GET)
	public Customer getCustomer (@PathVariable Integer id) {
		repo.getCustomer(id);
	}
}
