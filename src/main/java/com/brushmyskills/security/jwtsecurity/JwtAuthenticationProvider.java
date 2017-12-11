package com.brushmyskills.security.jwtsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.brushmyskills.security.model.JwtAuthenticationToken;
import com.brushmyskills.security.model.JwtTokenUser;
import com.brushmyskills.security.model.JwtUserDetails;

import java.util.List;

/*
 * Provide is where major processing happens
 */
@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{
	
	@Autowired
	private  JwtTokenValidator jwtTokenValidator;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authenticationToken)
			throws AuthenticationException {
		//We will convert received token to our JwtAuthenticationToken
		JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken)authenticationToken;
		//Form above JwtAuthenticationToken token we will create string token
		String token = jwtAuthenticationToken.getToken();
		//Now we need to validated our token and will receive a model which we will send across
		JwtTokenUser jwtTokenUser = jwtTokenValidator.validate(token);
		if(jwtTokenUser ==null) {
			throw new RuntimeException("JWT Token is not correct");
		}
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(jwtTokenUser.getRole());
		
		//We are creating finally JwtUserDetails model, 
		//which will used by spring framework to validate further
        return new JwtUserDetails(jwtTokenUser.getUserName(), jwtTokenUser.getId(), token, grantedAuthorities);
	    
	}

	@Override
	public boolean supports(Class<?> authentication) {
		//Below JwtAuthenticationToken class is nothing but model used by other classes
		return JwtAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
