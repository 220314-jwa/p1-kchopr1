package com.revature.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import com.revature.dao.EventDaoImpli;
import com.revature.model.Event;


public class EventSeriviceTest {

	private EventServicesImplimentation service=new EventServicesImplimentation(new EventDaoImpli());
	
	private Event temp=null;
	public EventSeriviceTest() {
		temp =new Event();
		temp.setEvent_id(2);
		temp.setEvent_type("EXAM");
	}
	
	
	@Test
	public void create() {
		Event temp1=service.creat(temp);
		assertEquals("EXAM", temp1.getEvent_type());
	}

	
	@Test
	public void delete() {
		String temp=service.delete(10);
		assertEquals("Invalid ID", temp);
	}

	@Test
	public void get() {
		Event temp=service.get(1);
		assertNotEquals(78, temp.getEvent_id());
	}
	
	@Test
	public void getAll() {
		
		List<Event> temp=service.getAll();
		for (Event tem : temp) {
			System.out.println(tem.toString());
		}
	}
	
}
