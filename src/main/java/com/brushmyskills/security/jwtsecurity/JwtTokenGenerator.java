package com.brushmyskills.security.jwtsecurity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import com.brushmyskills.security.model.JwtTokenUser;

@Component
public class JwtTokenGenerator {


	/*
	 * This below function will generate & return our Jwt Token in form of
	 * [header.payload.signature]
	 */
    public String generate(JwtTokenUser jwtUser) {


    	/*
    	 * We need to create claims, which will be used by JwtTokenValidator
    	 */
        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUserName());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", jwtUser.getRole());

        //Right now we are returning infinite time valid token
        //For particular time validity of token we can set expiration on Jwts
        //Jwts.builder().setExpiration(Date date)
        return Jwts.builder()
                .setClaims(claims)
                //We need to sign it using algorithm and with our secret
                .signWith(SignatureAlgorithm.HS512, "brushmyskills")
                //We don't want very big token, so we are saying compact
                .compact();
    }
}