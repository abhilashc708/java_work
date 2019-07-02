package com.testcode.springbootrestapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.testcode.springbootrestapi.model.Login;
import com.testcode.springbootrestapi.repository.LoginRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private LoginRepository repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Login login=repository.findByUsername(username);
		CustomUserDetails userDetails=null;
		if(login != null) {
			userDetails=new CustomUserDetails();
			userDetails.setLogin(login);
			
		}
		else
		{
			throw new UsernameNotFoundException("user not exist with name :"+username);
		}
		return userDetails;
		
	
	}

}
