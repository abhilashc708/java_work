package com.testcode.springbootrestapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testcode.springbootrestapi.dto.WorkersDTO;
import com.testcode.springbootrestapi.model.Services;
import com.testcode.springbootrestapi.model.Workers;
import com.testcode.springbootrestapi.repository.WorkerRepository;

@Service
public class WorkerService {
	
	@Autowired
	private WorkerRepository workerRepository;
	
	@Autowired
	ServicesService servicesService;
	
	@Transactional
	public Workers save(WorkersDTO workersDTO) {
		Workers workers= new Workers();
		BeanUtils.copyProperties(workersDTO, workers);
		List<Services> services= workersDTO.getServiceIds().stream().map(x->servicesService.findOne(x)).collect(Collectors.toList());
		workers.setServices(services);
		return workerRepository.save(workers);
	}

}
