package com.revature.services;

import java.util.List;

import com.revature.dao.ReimbursementDaoImpli;
import com.revature.model.Reimbursement;

public class ReimbursementServicesImplimentation implements ReimbursementServices {

	private ReimbursementDaoImpli dao;
	public ReimbursementServicesImplimentation(ReimbursementDaoImpli dao) {
		this.dao = dao;
	}

	@Override
	public Reimbursement creat(Reimbursement st) throws InterruptedException {
		return dao.creat(st);
	}

	@Override
	public Reimbursement get(int id) throws InterruptedException {
		System.out.println("Service id :"+id);
		return dao.get(id);
	}

	@Override
	public List<Reimbursement> getAll() throws InterruptedException {
		return dao.getAll();
	}

	@Override
	public int update(Reimbursement st, int id) throws InterruptedException {
		return dao.update(st, id);
	}

	@Override
	public String delete(int id) throws InterruptedException {
		return dao.delete(id);
	}

}
