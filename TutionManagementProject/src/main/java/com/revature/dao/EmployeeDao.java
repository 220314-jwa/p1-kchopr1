package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;
public interface EmployeeDao {
	public Employee creat(Employee st);
	public Employee get(int id);
	public List<Employee> getAll();
	public int update(Employee st,int id);
	public String delete(int id);
}
