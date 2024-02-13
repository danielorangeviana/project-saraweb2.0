package com.br.saraweb20.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.saraweb20.entities.BaptismData;
import com.br.saraweb20.service.BaptismDataService;

@RestController
@RequestMapping(value = "/baptisms")
public class BaptismDataResource {
	
	@Autowired
	private BaptismDataService service;
	
	@GetMapping
	public ResponseEntity<List<BaptismData>> findAll() {
		List<BaptismData> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<BaptismData> findById(@PathVariable Long id) {
		BaptismData obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "findByName")
	public ResponseEntity<List<BaptismData>> findByName(@RequestParam(name = "nameChildren") String nameChildren) {
		List<BaptismData> list = service.findByName(nameChildren.trim().toUpperCase());
		return ResponseEntity.ok().body(list);
	}
	
}
