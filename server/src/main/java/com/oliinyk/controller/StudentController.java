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

import com.oliinyk.domain.StudentDTO;
import com.oliinyk.service.StudentService;

@RestController
@RequestMapping("students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping
	public ResponseEntity<Void> addStudent(@RequestBody StudentDTO student) {
		studentService.save(student);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<StudentDTO>> getStudents() {
		return new ResponseEntity<List<StudentDTO>>(studentService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{studentId}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable("studentId") Long id) {
		return new ResponseEntity<StudentDTO>(studentService.findById(id), HttpStatus.OK);
	}
	
	@PutMapping("/{studentId}")
	public ResponseEntity<Void> editStudent(@PathVariable("studentId") Long id, @RequestBody StudentDTO studentIn) {
		StudentDTO student = studentService.findById(id);
		if (student != null) {
			studentIn.setId(student.getId());
			studentService.save(studentIn);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{studentId}")
	public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") Long id) {
		studentService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
