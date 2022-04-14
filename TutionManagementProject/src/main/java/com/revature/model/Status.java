package com.revature.model;
public class Status {


	private int id;

	private int statusid;

	private String status_name;
	
	public Status() {
	}

	public Status(int statusid, String status_name) {
		super();
		this.statusid = statusid;
		this.status_name = status_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatusid() {
		return statusid;
	}

	public void setStatusid(int statusid) {
		this.statusid = statusid;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

	@Override
	public String toString() {
		return "Status [id=" + id + ", statusid=" + statusid + ", status_name=" + status_name + "]";
	}


	
}
