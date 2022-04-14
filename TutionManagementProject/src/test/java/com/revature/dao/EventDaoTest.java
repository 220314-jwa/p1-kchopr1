package com.revature.dao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import com.revature.model.Event;


public class EventDaoTest {

	private EventDaoImpli dao;
	
	private Event temp=null;
	public EventDaoTest() {
		dao=new EventDaoImpli();
		temp =new Event();
		temp.setEvent_id(2);
		temp.setEvent_type("EXAM");
	}
	
	
	@Test
	public void create() {
		Event temp1=dao.creat(temp);
		assertEquals("EXAM", temp1.getEvent_type());
	}

	
	@Test
	public void delete() {
		String temp=dao.delete(10);
		assertEquals("Invalid ID", temp);
	}

	@Test
	public void get() {
		Event temp=dao.get(1);
		assertNotEquals(78, temp.getEvent_id());
	}
	
	@Test
	public void getAll() {
		
		List<Event> temp=dao.getAll();
		for (Event tem : temp) {
			System.out.println(tem.toString());
		}
	}
	
}
