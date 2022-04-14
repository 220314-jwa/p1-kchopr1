package com.revature.services;

import java.util.List;


import com.revature.dao.DepartmentDaoImpli;
import com.revature.model.Department;

public class DepartmentServicesImplimentation implements DepartmentServices {

	private DepartmentDaoImpli dao;
	public DepartmentServicesImplimentation(DepartmentDaoImpli dao) {
		this.dao = dao;
	}

	@Override
	public Department creat(Department st) {
		return dao.creat(st);
	}

	@Override
	public Department get(int id) {
		System.out.println("Service id :"+id);
		return dao.get(id);
	}

	@Override
	public List<Department> getAll() {
		return dao.getAll();
	}

	@Override
	public int update(Department st, int id) {
		return dao.update(st, id);
	}

	@Override
	public String delete(int id) {
		return dao.delete(id);
	}

}
