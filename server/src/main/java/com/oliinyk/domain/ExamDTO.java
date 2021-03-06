package com.oliinyk.domain;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExamDTO {

	private Long id;
	private StudentDTO student;
	private SongDTO song;
	private LocalDate date;
}
