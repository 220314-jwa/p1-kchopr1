package com.revature.dao;

import java.util.List;

import com.revature.model.Department;
public interface DepartmentDao {
	public Department creat(Department st);
	public Department get(int id);
	public List<Department> getAll();
	public int update(Department st,int id);
	public String delete(int id);
}
