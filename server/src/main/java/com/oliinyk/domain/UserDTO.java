package com.oliinyk.domain;

import com.oliinyk.entity.enums.UserRole;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

	private Long id;	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private UserRole role;
}
