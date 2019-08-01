package com.testcode.springbootrestapi.controller;



import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.testcode.springbootrestapi.dto.WorkersDTO;
import com.testcode.springbootrestapi.model.Workers;
import com.testcode.springbootrestapi.service.ServicesService;
import com.testcode.springbootrestapi.service.WorkerService;

@RestController
public class WorkerController {

	@Autowired
	private WorkerService workerService;
	private static final Logger LOGGER = LogManager.getLogger(WorkerController.class);
	
	@Autowired
	private ServicesService servicesService;
	
	@PostMapping("/services/workers")
	public ResponseEntity<?> addWorkers(@RequestBody WorkersDTO workersDTO) {
		try {
			//Services services = servicesService.findOne(id);
			//workers.setServices(services);
			Workers workers = workerService.save(workersDTO);
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
	
	
	@GetMapping("/services/workers")
	public List<Workers> getAllWorkers() {
		return workerService.findAll();
	}
	
	
}
