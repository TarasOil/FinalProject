package com.oliinyk.service.impl;

import static com.oliinyk.constants.ErrorMessages.RECORD_ALREADY_EXISTS;
import static com.oliinyk.constants.ErrorMessages.RECORD_NOT_FOUND;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.oliinyk.domain.TeacherDTO;
import com.oliinyk.entity.TeacherEntity;
import com.oliinyk.exceptions.TeacherServiceException;
import com.oliinyk.repository.TeacherRepository;
import com.oliinyk.service.TeacherService;
import com.oliinyk.service.cloudinary.CloudinaryService;
import com.oliinyk.service.impl.utils.ObjectMapperUtils;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private ObjectMapperUtils objectMapper;
	
	@Autowired
	private CloudinaryService cloudinaryService;
	
	@Override
	public void save(TeacherDTO teacher) {
		if(teacherRepository.existsByEmail(teacher.getEmail()) == true) {
			throw new TeacherServiceException(RECORD_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
		}
		teacherRepository.save(objectMapper.map(teacher, TeacherEntity.class));
	}

	@Override
	public TeacherDTO findById(Long id) {
		try {
			TeacherEntity teacher = teacherRepository.findById(id).get();
			return objectMapper.map(teacher, TeacherDTO.class);
		} catch(Exception e) {
			throw new TeacherServiceException(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<TeacherDTO> findAll() {
		return objectMapper.mapAll(teacherRepository.findAll(), TeacherDTO.class);
	}

	@Override
	public void deleteById(Long id) {
		if(teacherRepository.existsById(id) == false) {
			throw new TeacherServiceException(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
		teacherRepository.deleteById(id);
	}
	
	@Override
	public TeacherDTO findByEmail(String email) {
		try {
			TeacherEntity teacher = teacherRepository.findByEmail(email);
			return objectMapper.map(teacher, TeacherDTO.class);
		} catch (Exception e) {
			throw new TeacherServiceException(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
		}	
	}
	
	@Override
	public boolean existsByEmail(String email) {
		if(teacherRepository.findByEmail(email) != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public void uploadPhoto(MultipartFile photo, Long id) {
		String photoUrl = cloudinaryService.uploadFile(photo, "teachers");
		
		TeacherEntity teacherEntity = teacherRepository.findById(id).get();
		if(teacherEntity == null) {
			throw new TeacherServiceException(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
		
		teacherEntity.setPhoto(photoUrl);
		teacherRepository.save(teacherEntity);
	}
}
