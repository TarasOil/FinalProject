package com.oliinyk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.oliinyk.domain.ScheduleDTO;
import com.oliinyk.entity.ScheduleEntity;
import com.oliinyk.exceptions.ScheduleServiceException;
import com.oliinyk.repository.ScheduleRepository;
import com.oliinyk.service.ScheduleService;
import com.oliinyk.service.impl.utils.ObjectMapperUtils;
import static com.oliinyk.constants.ErrorMessages.*;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	private ObjectMapperUtils objectMapper;
	
	@Override
	public void save(ScheduleDTO schedule) {
		scheduleRepository.save(objectMapper.map(schedule, ScheduleEntity.class));
	}

	@Override
	public ScheduleDTO findById(Long id) {
		try {
			ScheduleEntity schedule = scheduleRepository.findById(id).get();
			return objectMapper.map(schedule, ScheduleDTO.class);
		} catch (Exception e) {
			throw new ScheduleServiceException(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<ScheduleDTO> findAll() {
		return objectMapper.mapAll(scheduleRepository.findAll(), ScheduleDTO.class);
	}

	@Override
	public void deleteById(Long id) {
		if(scheduleRepository.existsById(id) == false) {
			throw new ScheduleServiceException(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
		scheduleRepository.deleteById(id);
	}

}
