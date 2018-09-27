package com.oliinyk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oliinyk.entity.CompetitionEntity;

public interface CompetitionRepository extends JpaRepository<CompetitionEntity, Long> {

}
