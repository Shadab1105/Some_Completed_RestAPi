package com.dto;

import java.util.Set;

import com.model.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDTO 
{
	private Long id;
	private String name;
	
//	private Set<User> users;

}
