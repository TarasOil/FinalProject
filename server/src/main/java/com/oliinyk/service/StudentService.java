package com.oliinyk.service;

import java.util.List;

import com.oliinyk.domain.StudentDTO;

public interface StudentService {

	void save(StudentDTO student);
	
	StudentDTO findById(Long id);
	
	List<StudentDTO> findAll();
	
	void deleteById(Long id);
	
	StudentDTO findByEmail(String email);
	
	boolean existsByEmail(String email);
}
