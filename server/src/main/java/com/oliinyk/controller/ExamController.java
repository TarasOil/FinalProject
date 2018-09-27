package com.oliinyk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oliinyk.domain.ExamDTO;
import com.oliinyk.service.ExamService;

@RestController
@RequestMapping("exams")
public class ExamController {

	@Autowired
	private ExamService examService;
	
	@PostMapping
	public ResponseEntity<Void> addExam(@RequestBody ExamDTO exam) {
		examService.save(exam);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<ExamDTO>> getExams() {
		return new ResponseEntity<List<ExamDTO>>(examService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{examId}")
	public ResponseEntity<ExamDTO> getExamById(@PathVariable("examId") Long id) {
		return new ResponseEntity<ExamDTO>(examService.findById(id), HttpStatus.OK);
	}
	
	@PutMapping("/{examId}")
	public ResponseEntity<Void> editExam(@PathVariable("examId") Long id, @RequestBody ExamDTO examIn) {
		ExamDTO exam = examService.findById(id);
		if (exam != null) {
			examIn.setId(exam.getId());
			examService.save(examIn);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{examId}")
	public ResponseEntity<Void> deleteExam(@PathVariable("examId") Long id) {
		examService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
