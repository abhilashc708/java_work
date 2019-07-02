package com.testcode.springbootrestapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/auth/")
public class ApplicationController {
	
	@RequestMapping("/process")
	public String process() {
		return "processing....";
	}

}
