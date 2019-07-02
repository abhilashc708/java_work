package com.testcode.springbootrestapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testcode.springbootrestapi.model.Login;
import com.testcode.springbootrestapi.repository.LoginRepository;

@RestController
@RequestMapping("/secure/rest")
public class AdminController {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/admin/add")
	public String userAddByAdmin(@RequestBody Login login) {
		String pwd=login.getPassword();
		String encryptPwd=passwordEncoder.encode(pwd);
		login.setPassword(encryptPwd);
		loginRepository.save(login);
		return "user added successfully...";


	}
}
