package com.revature.services;

import java.util.List;

import com.revature.model.Event;

public interface EventServices {

	public Event creat(Event st);
	public Event get(int id);
	public List<Event> getAll();
	public int update(Event st,int id);
	public String delete(int id);
	
}
