package com.revature.model;


public class Department {


	private int id;

	private int deptid;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String dept_name;

	private int dep_head_id;

	// setting getter and setter method
	public int getDeptid() {
		return deptid;
	}

	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public int getDep_head_id() {
		return dep_head_id;
	}

	public void setDep_head_id(int dep_head_id) {
		this.dep_head_id = dep_head_id;
	}

	// overriding tostring, hashcode, and equals
	@Override
	public String toString() {
		return "Department [deptid=" + deptid + ", dept_name=" + dept_name + ", dep_head_id=" + dep_head_id + "]";
	}



}
