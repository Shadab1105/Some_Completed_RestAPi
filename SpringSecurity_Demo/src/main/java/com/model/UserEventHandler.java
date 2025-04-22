package com.model;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.repository.RoleRepository;

@Component
@RepositoryEventHandler(User.class)
public class UserEventHandler 
{
	@Autowired
	private RoleRepository rr;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@HandleBeforeCreate
	public void handelCreateEvent(User user)
	{
		user.setUid(UUID.randomUUID().toString());
		Role role = rr.findById(3L).orElseThrow(()-> new RuntimeException("Role Not Found"));
	
		user.getRoles().add(role);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
	}
}
