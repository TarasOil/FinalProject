package com.oliinyk.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SongDTO {

	private Long id;
	private String name;
	private SingerDTO singer;
}
