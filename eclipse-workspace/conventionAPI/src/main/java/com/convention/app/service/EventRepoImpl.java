package com.convention.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.convention.app.domain.Event;
import com.convention.app.repository.EventRepository;

@Service
public class EventRepoImpl implements EventService{

	@Autowired
	private EventRepository repo;
	
	@Override
	public Iterable<Event> findAll() {
		return repo.findAll();
	}

	@Override
	public Event findOne(String eventName) {
		return repo.findByName(eventName);
	}

	@Override
	public Event addOne(Event event) {
		return repo.save(event);
		
	}

	@Override
	public void delete(String eventName) {
		repo.delete(repo.findByName(eventName));
	}

}
