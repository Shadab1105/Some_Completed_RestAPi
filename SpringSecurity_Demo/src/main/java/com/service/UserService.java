package com.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dto.RoleDTO;
import com.dto.UserDTO;
import com.exception.ResourceNotFoundException;
import com.model.User;
import com.repository.UserRepository;

@Service
public class UserService implements UserDetailsService 
{
	
	private final UserRepository ur;
	
	@Autowired
    private ModelMapper modelMapper;



	public UserService(UserRepository ur) {
		this.ur = ur;
	}



	/* When We use Many TO One Mapping */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		User user = ur.findByEmail(username).orElseThrow(()-> 
		new ResourceNotFoundException("Not Found ="+ username) );
		
		UserDetails userDetails = new CustomUserDetail(user);
		
		return userDetails;
	}


	
	
	
	
	/* When we use Many to Many */
	
//	public UserDTO getUserDTO(String email) {
//        User user = ur.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Convert User entity to UserDTO using ModelMapper
//        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
//
//        // Manually mapping roles to RoleDTO
//        Set<RoleDTO> roles = user.getRoles().stream()
//                                  .map(role -> modelMapper.map(role, RoleDTO.class))
//                                  .collect(Collectors.toSet());
//
//        userDTO.setRoles(roles);
//
//        return userDTO;
//    }
//	
	
	
	
}
