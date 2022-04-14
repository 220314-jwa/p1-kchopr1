package com.revature.controller;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.dao.EventDaoImpli;
import com.revature.dao.ReimbursementDaoImpli;
import com.revature.model.Reimbursement;
import com.revature.services.ReimbursementServicesImplimentation;

import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReimbursementController implements CrudHandler {

	
	private ReimbursementServicesImplimentation service;

	public ReimbursementController(ReimbursementServicesImplimentation service) {
		System.out.println("reimbursement controller constructor");
		this.service = service;
		if(this.service==null)
			this.service=new ReimbursementServicesImplimentation(new ReimbursementDaoImpli());
	}

	@Override
	public void create(@NotNull Context ctx) {
		Reimbursement student = ctx.bodyAsClass(Reimbursement.class);
        System.out.println(student);
        System.out.println("create");
        Reimbursement temp = null;
		try {
			temp = service.creat(student);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(temp==null)
        	ctx.result("Invalid Input");
        else
        ctx.json(temp);
	}

	@Override
	public void delete(@NotNull Context ctx,@NotNull String arg1) {
		String result = null;
		try {
			result = service.delete(Integer.parseInt(arg1));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ctx.result(result);
	}

	@Override
	public void getAll(@NotNull Context ctx) {
		System.out.println("reimbursement controller getAll");
		List<Reimbursement> all = null;
		try {
			all = service.getAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for (Reimbursement reimbursement : all) {
//			System.out.println(reimbursement.getEvent_date());
//			System.out.println(reimbursement.getSubmited_date());
//		}
		ctx.json(all);
	}

	@Override
	public void getOne(@NotNull Context ctx,@NotNull String arg1) {
		System.out.println("Controller ID: "+Integer.parseInt(arg1));
		Reimbursement temp = null;
		try {
			temp = service.get(Integer.parseInt(arg1));
		} catch (NumberFormatException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (temp==null)
			ctx.result("Invalid ID");
		else
		ctx.json(temp);
	}

	@Override
	public void update(@NotNull Context ctx, @NotNull String arg1) {
		Reimbursement student = ctx.bodyAsClass(Reimbursement.class);
		int temp = 0;
		try {
			temp = service.update(student, Integer.parseInt(arg1));
		} catch (NumberFormatException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("1");
		System.out.println(temp);
		ctx.json(temp);
		if (temp==0)
			ctx.result("Invalid INPUT");
		else
		{
			try {
				//ctx.json(temp);
				ctx.result("UPDATED THE USER");
			System.out.println("controller temp");
			System.out.println(temp);
			
			}catch (Exception e) {
				System.out.println(temp);
				e.printStackTrace();
				ctx.result("SERVER ERROR");
			}
			
		}
	}

}
