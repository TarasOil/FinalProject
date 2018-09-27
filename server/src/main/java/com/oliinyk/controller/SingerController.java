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

import com.oliinyk.domain.SingerDTO;
import com.oliinyk.service.SingerService;

@RestController
@RequestMapping("singers")
public class SingerController {

	@Autowired
	private SingerService singerService;
	
	@PostMapping
	public ResponseEntity<Void> addSinger(@RequestBody SingerDTO singer) {
		singerService.save(singer);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<SingerDTO>> getSingers() {
		return new ResponseEntity<List<SingerDTO>>(singerService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{singerId}")
	public ResponseEntity<SingerDTO> getSingerById(@PathVariable("singerId") Long id) {
		return new ResponseEntity<SingerDTO>(singerService.findById(id), HttpStatus.OK);
	}
	
	@PutMapping("/{singerId}")
	public ResponseEntity<Void> editSinger(@PathVariable("singerId") Long id, @RequestBody SingerDTO singerIn) {
		SingerDTO singer = singerService.findById(id);
		if (singer != null) {
			singerIn.setId(singer.getId());
			singerService.save(singerIn);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{singerId}")
	public ResponseEntity<Void> deleteSinger(@PathVariable("singerId") Long id) {
		singerService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
