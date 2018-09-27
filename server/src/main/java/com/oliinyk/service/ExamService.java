package com.oliinyk.service;

import java.util.List;

import com.oliinyk.domain.ExamDTO;

public interface ExamService {
	
	void save(ExamDTO exam);
	
	ExamDTO findById(Long id);
	
	List<ExamDTO> findAll();
	
	void deleteById(Long id);
}
