package com.oliinyk.service.impl;

import static com.oliinyk.constants.ErrorMessages.RECORD_ALREADY_EXISTS;
import static com.oliinyk.constants.ErrorMessages.RECORD_NOT_FOUND;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.oliinyk.domain.SongDTO;
import com.oliinyk.entity.SongEntity;
import com.oliinyk.exceptions.SongServiceException;
import com.oliinyk.repository.SongRepository;
import com.oliinyk.service.SongService;
import com.oliinyk.service.impl.utils.ObjectMapperUtils;

@Service
public class SongServiceImpl implements SongService {

	@Autowired
	private SongRepository songRepository;
	
	@Autowired
	private ObjectMapperUtils objectMapper;
	
	@Override
	public void save(SongDTO song) {
		if(songRepository.existsByName(song.getName()) == true) {
			throw new SongServiceException(RECORD_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
		}
		songRepository.save(objectMapper.map(song, SongEntity.class));
	}

	@Override
	public SongDTO findById(Long id) {
		try {
			SongEntity song = songRepository.findById(id).get();
			return objectMapper.map(song, SongDTO.class);
		} catch (Exception e) {
			throw new SongServiceException(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<SongDTO> findAll() {
		return objectMapper.mapAll(songRepository.findAll(), SongDTO.class);
	}

	@Override
	public void deleteById(Long id) {
		if(songRepository.existsById(id) == false) {
			throw new SongServiceException(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
		songRepository.deleteById(id);
	}

	@Override
	public SongDTO findByName(String name) {
		try {
			SongEntity song = songRepository.findByName(name);
			return objectMapper.map(song, SongDTO.class);
		} catch (Exception e) {
			throw new SongServiceException(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}		

	@Override
	public boolean existsByName(String name) {
		return songRepository.existsByName(name);
	}

}
