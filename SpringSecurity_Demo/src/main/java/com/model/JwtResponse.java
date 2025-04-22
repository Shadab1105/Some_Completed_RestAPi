package com.model;

import com.dto.UserDTO;

public record JwtResponse(String accessToken,
		 					String refreshToken,
		 						User user) 
{
	
		 
}



