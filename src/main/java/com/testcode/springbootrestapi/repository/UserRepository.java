package com.testcode.springbootrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testcode.springbootrestapi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
