package com.testcode.springbootrestapi.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testcode.springbootrestapi.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	public Set<Role> findAllByRoleIn(List<String> names);

}
