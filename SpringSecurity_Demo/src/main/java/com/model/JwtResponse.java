package com.model;

public record JwtResponse(String accessToken,
		 					String refreshToken,
		 						User user) 
{
	
		 
}

