package com.oliinyk.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "teachers")
public class TeacherEntity extends BaseEntity {

	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	private int age;
	@Column(nullable = false, unique = true)
	private String phone;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String education;
	private String experience;
	private String photo;
	
	@OneToMany(mappedBy = "teacher")
	private List<StudentEntity> students;
	
}
