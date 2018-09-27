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

import com.oliinyk.domain.ScheduleDTO;
import com.oliinyk.service.ScheduleService;

@RestController
@RequestMapping("schedule")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;
	
	@PostMapping
	public ResponseEntity<Void> addSchedule(@RequestBody ScheduleDTO schedule) {
		scheduleService.save(schedule);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<ScheduleDTO>> getSchedules() {
		return new ResponseEntity<List<ScheduleDTO>>(scheduleService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{scheduleId}")
	public ResponseEntity<ScheduleDTO> getScheduleById(@PathVariable("scheduleId") Long id) {
		return new ResponseEntity<ScheduleDTO>(scheduleService.findById(id), HttpStatus.OK);
	}
	
	@PutMapping("/{scheduleId}")
	public ResponseEntity<Void> editSchedule(@PathVariable("scheduleId") Long id, @RequestBody ScheduleDTO scheduleIn) {
		ScheduleDTO schedule = scheduleService.findById(id);
		if (schedule != null) {
			scheduleIn.setId(schedule.getId());
			scheduleService.save(scheduleIn);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{scheduleId}")
	public ResponseEntity<Void> deleteSchedule(@PathVariable("scheduleId") Long id) {
		scheduleService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
