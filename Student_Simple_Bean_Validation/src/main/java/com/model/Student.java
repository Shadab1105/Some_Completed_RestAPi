package com.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity

public class Student implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Name Can Not be null")
	@Size(min=3,max=15,message = "Name must be between 3 and 15 character")
	private String name;
	
	@NotNull(message = "Name Can Not be null")
	@Size(min=3,max=15,message = "Father's Name must be between 3 and 15 character")
	private String fName;
	
	@NotNull(message = "Name Can Not be null")
	@Size(min=3,max=25,message = "Address must be between 3 and 15 character")
	private String address;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", fName=" + fName + ", address=" + address + ", getId()="
				+ getId() + ", getName()=" + getName() + ", getfName()=" + getfName() + ", getAddress()=" + getAddress()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	

}
