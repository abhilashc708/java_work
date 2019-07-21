package com.testcode.springbootrestapi.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.testcode.springbootrestapi.UserApplication;
import com.testcode.springbootrestapi.model.User;
import com.testcode.springbootrestapi.service.UserService;

@RestController
@RequestMapping("/company")
public class UserController {

	@Autowired
	UserService userDao;
	private static final Logger LOGGER = LogManager.getLogger(UserController.class);

	@PostMapping("/users")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
		try {
			user = userDao.save(user);
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

	}

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) {
		User user = userDao.findOne(id);
		if (user == null) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok().body(user);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id, @Valid @RequestBody User user) {
		User usr = userDao.findOne(id);
		if (usr == null) {
			return ResponseEntity.notFound().build();
		}
		usr.setName(user.getName());
		usr.setPhone(user.getPhone());
		usr.setEmail(user.getEmail());
		usr.setAge(user.getAge());
		usr.setLocation(user.getLocation());
		usr.setUpdateddAt(user.getUpdateddAt());
		usr.setRoles(user.getRoles());
		User userUpdate = userDao.save(usr);
		return ResponseEntity.ok().body(userUpdate);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) {
		User user = userDao.findOne(id);
		if (user == null) {
			// return ResponseEntity.notFound().build();
			return ((BodyBuilder) ResponseEntity.notFound()).body("User Not Found");
		}

		userDao.delete(user);
		return ResponseEntity.ok().body(user.getName() + "  Successfully Deleted");
	}

	@GetMapping("/userslist")
	public ResponseEntity<Page<User>> listUserByPages(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		try {

			Page<User> userPages = userDao.findUserByPages(page, size);
			return ResponseEntity.ok(userPages);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
