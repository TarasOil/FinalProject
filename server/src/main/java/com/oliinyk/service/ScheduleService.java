package com.oliinyk.service;

import java.util.List;

import com.oliinyk.domain.ScheduleDTO;

public interface ScheduleService {

	void save(ScheduleDTO schedule);
	
	ScheduleDTO findById(Long id);
	
	List<ScheduleDTO> findAll();
	
	void deleteById(Long id);
}
