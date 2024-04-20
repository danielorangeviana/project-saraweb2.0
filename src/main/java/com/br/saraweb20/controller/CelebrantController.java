package com.br.saraweb20.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.saraweb20.dto.CelebrantDTO;
import com.br.saraweb20.service.CelebrantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/celebrants")
public class CelebrantController {
	
	@Autowired
	private CelebrantService service;
	
	@GetMapping
	public ResponseEntity<Page<CelebrantDTO>> findAll(Pageable pageable) {
		Page<CelebrantDTO> list = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CelebrantDTO> findById(@PathVariable Long id) {
		CelebrantDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	//@GetMapping(value = "findByName")
	//public ResponseEntity<List<Celebrant>> findByName(@RequestParam(name = "name") String name) {
	//	List<Celebrant> list = service.findByName(name.trim().toUpperCase());
	//	return ResponseEntity.ok().body(list);
	//}
	
	@PostMapping
	public ResponseEntity<CelebrantDTO> insert(@Valid @RequestBody CelebrantDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<CelebrantDTO> update(@PathVariable Long id, @Valid @RequestBody CelebrantDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
