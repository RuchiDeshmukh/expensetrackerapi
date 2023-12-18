package com.expensetrackerapi.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

	private final int JWTTOKEN_VALIDITY = 5*60*60;
	

	//@Value("${jwt.secret}")
	private String secret="b2tech";

	public String generateToken(UserDetails userDetails) {
		Map<String,Object> claims = new HashMap<>();
		
		return Jwts.builder()
		.setClaims(claims)
		.setSubject(userDetails.getUsername())
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis()+JWTTOKEN_VALIDITY *1000))
		.signWith(SignatureAlgorithm.HS512,secret)
		.compact();
	}
	
	public String getUserNameFromToken(String jwtToken) {
		return getClaimFromToken(jwtToken, Claims :: getSubject);
	}
	
	private <T> T getClaimFromToken(String token,Function <Claims,T> claimsResolver) {
	
		final Claims claims =  Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		
		return claimsResolver.apply(claims);
	}

	public boolean validateToken(String jwtToken, UserDetails userDetails) {
		String username = getUserNameFromToken(jwtToken);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken);
	}

	private boolean isTokenExpired(String jwtToken) {
		Date expirationDate = getExpirationDateFromToken(jwtToken);
		return expirationDate.before(new Date());
	}

	private Date getExpirationDateFromToken(String jwtToken) {
		return getClaimFromToken(jwtToken, Claims :: getExpiration);
	}

}
