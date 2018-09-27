package com.oliinyk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.oliinyk.domain.SingerDTO;
import com.oliinyk.entity.SingerEntity;
import com.oliinyk.exceptions.SingerServiceException;
import com.oliinyk.repository.SingerRepository;
import com.oliinyk.service.SingerService;
import com.oliinyk.service.impl.utils.ObjectMapperUtils;
import static com.oliinyk.constants.ErrorMessages.*;

@Service
public class SingerServiceImpl implements SingerService {

	@Autowired
	private SingerRepository singerRepository;
	
	@Autowired
	private ObjectMapperUtils objectMapper;
	
	@Override
	public void save(SingerDTO singer) {
		singerRepository.save(objectMapper.map(singer, SingerEntity.class));
	}

	@Override
	public SingerDTO findById(Long id) {
		try {
			SingerEntity singer = singerRepository.findById(id).get();
			return objectMapper.map(singer, SingerDTO.class);
		} catch (Exception e) {
			throw new SingerServiceException(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<SingerDTO> findAll() {
		return objectMapper.mapAll(singerRepository.findAll(), SingerDTO.class);
	}

	@Override
	public void deleteById(Long id) {
		if(singerRepository.existsById(id) == false) {
			throw new SingerServiceException(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
		singerRepository.deleteById(id);
	}

}
