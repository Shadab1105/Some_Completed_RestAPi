package com.jwt;

import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final String SECRET_KEY = "abcdefghijklmnopqrstucghcghcgvwxyzabcdef";
	private static final long ACCESS_TOKEN_EXPIRATION = 15 * 60 * 1000; // 15 minute

	private static final long REFRESH_TOKEN_EXPIRATION = 48 * 60 * 60 * 1000; // 48 hour

	// Generate Token

	public String generateToken(String username,boolean isAccessToken)
	{
		long expiration=isAccessToken ? ACCESS_TOKEN_EXPIRATION:REFRESH_TOKEN_EXPIRATION;
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+expiration))
				.signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()),SignatureAlgorithm.HS256)
				.compact();
		/*  compact();
		   	Yeh method JWT ko generate karke compact (small) string form mein 
		  	convert
		  	karta hai, jise aap return kar sakte hain.
		 */
	}

	// Get Name from Token
	public String getUsernameFromToken(String token) {
		return Jwts.parser()
				.setSigningKey(SECRET_KEY.getBytes())
				.build().parseClaimsJws(token)
				.getBody()
				/*
				 * getBody() method JWT ka body (actual claims) return karta hai, jisme subject
				 * (username) aur other information hoti hai.
				 */
				.getSubject();
		/*
		 * Yeh method body se subject (jo is case mein username hota hai) ko extract
		 * karta hai aur return karta hai.
		 */

	}

	// validate
	public boolean validateToken(String token) {
		System.out.println("Token received: [" + token + "]");

		try {
			Jwts.parser() 
					/*
					 * Jwts.parser() JWT ko parse karne ka kaam karta hai. Yeh method token ko read
					 * karne ke liye use hoti hai.
					 */
			.setSigningKey(SECRET_KEY.getBytes())
					/*
					 * Yeh method SECRET_KEY ko bytes mein convert kar ke JWT token ko verify karne
					 * ke liye use karta hai. Agar key galat hogi, toh verification fail ho jayega.
					 */
			.build()
					/*
					 * build() method parser ko setup karne ke baad parser object ko create kar leta
					 * hai.
					 */
			.parseClaimsJws(token);
			/*
			 * Yeh method JWT ko parse karta hai aur token ke claims ko extract karta hai
			 * (jaise subject, issued at, etc.).
			 */

			return true;
			
		}

		catch (JwtException ex) {
			System.out.println("Jwt error" + ex.getMessage());
			return false;
		}
	}

}
