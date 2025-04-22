package com.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter 
{	
	private JwtService jwtService;
	
	
	private UserService userService;

	public JwtAuthenticationFilter(JwtService jwtService, UserService userService) {
		
		this.jwtService = jwtService;
		this.userService = userService;
	}

//	@Override
//	protected void doFilterInternal(HttpServletRequest request, 
//									HttpServletResponse response, 
//									FilterChain filterChain) throws ServletException, 
//																	IOException 
//		{
//		
//		
//
//			String authHeader=request.getHeader("Authorization");
//			if(authHeader!=null && authHeader.startsWith("Bearer ")) {
//			
//				String token=authHeader.substring(7);
//				
//				if(jwtService.validateToken(token)) {
//				
//					String usernameToken =jwtService.getUsernameFromToken(token);
//					UserDetails userDetails=userService.loadUserByUsername(usernameToken);
//				
//				
//				if(SecurityContextHolder.getContext().getAuthentication()==null) {
//				
//					UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails,userDetails.getAuthorities());
//					SecurityContextHolder.getContext().setAuthentication(authentication);
//				
//			
//				}}}
//			
//			filterChain.doFilter(request, response);
//		}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {

	    String authHeader = request.getHeader("Authorization");

	    if (authHeader != null && authHeader.startsWith("Bearer ")) {

	        // 1. Remove "Bearer " prefix
	        String token = authHeader.substring(7);
	        System.out.println("Token received: [" + token + "]");

	        // 2. Validate token
	        if (jwtService.validateToken(token)) {

	            // 3. Get username from token
	            String username = jwtService.getUsernameFromToken(token);

	            // 4. Load user details from DB
	            UserDetails userDetails = userService.loadUserByUsername(username);

	            // 5. Create authentication object
	            UsernamePasswordAuthenticationToken authToken =
	                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

	            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	            // 6. Set authentication in context
	            SecurityContextHolder.getContext().setAuthentication(authToken);
	        }
	    }

	    // Continue filter chain
	    filterChain.doFilter(request, response);
	}

	
	
	
	
	
	
}

