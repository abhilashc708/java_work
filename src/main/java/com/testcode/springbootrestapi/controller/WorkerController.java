package com.testcode.springbootrestapi.controller;



import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.testcode.springbootrestapi.model.Services;
import com.testcode.springbootrestapi.model.Workers;
import com.testcode.springbootrestapi.service.ServicesService;
import com.testcode.springbootrestapi.service.WorkerService;

@Controller
@RequestMapping("/worker")
public class WorkerController {

	@Autowired
	private WorkerService workerService;
	private static final Logger LOGGER = LogManager.getLogger(WorkerController.class);
	
	@Autowired
	private ServicesService servicesService;
	
	@PostMapping("/services/{id}/workers")
	public ResponseEntity<?> addWorkers(@PathVariable Long id,
			@Valid @RequestBody Workers workers) {
		try {
			Services services = servicesService.findOne(id);
			workers.setServices(services);
			workers = workerService.save(workers);
			return ResponseEntity.ok(workers);
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
		//return servicesService.findOne(id)
		  /*.map(service -> {
			  workers.setServices(service);
			return workerService.save(workers);
		}).orElseThrow(() -> new NotFoundException("sevice not found!"));*/
		
		
		
	}
	
	
}
