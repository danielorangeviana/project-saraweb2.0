package com.br.saraweb20.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.saraweb20.entities.BaptismData;
import com.br.saraweb20.service.BaptismDataService;

@RestController
@RequestMapping(value = "/baptisms")
public class BaptismDataController {
	
	@Autowired
	private BaptismDataService service;
	
	@GetMapping
	public ResponseEntity<Page<BaptismData>> findAll(@RequestParam (name = "page", defaultValue = "1") int page) {
		Page<BaptismData> list = service.findAll(page);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<BaptismData> findById(@PathVariable Long id) {
		BaptismData obj = service.findById(id);
		if (obj != null) {
			return ResponseEntity.ok().body(obj);
		} 
			return ResponseEntity.notFound().build();
		
	}
	
	@GetMapping(value = "findByName")
	public ResponseEntity<List<BaptismData>> findByName(@RequestParam(name = "nameChildren") String nameChildren) {
		List<BaptismData> list = service.findByName(nameChildren.trim().toUpperCase());
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<BaptismData> insert(@RequestBody BaptismData obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
