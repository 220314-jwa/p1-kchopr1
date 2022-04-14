package com.revature.services;

import java.util.List;

import com.revature.dao.EventDaoImpli;
import com.revature.dao.StatusDaoImpli;
import com.revature.model.Status;

public class StatusServicesImplimentation implements StatusServices {

	private StatusDaoImpli dao;
	public StatusServicesImplimentation(StatusDaoImpli dao) {
		this.dao = dao;
	}

	@Override
	public Status creat(Status st) {
		return dao.creat(st);
	}

	@Override
	public Status get(int id) {
		System.out.println("Service id :"+id);
		return dao.get(id);
	}

	@Override
	public List<Status> getAll() {
		return dao.getAll();
	}

	@Override
	public int update(Status st, int id) {
		return dao.update(st, id);
	}

	@Override
	public String delete(int id) {
		return dao.delete(id);
	}

}
