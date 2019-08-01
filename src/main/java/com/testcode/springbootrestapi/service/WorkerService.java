package com.testcode.springbootrestapi.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.testcode.springbootrestapi.model.Workers;
import com.testcode.springbootrestapi.repository.WorkerRepository;

@Service
public class WorkerService {
	
	@Autowired
	private WorkerRepository workerRepository;
	
	@Transactional
	public Workers save(Workers workers) {
		return workerRepository.save(workers);
	}

}
