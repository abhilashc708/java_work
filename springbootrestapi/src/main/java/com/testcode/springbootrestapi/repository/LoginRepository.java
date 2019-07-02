package com.testcode.springbootrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testcode.springbootrestapi.model.Login;

public interface LoginRepository extends JpaRepository<Login, Integer> {

	Login findByUsername(String username);

}
