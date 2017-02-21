package com.cjsy3c;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

@Service
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class JwtToken {
	
	private JWTVerifier verifier;
	private final String signingKey =null;
	
	@Autowired
	private JwtToken() throws Exception{
		verifier = JWT.require(Algorithm.HMAC256(signingKey))
				.withClaim("role", "admin").acceptLeeway(1000L)
				.build(); 
	}
	
	// exception if false
	public JWT verify(String token){
		JWT jwt = (JWT) verifier.verify(token);
		return jwt;
	}
	
	public String createToken() throws Exception{
		Date d = new Date();
		d.setTime(d.getTime()+3600);
		// create
		String token = JWT.create().withExpiresAt(d)
				.withClaim("role", "admin").withClaim("userid", "Cody")
				.sign(Algorithm.HMAC256(signingKey));
		
		return token;
	}
	
}
