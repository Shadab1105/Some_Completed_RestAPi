package com.exception;

import java.util.Date;

public class ErrorDetails 
{
	private Date timeStamp;
	private String detail;
	private String description;
	
	

	public Date getTimeStamp() {
		return timeStamp;
	}

	public String getDetail() {
		return detail;
	}

	public String getDescription() {
		return description;
	}


	public ErrorDetails(Date timeStamp, String detail, String description) {
		super();
		this.timeStamp = timeStamp;
		this.detail = detail;
		this.description = description;
	}

	
	
	


}
