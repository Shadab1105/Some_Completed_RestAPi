package com.model;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Emp 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name = "empName")
	@NotNull(message = "Name name can not be null")
	@Size(min = 4,max = 20,message = "Name should be between 4 to 20")
	private String empName;
	

	@Column(name = "empSalary")
	@NotNull(message = "Salary name can not be null")
	//@Size(min = 5,message = "Salary should be Greater Than equal to 10000")
	@DecimalMin(value ="10000" ,message = "Salary should be greater than 10000")
	@DecimalMax(value ="50000" ,message = "Salary should be less than 50000")
	private Double empSalary;
	
	@Column(name = "number")
	@NotNull(message = "Number name can not be null")
	@Min(value = 1000000000L, message = "Number should be 10 digits")
    @Max(value = 9999999999L, message = "Number should be 10 digits")
	private Long num;
	
	@Column(name = "address")
	@NotNull(message = "Address name can not be null")
	@Size(min = 4,max = 30,message = "Address should be between 4 to 30")
	private String address;
	
	
	@Column(name = "dob")
    @Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dob;


	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name =  "cId",referencedColumnName = "cId",foreignKey = @ForeignKey(name="fk_cId"))
	private Company company;
}
