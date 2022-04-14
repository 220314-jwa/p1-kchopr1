package com.revature.dao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import com.revature.model.Department;

public class DepartmentDaoTest {

	DepartmentDaoImpli dao;
	private  Department temp=null;
	public DepartmentDaoTest() {
		dao=new DepartmentDaoImpli();
		temp =new Department();
		temp.setDept_name("ACCOUNT");
		temp.setDeptid(2);
		temp.setDep_head_id(100);
	}
	
	
	@Test
	public void create() {
		Department temp1=dao.creat(temp);
		assertEquals("ACCOUNT", temp1.getDept_name());
	}

	
	@Test
	public void delete() {
		String temp=dao.delete(1000);
		assertEquals("Invalid ID", temp);
	}

	@Test
	public void get() {
		Department temp=dao.get(1);
		assertNotEquals(78, temp.getDeptid());
	}
	
	@Test
	public void getAll() {
		
		List<Department> temp=dao.getAll();
		for (Department tem : temp) {
			System.out.println(tem.toString());
		}
	}
	
}
