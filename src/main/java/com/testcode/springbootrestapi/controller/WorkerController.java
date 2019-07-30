package com.testcode.springbootrestapi.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.testcode.springbootrestapi.dto.WorkersDTO;
import com.testcode.springbootrestapi.model.Workers;
import com.testcode.springbootrestapi.service.ServicesService;
import com.testcode.springbootrestapi.service.WorkerService;

@Controller
@RequestMapping("/worker")
public class WorkerController {

	@Autowired
	private WorkerService workerService;
	
	@Autowired
	private ServicesService servicesService;
	
	@PostMapping("/services/{id}/workers")
	public Workers addWorkers(@PathVariable Long id,
			@Valid @RequestBody Workers workers) {
		return servicesService.findOne(id)
		  .map(service -> {
			  workers.setServices(service);
			return workerService.save(workers);
		}).orElseThrow(() -> new NotFoundException("sevice not found!"));
		
		
		
	}
	
	
}
