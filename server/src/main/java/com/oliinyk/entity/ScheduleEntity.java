package com.oliinyk.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "schedule")
public class ScheduleEntity extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "teacher_id", nullable = false)
	private TeacherEntity teacher;
	
	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	private StudentEntity student;
	
	private LocalDate date;
	
	private LocalTime timeFrom;
	
	private LocalTime timeTo;
	
}
