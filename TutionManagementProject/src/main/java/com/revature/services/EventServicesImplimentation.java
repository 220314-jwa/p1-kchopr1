package com.revature.services;

import java.util.List;

import com.revature.dao.EventDaoImpli;
import com.revature.model.Event;

public class EventServicesImplimentation implements EventServices {

	private EventDaoImpli dao;
	public EventServicesImplimentation(EventDaoImpli dao) {
		this.dao = dao;
	}

	@Override
	public Event creat(Event st) {
		return dao.creat(st);
	}

	@Override
	public Event get(int id) {
		System.out.println("Service id :"+id);
		return dao.get(id);
	}

	@Override
	public List<Event> getAll() {
		System.out.println("event get all");
		return dao.getAll();
	}

	@Override
	public int update(Event st, int id) {
		return dao.update(st, id);
	}

	@Override
	public String delete(int id) {
		return dao.delete(id);
	}

}
