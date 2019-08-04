package com.testcode.springbootrestapi.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	public List<Workers> findAll() {
		return workerRepository.findAll();
	}
	
	public Workers findOne(Long worker_id) {
		return workerRepository.findOne(worker_id);
	}
	
	public Workers update(WorkersDTO workersDTO, Long worker_id) {
		Workers wrker= findOne(worker_id);
		wrker.setName(workersDTO.getName());
		wrker.setEmail(workersDTO.getEmail());
		wrker.setPhone(workersDTO.getPhone());
		wrker.setLocation(workersDTO.getLocation());
		wrker.setUpdateddAt(wrker.getUpdateddAt());
		List<Services> services= workersDTO.getServiceIds().stream().map(x->servicesService.findOne(x)).collect(Collectors.toList());
		wrker.setServices(services);
		BeanUtils.copyProperties(workersDTO, wrker);
		return workerRepository.save(wrker);
	}
	
	public void delete(Workers worker) {
		workerRepository.delete(worker);
	}
	
	public Page<Workers> findWorkerByPages(int pageNumber, int size) {
		Pageable pageable = new PageRequest(pageNumber, size);

		return workerRepository.findAll(pageable);
	}

}
