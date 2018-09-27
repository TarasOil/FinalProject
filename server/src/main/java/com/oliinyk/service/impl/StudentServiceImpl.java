package com.oliinyk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.oliinyk.domain.StudentDTO;
import com.oliinyk.entity.StudentEntity;
import com.oliinyk.exceptions.StudentServiceException;
import com.oliinyk.repository.StudentRepository;
import com.oliinyk.service.StudentService;
import com.oliinyk.service.impl.utils.ObjectMapperUtils;

import static com.oliinyk.constants.ErrorMessages.*;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ObjectMapperUtils objectMapper;
	
	@Override
	public void save(StudentDTO student) {
		if(studentRepository.existsByEmail(student.getEmail()) == true) {
			throw new StudentServiceException(RECORD_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
		}
		studentRepository.save(objectMapper.map(student, StudentEntity.class));
	}

	@Override
	public StudentDTO findById(Long id) {
		try {
			StudentEntity student = studentRepository.findById(id).get();
			return objectMapper.map(student, StudentDTO.class);
		} catch (Exception e) {
			throw new StudentServiceException(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<StudentDTO> findAll() {
		return objectMapper.mapAll(studentRepository.findAll(), StudentDTO.class);
	}

	@Override
	public void deleteById(Long id) {
		if(studentRepository.existsById(id) == false) {
			throw new StudentServiceException(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
		studentRepository.deleteById(id);
	}

	@Override
	public StudentDTO findByEmail(String email) {
		try {
			StudentEntity student = studentRepository.findByEmail(email);
			return objectMapper.map(student, StudentDTO.class);
		} catch (Exception e) {
			throw new StudentServiceException(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public boolean existsByEmail(String email) {
		return studentRepository.existsByEmail(email);
	}

}
