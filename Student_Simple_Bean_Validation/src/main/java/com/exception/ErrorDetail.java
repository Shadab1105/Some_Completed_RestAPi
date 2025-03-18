package com.exception;

import java.util.Date;

public class ErrorDetail 
{
	private Date timeStamp;
	private String message;
	private String details;
	
	
	public Date getTimeStamp() {
		return timeStamp;
	}

	public String getMessage() {
		return message;
	}
	
	public String getDetails() {
		return details;
	}

	public ErrorDetail(Date timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}
	@Override
	public String toString() {
		return "ErrorDetail [timeStamp=" + timeStamp + ", message=" + message + ", details=" + details + "]";
	}

	
	
}
