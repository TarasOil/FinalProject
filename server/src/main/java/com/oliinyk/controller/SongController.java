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

import com.oliinyk.domain.SongDTO;
import com.oliinyk.service.SongService;

@RestController
@RequestMapping("songs")
public class SongController {

	@Autowired
	private SongService songService;
	
	@PostMapping
	public ResponseEntity<Void> addTeacher(@RequestBody SongDTO song) {
		songService.save(song);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<SongDTO>> getSongs() {
		return new ResponseEntity<List<SongDTO>>(songService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{songId}")
	public ResponseEntity<SongDTO> getSongById(@PathVariable("songId") Long id) {
		return new ResponseEntity<SongDTO>(songService.findById(id), HttpStatus.OK);
	}
	
	@PutMapping("/{songId}")
	public ResponseEntity<Void> editSong(@PathVariable("songId") Long id, @RequestBody SongDTO songIn) {
		SongDTO song = songService.findById(id);
		if (song != null) {
			songIn.setId(song.getId());
			songService.save(songIn);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{songId}")
	public ResponseEntity<Void> deleteSong(@PathVariable("teacherId") Long id) {
		songService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
