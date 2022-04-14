package com.revature.controller;

import org.jetbrains.annotations.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.dao.EmployeeDaoImpli;
import com.revature.model.Employee;
import com.revature.services.EmployeeServicesImplimentation;
import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeController implements CrudHandler {

	
	private EmployeeServicesImplimentation service;

	public EmployeeController(EmployeeServicesImplimentation service) {
		this.service = service;
		if(this.service==null)
			this.service=new EmployeeServicesImplimentation(new EmployeeDaoImpli());
	}

	@Override
	public void create(@NotNull Context ctx) {
		Employee student = ctx.bodyAsClass(Employee.class);
        System.out.println(student);
        System.out.println("create");
        Employee temp=service.creat(student);
        if(temp==null)
        	ctx.result("Invalid Input");
        else
        ctx.json(temp);
	}

	@Override
	public void delete(@NotNull Context ctx,@NotNull String arg1) {
		String result=service.delete(Integer.parseInt(arg1));
		ctx.result(result);
	}

	@Override
	public void getAll(@NotNull Context ctx) {
		ctx.json(service.getAll());
	}

	@Override
	public void getOne(@NotNull Context ctx,@NotNull String arg1) {
		System.out.println("Controller ID: "+Integer.parseInt(arg1));
		Employee temp=service.get(Integer.parseInt(arg1));
		if (temp==null)
			ctx.result("Invalid ID");
		else
		ctx.json(temp);
	}

	@Override
	public void update(@NotNull Context ctx, @NotNull String arg1) {
		Employee student = ctx.bodyAsClass(Employee.class);
		int temp=service.update(student, Integer.parseInt(arg1));
		System.out.println("1");
		System.out.println(temp);
		if (temp==0)
			ctx.result("Invalid INPUT");
		else
		{
			try {
				//ctx.json(temp);
			ctx.result("UPDATED THE EMPLOYEE");
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
