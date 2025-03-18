package com.exception;

import java.util.Date;

public class ErrorDetail 
{
	private Date timeStamp;
	private String detail;
	private String description;
	
	
	private Date getTimeStamp()
	{
		return timeStamp;
	}

	public String getDetail() {
		return detail;
	}


	public String getDescription() {
		return description;
	}

	public ErrorDetail(Date timeStamp, String detail, String description) {
		super();
		this.timeStamp = timeStamp;
		this.detail = detail;
		this.description = description;
	}


	


	

	
}
