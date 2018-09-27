package com.oliinyk.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
	
	ROLE_TEACHER, ROLE_ADMIN;
	
	@Override
	public String getAuthority() {
		return name();
	}
	
}
