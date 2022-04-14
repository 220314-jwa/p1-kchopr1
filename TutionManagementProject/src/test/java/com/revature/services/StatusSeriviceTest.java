package com.revature.services;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import com.revature.dao.StatusDaoImpli;
import com.revature.model.Status;

public class StatusSeriviceTest {

	private StatusServicesImplimentation service=new StatusServicesImplimentation(new StatusDaoImpli());
	
	private Status status;
	public StatusSeriviceTest() {
			status =new Status();
			status.setStatus_name("OK");
			status.setStatusid(2);
	}
	
	
	@Test
	public void create() {
		Status temp=service.creat(status);
		assertEquals("OK", temp.getStatus_name());
		assertEquals(2, temp.getStatusid());
	}

	
	@Test
	public void delete() {
		String temp=service.delete(10);
		assertEquals("Invalid ID", temp);
	}

	@Test
	public void get() {
		Status temp=service.get(1);
		assertNotEquals(78, temp.getStatusid());
	}
	
	@Test
	public void getAll() {
		
		List<Status> temp=service.getAll();
		for (Status status : temp) {
			System.out.println(status.toString());
		}
	}
	
}
