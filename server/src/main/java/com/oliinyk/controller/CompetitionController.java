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

import com.oliinyk.domain.CompetitionDTO;
import com.oliinyk.service.CompetitionService;

@RestController
@RequestMapping("competitions")
public class CompetitionController {

	@Autowired
	private CompetitionService competitionService;
	
	@PostMapping
	public ResponseEntity<Void> addCompetition(@RequestBody CompetitionDTO competition) {
		competitionService.save(competition);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<CompetitionDTO>> getCompetitions() {
		return new ResponseEntity<List<CompetitionDTO>>(competitionService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{competitionId}")
	public ResponseEntity<CompetitionDTO> getCompetitionById(@PathVariable("competitionId") Long id) {
		return new ResponseEntity<CompetitionDTO>(competitionService.findById(id), HttpStatus.OK);
	}
	
	@PutMapping("/{competitionId}")
	public ResponseEntity<Void> editCompetition(@PathVariable("competitionId") Long id, @RequestBody CompetitionDTO competitionIn) {
		CompetitionDTO competition = competitionService.findById(id);
		if (competition != null) {
			competitionIn.setId(competition.getId());
			competitionService.save(competitionIn);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{competitionId}")
	public ResponseEntity<Void> deleteCompetition(@PathVariable("competitionId") Long id) {
		competitionService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
