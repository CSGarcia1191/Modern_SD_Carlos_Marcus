package com.convention.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.convention.app.domain.Registration;
import com.convention.app.repository.RegistrationRepository;

@Service
public class RegistrationRepoImpl implements RegistrationService{

	@Autowired
	private RegistrationRepository repo;
	
	@Override
	public Iterable<Registration> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Registration findOne(long eventId) {
		return repo.findById(eventId);
	}

	@Override
	public Registration addOne(Registration registration) {
		// TODO Auto-generated method stub
		return repo.save(registration);
	}

	@Override
	public void delete(long eventId) {
		repo.deleteById(eventId);
		
	}

}
