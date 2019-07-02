package com.testcode.springbootrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testcode.springbootrestapi.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
