package com.oliinyk.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeacherDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private int age;
	private String phone;
	private String email;
	private String education;
	private String experience;
	private String photo;
	@JsonIgnore
	private List<StudentDTO> students;
}
