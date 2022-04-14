package com.revature.controller;

import org.jetbrains.annotations.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.dao. DepartmentDaoImpli;
import com.revature.model. Department;
import com.revature.services. DepartmentServicesImplimentation;
import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartmentController implements CrudHandler {
	
	private  DepartmentServicesImplimentation service;
	public DepartmentController( DepartmentServicesImplimentation service) {
		this.service = service;
		if(this.service==null)
			this.service=new  DepartmentServicesImplimentation(new  DepartmentDaoImpli());
	}

	@Override
	public void create(@NotNull Context ctx) {
		 Department student = ctx.bodyAsClass(Department.class);
        System.out.println(student);
        System.out.println("create");
        Department temp=service.creat(student);
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
		 Department temp=service.get(Integer.parseInt(arg1));
		if (temp==null)
			ctx.result("Invalid ID");
		else
		ctx.json(temp);
	}

	@Override
	public void update(@NotNull Context ctx, @NotNull String arg1) {
		 Department student = ctx.bodyAsClass( Department.class);
		 int temp=service.update(student, Integer.parseInt(arg1));
		System.out.println("1");
		System.out.println(temp);
		if (temp==0)
			ctx.result("Invalid INPUT");
		else
		{
			try {
				//ctx.json(temp);
			ctx.result("UPDATED THE DEPARTMENT");
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
