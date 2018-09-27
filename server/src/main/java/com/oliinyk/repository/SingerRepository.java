package com.oliinyk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oliinyk.entity.SingerEntity;

public interface SingerRepository extends JpaRepository<SingerEntity, Long> {

}
