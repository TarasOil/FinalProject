package com.oliinyk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oliinyk.entity.ExamEntity;

public interface ExamRepository extends JpaRepository<ExamEntity, Long> {

}
