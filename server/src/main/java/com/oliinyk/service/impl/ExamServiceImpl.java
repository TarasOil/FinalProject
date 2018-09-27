package com.oliinyk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.oliinyk.domain.ExamDTO;
import com.oliinyk.entity.ExamEntity;
import com.oliinyk.exceptions.ExamServiceException;
import com.oliinyk.repository.ExamRepository;
import com.oliinyk.service.ExamService;
import com.oliinyk.service.impl.utils.ObjectMapperUtils;
import static com.oliinyk.constants.ErrorMessages.*;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamRepository examRepository;
	
	@Autowired
	private ObjectMapperUtils objectMapper;
	
	@Override
	public void save(ExamDTO exam) {
		examRepository.save(objectMapper.map(exam, ExamEntity.class));
	}

	@Override
	public ExamDTO findById(Long id) {
		try {
			ExamEntity exam = examRepository.findById(id).get();
			return objectMapper.map(exam, ExamDTO.class);
		} catch (Exception e) {
			throw new ExamServiceException(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<ExamDTO> findAll() {
		return objectMapper.mapAll(examRepository.findAll(), ExamDTO.class);
	}

	@Override
	public void deleteById(Long id) {
		if(examRepository.existsById(id) == false) {
			throw new ExamServiceException(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
		examRepository.deleteById(id);
	}

}
