package com.oliinyk.entity;

import java.time.LocalDate;

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
@Table(name = "competitions")
public class CompetitionEntity extends BaseEntity {

	private String name;
	
	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	private StudentEntity student;
	
	@ManyToOne
	@JoinColumn(name = "song_id", nullable = false)
	private SongEntity song;
	
	private LocalDate date;
	
	private String city;
}
