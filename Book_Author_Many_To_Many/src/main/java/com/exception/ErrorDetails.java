package com.exception;

import java.util.Date;

public class ErrorDetails 
{
	private Date date;
	private String details;
	private String description;
	public Date getDate() {
		return date;
	}
	
	public String getDetails() {
		return details;
	}

	public String getDescription() {
		return description;
	}

	public ErrorDetails(Date date, String details, String description) {
		super();
		this.date = date;
		this.details = details;
		this.description = description;
	}
	
	
	
	
	

}
