package com.convention.app.service;

import com.convention.app.domain.Customer;
import com.convention.app.domain.Registration;

public interface RegistrationService {
	public Iterable<Registration> findAll();
	public Registration findOne(long eventId);
	public Registration addOne (Registration registration);
	public void delete(long eventId);
}
