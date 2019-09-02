package com.convention.app.service;

import com.convention.app.domain.Customer;

public interface CustomerService {
	public Iterable<Customer> findAll();
	public Customer findOne(String customerName);
	public Customer addOne (Customer customer);
	public void delete(String customerName);
}
