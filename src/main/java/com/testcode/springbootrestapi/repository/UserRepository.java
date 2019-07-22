package com.testcode.springbootrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.testcode.springbootrestapi.model.User;

public interface UserRepository extends JpaRepository<User, Long>,PagingAndSortingRepository<User, Long> {

}
