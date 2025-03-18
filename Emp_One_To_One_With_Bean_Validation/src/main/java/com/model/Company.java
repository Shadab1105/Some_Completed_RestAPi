package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Company 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cId;
	
	@NotNull(message = "Company Name can not be null")
	@Size(min = 3,max=25,message = "Company name should be 3 to 25 character")
	private String companyName;
	
	@NotNull(message = "Company Adress Name can not be null")
	@Size(min = 3,max=25,message = "Company Adress name should be 3 to 25 character")
	private String address;

}
