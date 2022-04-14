package com.revature.exceptions;

public class InvalidLogin extends RuntimeException{
	
	private String message;
	private int statusCode;

	public InvalidLogin(String message, Throwable cause) {
		super(message, cause);
	}
	public InvalidLogin(String message, int statusCode) {
		System.out.println("InvalidLogin Constructor");
		this.message = message;
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	
	

}
