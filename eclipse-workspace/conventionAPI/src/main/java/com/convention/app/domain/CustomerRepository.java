
package com.convention.app.domain;

import java.util.*;

import org.springframework.stereotype.Component;


@Component
public class CustomerRepository {
	private List<Customer> customers = new ArrayList<>(Arrays.asList(
			new Customer("Joe","joe.blow@email.com","pass"),
			new Customer("kevin","kevin.hart@hart.com","pass"),
			new Customer("George","george@george.coms","pass")
			
	));
	
	public Collection<Customer> getAll(){
		return Collections.unmodifiableCollection(customers);
	}
	
	public Customer getCustomer(Integer id) {
		return (Customer) customers.stream().filter(customer -> customer.getId() == id);
	}
}
