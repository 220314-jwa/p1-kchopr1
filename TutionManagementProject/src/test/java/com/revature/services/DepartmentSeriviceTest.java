package com.revature.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import com.revature.dao.DepartmentDaoImpli;
import com.revature.model.Department;

public class DepartmentSeriviceTest {

	private DepartmentServicesImplimentation service=new DepartmentServicesImplimentation(new DepartmentDaoImpli());
	
	private  Department temp=null;
	public DepartmentSeriviceTest() {
		temp =new Department();
		temp.setDept_name("ACCOUNT");
		temp.setDeptid(2);
		temp.setDep_head_id(100);
	}
	
	
	@Test
	public void create() {
		Department temp1=service.creat(temp);
		assertEquals("ACCOUNT", temp1.getDept_name());
	}

	
	@Test
	public void delete() {
		String temp=service.delete(10);
		assertEquals("Invalid ID", temp);
	}

	@Test
	public void get() {
		Department temp=service.get(1);
		assertNotEquals(78, temp.getDeptid());
	}
	
	@Test
	public void getAll() {
		
		List<Department> temp=service.getAll();
		for (Department tem : temp) {
			System.out.println(tem.toString());
		}
	}
	
}
