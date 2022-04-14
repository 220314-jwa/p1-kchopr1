package com.revature.dao;

import java.util.List;

import com.revature.model.Status;
public interface StatusDao {
	public Status creat(Status st);
	public Status get(int id);
	public List<Status> getAll();
	public int update(Status st,int id);
	public String delete(int id);
}
