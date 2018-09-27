package com.oliinyk.service;

import java.util.List;

import com.oliinyk.domain.CompetitionDTO;

public interface CompetitionService {

	void save(CompetitionDTO competition);
	
	CompetitionDTO findById(Long id);
	
	List<CompetitionDTO> findAll();
	
	void deleteById(Long id);
}
