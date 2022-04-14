package com.revature.services;

import java.util.List;

import com.revature.dao.EmployeeDaoImpli;
import com.revature.model.Employee;

public class EmployeeServicesImplimentation implements EmployeeServices {

	private EmployeeDaoImpli dao;
	public EmployeeServicesImplimentation(EmployeeDaoImpli dao) {
		this.dao = dao;
	}

	@Override
	public Employee creat(Employee st) {
		return dao.creat(st);
	}

	@Override
	public Employee get(int id) {
		System.out.println("Service id :"+id);
		return dao.get(id);
	}

	@Override
	public List<Employee> getAll() {
		return dao.getAll();
	}

	@Override
	public int update(Employee st, int id) {
		return dao.update(st, id);
	}

	@Override
	public String delete(int id) {
		return dao.delete(id);
	}

}
