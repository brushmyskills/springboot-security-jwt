package com.brushmyskills.security.jwtsecurity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import com.brushmyskills.security.model.JwtTokenUser;

@Component
public class JwtTokenValidator {


    private String secret = "brushmyskills";

    /*
     * io.jsonwebtoken dependency in pom.xml helping us here
     */
    public JwtTokenUser validate(String token) {

        JwtTokenUser jwtTokenUser = null;
        try {
            
        	/*
        	 *here we are parsing our received to token [header.payload.signature] 
        	 * with the help of Jwts
        	 */
        	Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    //getBody is nothing but payload
                    .getBody();
        	
        	
        	jwtTokenUser = new JwtTokenUser();

        	jwtTokenUser.setUserName(body.getSubject());
        	jwtTokenUser.setId(Long.parseLong((String) body.get("userId")));
        	jwtTokenUser.setRole((String) body.get("role"));
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return jwtTokenUser;
    }
}