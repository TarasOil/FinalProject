package com.oliinyk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oliinyk.entity.ScheduleEntity;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long>{

}
