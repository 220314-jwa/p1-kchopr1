package com.revature.model;



public class Event {
	

	int id;

	private String event_type;

	private int event_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}

	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	@Override
	public String toString() {
		return "Event [event_type=" + event_type + ", event_id=" + event_id + "]";
	}
}
