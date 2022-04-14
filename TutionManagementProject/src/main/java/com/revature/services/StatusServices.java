package com.revature.services;

import java.util.List;

import com.revature.model.Status;

public interface StatusServices {

	public Status creat(Status st);
	public Status get(int id);
	public List<Status> getAll();
	public int update(Status st,int id);
	public String delete(int id);
	
}
