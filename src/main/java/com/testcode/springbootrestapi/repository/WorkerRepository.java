package com.testcode.springbootrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testcode.springbootrestapi.model.Workers;

public interface WorkerRepository extends JpaRepository<Workers, Long> {

}
