package com.revature.services;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeServices {

	public Employee creat(Employee st);
	public Employee get(int id);
	public List<Employee> getAll();
	public int update(Employee st,int id);
	public String delete(int id);
	
}
