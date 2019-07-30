package com.testcode.springbootrestapi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javassist.SerialVersionUID;

@Entity
@Table(name = "workers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
public class Workers implements Serializable  {
	private static final long SerialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long worker_id;
	
	@NotBlank
	private String name;
	
	private String phone;
	
	@Email
	private String email;
	
	private String location;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "workers_services",
	joinColumns = @JoinColumn( name = "worker_id", referencedColumnName="worker_id", nullable = false), 
	inverseJoinColumns = @JoinColumn(name = "id", referencedColumnName="id" , nullable = false))
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Services services;
	
	public Workers() {}
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updateddAt;

	public static long getSerialversionuid() {
		return SerialVersionUID;
	}

	public Long getWorker_id() {
		return worker_id;
	}

	public void setWorker_id(Long worker_id) {
		this.worker_id = worker_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Services getServices() {
		return services;
	}

	public void setServices(Services services) {
		this.services = services;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdateddAt() {
		return updateddAt;
	}

	public void setUpdateddAt(Date updateddAt) {
		this.updateddAt = updateddAt;
	}
	
	

}
