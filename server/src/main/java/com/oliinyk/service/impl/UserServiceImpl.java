package com.oliinyk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oliinyk.config.jwt.JWTTokenProvider;
import com.oliinyk.domain.UserDTO;
import com.oliinyk.entity.UserEntity;
import com.oliinyk.entity.enums.UserRole;
import com.oliinyk.exceptions.UserServiceException;
import com.oliinyk.repository.UserRepository;
import com.oliinyk.service.UserService;
import com.oliinyk.service.impl.utils.ObjectMapperUtils;
import static com.oliinyk.constants.ErrorMessages.*;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ObjectMapperUtils objectMapper;
	
	@Autowired
	private JWTTokenProvider jwtTokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public void save(UserDTO user) {
		if (userRepository.existsByUsername(user.getUsername())) {
			throw new UserServiceException(RECORD_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
		}
		user.setRole(UserRole.ROLE_TEACHER);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(objectMapper.map(user, UserEntity.class));	
	}

	@Override
	public UserDTO findById(Long id) {
		try {
			UserEntity user = userRepository.findById(id).get();
			return objectMapper.map(user, UserDTO.class);
		} catch(Exception e) {
			throw new UserServiceException(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<UserDTO> findAll() {
		return objectMapper.mapAll(userRepository.findAll(), UserDTO.class);
	}

	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public UserDTO findByUsername(String username) {
		try {
			UserEntity user = userRepository.findByUsername(username);
			return objectMapper.map(user, UserDTO.class);
		} catch(Exception e) {
			throw new UserServiceException(RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public String signin(String username, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRole());
	}

	
}
