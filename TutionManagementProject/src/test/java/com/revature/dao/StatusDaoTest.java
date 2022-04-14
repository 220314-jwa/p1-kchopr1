package com.revature.dao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import com.revature.model.Status;

class StatusDaoTest {

	private StatusDaoImpli dao;
	private static Status temp;
	public StatusDaoTest() {
			System.out.println("StatusDaoTest");
			dao =new StatusDaoImpli();
			System.out.println(dao.hashCode());
			temp=new Status();
			temp.setStatus_name("OK");
			temp.setStatusid(2);
	}
	
	@Test
	public void create() {
		System.out.println("crreate");
		StatusDaoImpli dao=new StatusDaoImpli();
		dao.creat(temp);
		assertEquals("OK", temp.getStatus_name());
		assertEquals(2, temp.getStatusid());
		
	}
	
	

	@Test
	public void delete() {
		String temp=dao.delete(10);
		assertEquals("Invalid ID", temp);
	}

	@Test
	public void get() {
		Status temp=dao.get(1);
		assertNotEquals(78, temp.getStatusid());
	}
	
	@Test
	public void getAll() {
		List<Status> temp=dao.getAll();
		for (Status status : temp) {
			System.out.println(status.toString());
		}
	}
	

}
