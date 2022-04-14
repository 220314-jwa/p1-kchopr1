package com.revature.services;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import com.revature.dao.EmployeeDaoImpli;
import com.revature.dao.StatusDaoImpli;
import com.revature.model.Department;
import com.revature.model.Employee;
import com.revature.model.Status;

public class EmployeeSeriviceTest {

	private EmployeeServicesImplimentation service=new EmployeeServicesImplimentation(new EmployeeDaoImpli());
	
	private Employee temp;
	public EmployeeSeriviceTest() {
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
		Employee temp1=service.creat(temp);
		assertEquals("Jhon", temp1.getFirstname());
	}

	
	@Test
	public void delete() {
		String temp=service.delete(10);
		assertEquals("Invalid ID", temp);
	}

	@Test
	public void get() {
		Employee temp=service.get(1);
		assertNotEquals(78, temp.getId());
	}
	
	@Test
	public void getAll() {
		
		List<Employee> temp=service.getAll();
		for (Employee status : temp) {
			System.out.println(status.toString());
		}
	}
	
}
