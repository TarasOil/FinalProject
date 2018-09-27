package com.oliinyk.entity;

import lombok.Setter;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "singers")
public class SingerEntity extends BaseEntity {
	
	private String firstName;
	private String lastName;
	private String alias;
	
	@OneToMany(mappedBy = "singer")
	private List<SongEntity> songs;
}
