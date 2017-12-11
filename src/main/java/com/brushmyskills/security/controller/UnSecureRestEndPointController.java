package com.brushmyskills.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brushmyskills.security.jwtsecurity.JwtTokenGenerator;
import com.brushmyskills.security.model.JwtTokenUser;

@RequestMapping("/api/unsecure")
@RestController
public class UnSecureRestEndPointController {
	
	private JwtTokenGenerator jwtTokenGenerator;
	
	public UnSecureRestEndPointController(JwtTokenGenerator jwtTokenGenerator) {
		super();
		this.jwtTokenGenerator = jwtTokenGenerator;
	}


	@PostMapping("/generateJwtToken")
	public String generatorJwtToken(@RequestBody JwtTokenUser jwtTokenUser) {

		jwtTokenGenerator = new JwtTokenGenerator();
		return jwtTokenGenerator.generate(jwtTokenUser);

	}

	
	@GetMapping("/all")
	public String getAllUnSecuredResource() {
		
		return "Returning all resources from unsecured Rest End Point";
		
	}

}
