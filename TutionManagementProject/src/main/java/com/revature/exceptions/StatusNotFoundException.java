package com.revature.exceptions;

public class StatusNotFoundException extends RuntimeException{

	private String message;

	
	public StatusNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
