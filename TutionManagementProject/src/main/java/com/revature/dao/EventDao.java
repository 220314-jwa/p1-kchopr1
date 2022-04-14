package com.revature.dao;

import java.util.List;

import com.revature.model.Event;
public interface EventDao {
	public Event creat(Event st);
	public Event get(int id);
	public List<Event> getAll();
	public int update(Event st,int id);
	public String delete(int id);
}
