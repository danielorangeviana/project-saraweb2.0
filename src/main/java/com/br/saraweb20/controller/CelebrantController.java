package com.br.saraweb20.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.saraweb20.entities.Celebrant;
import com.br.saraweb20.service.CelebrantService;

@RestController
@RequestMapping(value = "/celebrants")
public class CelebrantController {
	
	@Autowired
	private CelebrantService service;
	
	@GetMapping
	public ResponseEntity<List<Celebrant>> findAll() {
		List<Celebrant> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Celebrant> findById(@PathVariable Long id) {
		Celebrant obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "findByName")
	public ResponseEntity<List<Celebrant>> findByName(@RequestParam(name = "name") String name) {
		List<Celebrant> list = service.findByName(name.trim().toUpperCase());
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Celebrant> insert(@RequestBody Celebrant obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
