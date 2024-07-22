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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.saraweb20.dto.BaptismDataDTO;
import com.br.saraweb20.dto.BaptismDataMinDTO;
import com.br.saraweb20.service.BaptismDataService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/baptisms")
public class BaptismDataController {
	
	@Autowired
	private BaptismDataService service;
	
	@GetMapping
	public ResponseEntity<Page<BaptismDataMinDTO>> findAll(
			@RequestParam(name = "nameChildren", defaultValue = "") String nameChildren, 
			Pageable pageable) {
		
		Page<BaptismDataMinDTO> page = service.findAllPaged(nameChildren, pageable);
		
		return ResponseEntity.ok().body(page);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<BaptismDataMinDTO> findById(@PathVariable Long id) {
		BaptismDataMinDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<BaptismDataDTO> insert(@Valid @RequestBody BaptismDataDTO dto) {
		BaptismDataDTO newDto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<BaptismDataDTO> update(@PathVariable Long id, @Valid @RequestBody BaptismDataDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
