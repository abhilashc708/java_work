package com.testcode.springbootrestapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.testcode.springbootrestapi.dto.ServicesDTO;
import com.testcode.springbootrestapi.service.ServicesService;

@Controller
public class ServiceController {

	@Autowired
	private ServicesService servicesService;

	@PostMapping("/services")
	public ResponseEntity<ServicesDTO> createService(@ModelAttribute ServicesDTO services) {
		services = servicesService.save(services);
		return new ResponseEntity<ServicesDTO>(services, HttpStatus.OK);
	}
}
