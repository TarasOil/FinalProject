package com.oliinyk.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private int age;
	private String phone;
	private String email;
	private TeacherDTO teacher;
}
