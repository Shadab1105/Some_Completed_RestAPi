package com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exception.ResourceNotFoundException;
import com.model.User;
import com.repository.UserRepository;

@Service
public class UserService implements UserDetailsService 
{
	
	private final UserRepository ur;
	
	
	



	public UserService(UserRepository ur) {
		this.ur = ur;
	}




	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{

		User user = ur.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("Not Found ="+ username) );

		 UserDetails userDetails = new CustomUserDetail(user);
		
		return userDetails;
	}


	
	
	
	
}
