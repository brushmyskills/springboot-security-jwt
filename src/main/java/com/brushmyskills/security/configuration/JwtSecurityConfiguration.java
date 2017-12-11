package com.brushmyskills.security.configuration;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.brushmyskills.security.jwtsecurity.JwtAuthenticationEntryPoint;
import com.brushmyskills.security.jwtsecurity.JwtAuthenticationProvider;
import com.brushmyskills.security.jwtsecurity.JwtAuthenticationTokenFilter;
import com.brushmyskills.security.jwtsecurity.JwtSuccessHandler;


@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebSecurity
@Configuration
public class JwtSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	/*
	 * Our custom authentication provider
	 */
	@Autowired
    private JwtAuthenticationProvider authenticationProvider;
    
	@Autowired
    private JwtAuthenticationEntryPoint entryPoint;

	
	  @Bean
	    public AuthenticationManager authenticationManager() {
	        return new ProviderManager(Collections.singletonList(authenticationProvider));
	    }

	    @Bean
	    public JwtAuthenticationTokenFilter authenticationTokenFilter() {
	        JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
	        filter.setAuthenticationManager(authenticationManager());
	        filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
	        return filter;
	    }
	    

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {

	     http.csrf().disable();
	     http.authorizeRequests()
	         .antMatchers("**/secure/**").authenticated()
	         .and()
	         //If the URL is not authenticated, the request will go to the entryPoint
	         .exceptionHandling().authenticationEntryPoint(entryPoint)
	         .and()
	         //making our session stateless
	         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            //We are adding our custom filter before UsernamePasswordAuthenticationFilter
	        
	     http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	     http.headers().cacheControl();

	    }

}
