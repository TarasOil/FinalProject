package com.oliinyk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.oliinyk.domain.CompetitionDTO;
import com.oliinyk.entity.CompetitionEntity;
import com.oliinyk.exceptions.CompetitionServiceException;
import com.oliinyk.repository.CompetitionRepository;
import com.oliinyk.service.CompetitionService;
import com.oliinyk.service.impl.utils.ObjectMapperUtils;
import static com.oliinyk.constants.ErrorMessages.*;

@Service
public class CompetitionServiceImpl implements CompetitionService {

	@Autowired
	private CompetitionRepository competitionRepository;
	
	@Autowired
	private ObjectMapperUtils objectMapper;
	
	@Override
	public void save(CompetitionDTO competition) {
		competitionRepository.save(objectMapper.map(competition, CompetitionEntity.class));
	}

	@Override
	public CompetitionDTO findById(Long id) {
		try {
			CompetitionEntity competition = competitionRepository.findById(id).get();
			return objectMapper.map(competition, CompetitionDTO.class);
		} catch (Exception e) {
			throw new CompetitionServiceException(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<CompetitionDTO> findAll() {
		return objectMapper.mapAll(competitionRepository.findAll(), CompetitionDTO.class);
	}

	@Override
	public void deleteById(Long id) {
		if(competitionRepository.existsById(id) == false) {
			throw new CompetitionServiceException(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
		competitionRepository.deleteById(id);
	}

}
