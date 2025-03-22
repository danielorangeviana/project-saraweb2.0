package com.br.saraweb20.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.saraweb20.dto.GodparentDTO;
import com.br.saraweb20.entities.Godparent;
import com.br.saraweb20.repositories.GodparentRepository;
import com.br.saraweb20.service.exceptions.DatabaseException;
import com.br.saraweb20.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GodparentService {
	
	@Autowired
	private GodparentRepository godparentRepository;
	
	@Transactional(readOnly = true)
	public Page<GodparentDTO> findAllPaged(Pageable pageable) {
		Page<Godparent> list = godparentRepository.findAll(pageable);
		return list.map(element -> new GodparentDTO(element));
	}

	@Transactional(readOnly = true)
	public GodparentDTO findById(Long id) {
		Optional<Godparent> obj = godparentRepository.findById(id);
		Godparent entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
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
				  .orElseThrow(() -> new ResourceNotFoundException(id));
		
		try {
			Godparent entity = godparentRepository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = godparentRepository.save(entity);
			return new GodparentDTO(entity);
		} 
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}		
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		godparentRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException(id));
		
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
