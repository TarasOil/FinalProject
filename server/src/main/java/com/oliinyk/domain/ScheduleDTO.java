package com.oliinyk.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScheduleDTO {

	private Long id;
	private TeacherDTO teacher;
	private StudentDTO student;
	private LocalDate date;
	private LocalTime timeFrom;
	private LocalTime timeTo;
}
