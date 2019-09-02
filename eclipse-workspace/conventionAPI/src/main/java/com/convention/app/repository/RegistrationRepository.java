package com.convention.app.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.convention.app.domain.Registration;

public interface RegistrationRepository extends CrudRepository<Registration, Long> {

	Registration findById(long id);


}
