package com.convention.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.convention.app.domain.Customer;
import com.convention.app.domain.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

	Event findByName(String eventName);
}
