package com.testcode.springbootrestapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testcode.springbootrestapi.dao.UserDao;
import com.testcode.springbootrestapi.model.User;

@RestController
@RequestMapping("/company")
public class UserController {
	
	@Autowired
	UserDao userDao;
	
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {	
		return userDao.save(user);	
	}
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userDao.findAll();
	}
	
	@GetMapping("/notes/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value="id") Long id){
		User user= userDao.findOne(id);
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value="id") Long id, @Valid @RequestBody User user){
		User usr= userDao.findOne(id);
		if(usr==null) {
			return ResponseEntity.notFound().build();
		}
		usr.setName(user.getName());
		usr.setDesignation(user.getDesignation());
		usr.setCreatedAt(user.getCreatedAt());
		
		User userUpdate=userDao.save(usr);
		return ResponseEntity.ok().body(userUpdate);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable(value="id") Long id){
		User user=userDao.findOne(id);
		if(user==null) {
			return ResponseEntity.notFound().build();
		}
		
		userDao.delete(user);
		return ResponseEntity.ok().build();
	}
	
	

}
