package com.brushmyskills.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/secure")
@RestController
public class SecureRestEndPointController {

	@GetMapping("/all")
	public String getAllSecureResource() {

		return "Returning all resources from secured Rest End Point";

	}

	@GetMapping("/all/admin")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public String getAllAdminOnlySecureResource() {

		return "Returning all resources from secured Rest End Point which has Admin role access";

	}

}
