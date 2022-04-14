package com.revature.model;



import java.sql.Date;


import com.fasterxml.jackson.annotation.JsonFormat;


public class Reimbursement {


	private int id;

	private Employee employee;

	private Event event;

	private Status status;
	private int cost;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date event_date;
	private String description;
	private String location;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date submited_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public Date getEvent_date() {
		return event_date;
	}
	public void setEvent_date(Date event_date) {
		this.event_date = event_date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getSubmited_date() {
		return submited_date;
	}
	public void setSubmited_date(Date submited_date) {
		System.out.println("Date "+submited_date);
		this.submited_date = submited_date;
	}
	
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", employee=" + employee + ", event=" + event + ", status=" + status
				+ ", cost=" + cost + ", event_date=" + event_date + ", description=" + description + ", location="
				+ location + ", submited_date=" + submited_date + "]";
	}
	
	
	
	
	
}
