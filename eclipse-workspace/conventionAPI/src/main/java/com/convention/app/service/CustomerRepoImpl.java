package com.convention.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.convention.app.domain.Customer;
import com.convention.app.repository.CustomerRepository;

@Service
public class CustomerRepoImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository repo;

	@Override
	public Iterable<Customer> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Customer findOne(String customerName) {
		// TODO Auto-generated method stub
		return repo.findByName(customerName);
	}

	@Override
	public void delete(String customerName) {
		repo.delete(repo.findByName(customerName));
	}

	@Override
	public Customer addOne(Customer customer) {
		return repo.save(customer);
		
	}

	
}
