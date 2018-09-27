package com.oliinyk.service;

import java.util.List;

import com.oliinyk.domain.SongDTO;

public interface SongService {

	void save(SongDTO song);
	
	SongDTO findById(Long id);
	
	List<SongDTO> findAll();
	
	void deleteById(Long id);
	
	SongDTO findByName(String name);
	
	boolean existsByName(String name);
}
