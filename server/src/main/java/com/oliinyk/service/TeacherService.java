package com.oliinyk.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.oliinyk.domain.TeacherDTO;

public interface TeacherService {

	void save(TeacherDTO teacher);
	
	TeacherDTO findById(Long id);
	
	List<TeacherDTO> findAll();
	
	void deleteById(Long id);
	
	TeacherDTO findByEmail(String email);
	
	boolean existsByEmail(String email);
	
	void uploadPhoto(MultipartFile file, Long id);
}
