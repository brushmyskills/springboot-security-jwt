package com.brushmyskills.security.jwtsecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    
	/*
	 * Need to give some error message, If particular request fails
	 */
	@Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

		//setting error message user is not authorized - 401
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UnAuthorized User");
    }
}