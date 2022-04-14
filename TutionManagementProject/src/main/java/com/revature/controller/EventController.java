package com.revature.controller;

import org.jetbrains.annotations.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.dao.EventDaoImpli;
import com.revature.model.Event;
import com.revature.services.EventServicesImplimentation;
import com.revature.services.StatusServicesImplimentation;

import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventController implements CrudHandler {

	
	private EventServicesImplimentation service;

	public EventController(EventServicesImplimentation service) {
		this.service = service;
		if(this.service==null)
			this.service=new EventServicesImplimentation(new EventDaoImpli());
	}

	@Override
	public void create(@NotNull Context ctx) {
		Event temp = ctx.bodyAsClass(Event.class);
        System.out.println(temp);
        System.out.println("create");
        Event temp1=service.creat(temp);
        if(temp==null)
        	ctx.result("Invalid Input");
        else
        ctx.json(temp1);
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
		Event temp=service.get(Integer.parseInt(arg1));
		if (temp==null)
			ctx.result("Invalid ID");
		else
		ctx.json(temp);
	}

	@Override
	public void update(@NotNull Context ctx, @NotNull String arg1) {
		Event student = ctx.bodyAsClass(Event.class);
		int temp=service.update(student, Integer.parseInt(arg1));
		System.out.println("1");
		System.out.println(temp);
		if (temp==0)
			ctx.result("Invalid INPUT");
		else
		{
			try {
				//ctx.json(temp);
			ctx.result("UPDATED THE EVENT");
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
