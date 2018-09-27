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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.oliinyk.domain.TeacherDTO;
import com.oliinyk.service.TeacherService;

@RestController
@RequestMapping("teachers")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	@PostMapping
	public ResponseEntity<TeacherDTO> addTeacher(@RequestBody TeacherDTO teacher) {
		teacherService.save(teacher);
		return new ResponseEntity<TeacherDTO>(teacherService.findByEmail(teacher.getEmail()), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<TeacherDTO>> getTeachers() {
		return new ResponseEntity<List<TeacherDTO>>(teacherService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{teacherId}")
	public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable("teacherId") Long id) {
		return new ResponseEntity<TeacherDTO>(teacherService.findById(id), HttpStatus.OK);
	}
	
	@PutMapping("/{teacherId}")
	public ResponseEntity<Void> editTeacher(@PathVariable("teacherId") Long id, @RequestBody TeacherDTO teacherIn) {
		TeacherDTO teacher = teacherService.findById(id);
		if (teacher != null) {
			teacherIn.setId(teacher.getId());
			teacherService.save(teacherIn);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{teacherId}")
	public ResponseEntity<Void> deleteTeacher(@PathVariable("teacherId") Long id) {
		teacherService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/photo/{teacherId}")
	public ResponseEntity<Void> uploadPhoto(@PathVariable("teacherId") Long teacherId, @RequestParam("photo") MultipartFile photo) {
		
		teacherService.uploadPhoto(photo, teacherId);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	
}
