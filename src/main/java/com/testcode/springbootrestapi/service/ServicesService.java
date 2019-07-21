package com.testcode.springbootrestapi.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.testcode.springbootrestapi.dto.ServicesDTO;
import com.testcode.springbootrestapi.model.Services;
import com.testcode.springbootrestapi.repository.ServicesRepository;

@Service
public class ServicesService {

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private ServicesRepository servicesRepository;

	public ServicesDTO save(ServicesDTO servicesDTO) {
		Services services = new Services();
		BeanUtils.copyProperties(servicesDTO, services);
		MultipartFile file = servicesDTO.getFile();
		if (file != null) {
			String filePath = fileStorageService.storeFileInAPath(file);
			services.setImages(filePath);
			servicesDTO.setFile(null);
		}
		services = servicesRepository.save(services);
		BeanUtils.copyProperties(services, servicesDTO);
		return servicesDTO;
	}

	public List<Services> findAll() {
		return servicesRepository.findAll();
	}

	public Services findOne(Long id) {
		return servicesRepository.findOne(id);
	}

	public void delete(Services services) {
		servicesRepository.delete(services);
	}

	public ServicesDTO findById(Long serviceId) {
		ServicesDTO servicesDTO=null;
		Services services= findOne(serviceId);
		if(services!=null){
			servicesDTO= new ServicesDTO();
			BeanUtils.copyProperties(services, servicesDTO);
		}		 
		return servicesDTO;
	}
	
	
	public boolean updateService(ServicesDTO servicesDTO) {
		boolean isUpdated=false;
		Services services = findOne(servicesDTO.getId());
		BeanUtils.copyProperties(servicesDTO, services);
		MultipartFile file = servicesDTO.getFile();
		if (file != null) {
			String filePath = fileStorageService.storeFileInAPath(file);
			services.setImages(filePath);
			servicesDTO.setFile(null);
		}
		servicesRepository.save(services);
		isUpdated=true;
		return isUpdated;
	}

}
