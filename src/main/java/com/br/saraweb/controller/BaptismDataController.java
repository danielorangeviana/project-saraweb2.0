package com.br.saraweb.controller;

import java.net.URI;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.saraweb.dto.BaptismDataDTO;
import com.br.saraweb.dto.BaptismDataMinDTO;
import com.br.saraweb.dto.ScheduleBaptismDTO;
import com.br.saraweb.service.BaptismDataService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/baptisms")
@RequiredArgsConstructor
public class BaptismDataController {

	private final BaptismDataService service;

	@GetMapping
	public ResponseEntity<Page<BaptismDataMinDTO>> findAllByName(
			@RequestParam(name = "nameChildren", defaultValue = "") String nameChildren, 
			Pageable pageable) {
		
		Page<BaptismDataMinDTO> page = service.findAllPagedByName(nameChildren, pageable);
		
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

	@PatchMapping("/{id}/schedule")
	public ResponseEntity<BaptismDataDTO> scheduleDate(@PathVariable Long id, @RequestBody ScheduleBaptismDTO newDateBaptism) {
	    BaptismDataDTO updatedDateBaptism = service.scheduleDateBaptism(id, newDateBaptism);
	    return ResponseEntity.ok(updatedDateBaptism);
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
