package com.testcode.springbootrestapi.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.testcode.springbootrestapi.dto.ServicesDTO;
import com.testcode.springbootrestapi.model.Services;
import com.testcode.springbootrestapi.service.ServicesService;

@Controller
public class ServiceController {

	@Autowired
	private ServicesService servicesService;

	@PostMapping("/services")
	public ResponseEntity<ServicesDTO> createService(@ModelAttribute ServicesDTO services) {
		
		try {
			services = servicesService.save(services);
			return ResponseEntity.created(new URI("/services/"+services.getId().toString())).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@PostMapping("/services/{serviceId}")
	public ResponseEntity<?> updateService(@PathVariable Long serviceId,  @ModelAttribute ServicesDTO services) {
		Services servicesOb= servicesService.findOne(serviceId);
		if(servicesOb==null) {
			return ResponseEntity.notFound().build();
		}
		
		 if(servicesService.updateService(services)){
			 return new ResponseEntity<ServicesDTO>(services, HttpStatus.OK); 
		 }else{
			 return  ResponseEntity.badRequest().build(); 
		 }
		
	}
}
