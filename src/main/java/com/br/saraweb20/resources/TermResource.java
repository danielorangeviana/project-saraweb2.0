package com.br.saraweb20.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.saraweb20.entities.Term;
import com.br.saraweb20.service.TermService;

@RestController
@RequestMapping(value = "/terms")
public class TermResource {
	
	@Autowired
	private TermService service;
	
	@GetMapping
	public ResponseEntity<List<Term>> findAll() {
		List<Term> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Term> findById(@PathVariable Long id) {
		Term obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
