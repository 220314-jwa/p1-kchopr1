package com.revature.dao;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import com.revature.model.Department;
import com.revature.model.Employee;

public class EmployeeDaoTest {

	private EmployeeDaoImpli dao;
	private Employee temp;
	public EmployeeDaoTest() {
			dao=new EmployeeDaoImpli();
			temp =new Employee();
			Department dep=new Department();
			dep.setDept_name("ACCOUNT");
			dep.setDeptid(2);
			temp.setFirstname("Jhon");
			temp.setLastname("Mathew");
			temp.setPassword("123");
			temp.setRole("MANAGER");
			temp.setUsername("john");
			temp.setDepartment(dep);
	}
	
	
	@Test
	public void create() {
		Employee temp1=dao.creat(temp);
		assertEquals("Jhon", temp1.getFirstname());
	}

	
	@Test
	public void delete() {
		String temp=dao.delete(10);
		assertEquals("Invalid ID", temp);
	}

	@Test
	public void get() {
		Employee temp=dao.get(1);
		assertNotEquals(78, temp.getId());
	}
	
	@Test
	public void getAll() {
		
		List<Employee> temp=dao.getAll();
		for (Employee status : temp) {
			System.out.println(status.toString());
		}
	}
	
}
