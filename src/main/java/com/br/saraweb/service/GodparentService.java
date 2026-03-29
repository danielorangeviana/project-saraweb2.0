package com.br.saraweb.service;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.saraweb.dto.GodparentDTO;
import com.br.saraweb.entities.Godparent;
import com.br.saraweb.repositories.GodparentRepository;
import com.br.saraweb.exceptions.DatabaseException;
import com.br.saraweb.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class GodparentService {

	private final GodparentRepository godparentRepository;
	
	@Transactional(readOnly = true)
	public Page<GodparentDTO> findAllPaged(Pageable pageable) {
		Page<Godparent> list = godparentRepository.findAll(pageable);
		return list.map(element -> new GodparentDTO(element));
	}

	@Transactional(readOnly = true)
	public GodparentDTO findById(Long id) {
		Optional<Godparent> obj = godparentRepository.findById(id);
		Godparent entity = obj.orElseThrow(() -> new ResourceNotFoundException("Godparent not found!"));
		return new GodparentDTO(entity);
	}

	@Transactional
	public GodparentDTO insert(GodparentDTO dto) {
		Godparent entity = new Godparent();
		copyDtoToEntity(dto, entity);
		entity = godparentRepository.save(entity);
		return new GodparentDTO(entity);
	}
	
	@Transactional
	public GodparentDTO update(Long id, GodparentDTO dto) {
		
		godparentRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("Godparent not found!"));
		
		try {
			Godparent entity = godparentRepository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = godparentRepository.save(entity);
			return new GodparentDTO(entity);
		} 
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Godparent not found!");
		}		
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		godparentRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("Godparent not found!"));
		
		try {
			godparentRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException exception) {
			throw new DatabaseException(exception.getMessage());
		}
	}
	
	private void copyDtoToEntity(GodparentDTO dto, Godparent entity) {

		entity.setGodfather(dto.getGodfather());
		entity.setGodmother(dto.getGodmother());
		
	}
}
