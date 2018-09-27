package com.oliinyk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oliinyk.entity.SongEntity;

public interface SongRepository extends JpaRepository<SongEntity, Long> {

	SongEntity findByName(String name);
	
	boolean existsByName(String name);
}
