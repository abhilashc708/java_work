package com.testcode.springbootrestapi.service;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.testcode.springbootrestapi.model.User;
import com.testcode.springbootrestapi.repository.RoleRepository;
import com.testcode.springbootrestapi.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	@Transactional
	public User save(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	public User update(User user, Long id) {
		User usr =findOne(id);
		if(user.getPassword().equals("") || user.getPassword() == null ) {
			usr.setPassword(new BCryptPasswordEncoder().encode(usr.getPassword()));
		}
		else
		{
			usr.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		}
		usr.setName(user.getName());
		usr.setPhone(user.getPhone());
		usr.setEmail(user.getEmail());
		usr.setAge(user.getAge());
		usr.setLocation(user.getLocation());
		usr.setUpdateddAt(user.getUpdateddAt());
		usr.setRoles(user.getRoles());
		return userRepository.saveAndFlush(usr);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

	public Page<User> findUserByPages(int pageNumber, int size) {
		Pageable pageable = new PageRequest(pageNumber, size);

		return userRepository.findAll(pageable);
	}

}
