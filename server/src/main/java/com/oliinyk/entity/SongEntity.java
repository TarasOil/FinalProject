package com.oliinyk.entity;

import javax.persistence.Column;
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
@Table(name = "songs")
public class SongEntity extends BaseEntity {

	@Column(nullable = false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "singer_id", nullable = false)
	private SingerEntity singer;
}
