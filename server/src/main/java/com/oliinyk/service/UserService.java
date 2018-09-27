package com.oliinyk.service;

import java.util.List;

import com.oliinyk.domain.UserDTO;

public interface UserService {

	void save(UserDTO user);
	
	UserDTO findById(Long id);
	
	List<UserDTO> findAll();
	
	boolean existsByUsername(String username);
	
	UserDTO findByUsername(String username);
	
	String signin(String username, String password);
}
