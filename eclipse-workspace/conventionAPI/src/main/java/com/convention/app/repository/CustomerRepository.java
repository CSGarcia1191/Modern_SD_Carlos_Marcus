package com.convention.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.convention.app.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	
	public Customer findByName(String customerName);
	
}
