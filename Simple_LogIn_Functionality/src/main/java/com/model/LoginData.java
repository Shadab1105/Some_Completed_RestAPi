package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class LoginData 
{
	
	@Id
	private String Username;
	private String password;

}
