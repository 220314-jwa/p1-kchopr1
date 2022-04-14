package com.revature.dao;

import java.util.List;

import com.revature.model.Reimbursement;

public interface ReimbursementDao {
	public Reimbursement creat(Reimbursement st) throws InterruptedException;
	public Reimbursement get(int id) throws InterruptedException;
	public List<Reimbursement> getAll() throws InterruptedException;
	public int update(Reimbursement st,int id) throws InterruptedException;
	public String delete(int id) throws InterruptedException;
}
