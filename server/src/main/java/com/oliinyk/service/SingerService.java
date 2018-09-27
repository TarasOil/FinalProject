package com.oliinyk.service;

import java.util.List;

import com.oliinyk.domain.SingerDTO;

public interface SingerService {
	
	void save(SingerDTO singer);
	
	SingerDTO findById(Long id);
	
	List<SingerDTO> findAll();
	
	void deleteById(Long id);
}
