package com.convention.app.service;

import com.convention.app.domain.Event;

public interface EventService {
	public Iterable<Event> findAll();
	public Event findOne(String eventName);
	public Event addOne (Event event);
	public void delete(String eventName);
}
