package com.testcode.springbootrestapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Role {
	
	@Id
	@GeneratedValue
	private Long roleid;
	private String role;
	
	
	
	public Long getRoleid() {
		return roleid;
	}
	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}