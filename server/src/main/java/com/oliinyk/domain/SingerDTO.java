package com.oliinyk.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SingerDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String alias;
	@JsonIgnore
	private List<SongDTO> songs;
}
