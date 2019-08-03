package com.testcode.springbootrestapi.controller;



import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@PostMapping("/services/workers")
	public ResponseEntity<?> addWorkers(@RequestBody WorkersDTO workersDTO) {
		try {
			Workers workers = workerService.save(workersDTO);
			return ResponseEntity.ok(workers);
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
		
	}
	
	
	@GetMapping("/services/workers")
	public List<Workers> getAllWorkers() {
		return workerService.findAll();
	}
	
	
	@GetMapping("/services/workers/{worker_id}")
	public ResponseEntity<?> getUserById(@PathVariable(value = "worker_id") Long worker_id) {
		try {
		Workers worker = workerService.findOne(worker_id);
		if (worker == null) {
			return ((BodyBuilder) ResponseEntity.notFound()).body("User Not Found");
		}
		return ResponseEntity.ok().body(worker);
	}
	catch(Exception e) {
		
		LOGGER.error(e.getMessage(), e);
		System.out.println(e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
		
	}
}
	
	
	@PutMapping("/services/workers/{worker_id}")
	public ResponseEntity<?> updateUser(@PathVariable(value = "worker_id") Long worker_id, @RequestBody WorkersDTO workersDTO) {
		try {
		Workers workerUpdate = workerService.update(workersDTO, worker_id);
		return ResponseEntity.ok().body(workerUpdate);
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@DeleteMapping("/services/workers/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) {
		try {
		Workers worker = workerService.findOne(id);
		if (worker == null) {
			return ((BodyBuilder) ResponseEntity.notFound()).body("User Not Found");
		}

		workerService.delete(worker);
		return ResponseEntity.ok().body(worker.getName() + "  Successfully Deleted");
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@GetMapping("/services/workerslist")
	public ResponseEntity<Page<Workers>> listWorkerByPages(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		try {

			Page<Workers> workerPages = workerService.findWorkerByPages(page, size);
			return ResponseEntity.ok(workerPages);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
