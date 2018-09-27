package com.oliinyk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oliinyk.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long>{

	StudentEntity findByEmail(String email);
	
	boolean existsByEmail(String email);
}
