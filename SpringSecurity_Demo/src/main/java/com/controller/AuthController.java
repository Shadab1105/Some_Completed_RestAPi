package com.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.JwtService;
import com.model.AuthRequest;
import com.model.JwtResponse;
import com.model.RefreshTokenRequest;
import com.model.User;
import com.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController 
{

	private final UserRepository ur;
	
	private final AuthenticationManager authManager;
	private final UserDetailsService userDetailsService;
	private final JwtService jwtService;
	



	

	public AuthController(UserRepository ur, AuthenticationManager authManager, UserDetailsService userDetailsService,
			JwtService jwtService) {
		this.ur = ur;
		this.authManager = authManager;
		this.userDetailsService = userDetailsService;
		this.jwtService = jwtService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest request)
	{
		authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
		
		String accessToken=jwtService.generateToken(request.getEmail(),true);
		String refreshToken=jwtService.generateToken(request.getEmail(),false);
		
		User user = ur.findByEmail(request.getEmail()).get();
		
		JwtResponse jwtResponse = new JwtResponse(accessToken,refreshToken,user);
		
		return ResponseEntity.ok(jwtResponse);
		
	}
	
	@PostMapping("/refresh-token")
	public ResponseEntity<?> freshToken(@RequestBody RefreshTokenRequest request)
	{
		if(jwtService.validateToken(request.refreshToken()))
		{
		String usernameFromToken=	jwtService.getUsernameFromToken(request.refreshToken());
		String accessToken=jwtService.generateToken(usernameFromToken,true);
		String refreshToken=jwtService.generateToken(request.refreshToken(),false);
		
		User user=	ur.findByEmail(usernameFromToken).get();
		JwtResponse jwtResponse=new JwtResponse(accessToken,refreshToken,user);
		
		return ResponseEntity.ok(jwtResponse);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Refresh Token");
	}
	
	
}
