package com.br.saraweb20.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.saraweb20.dto.ParentDTO;
import com.br.saraweb20.entities.Parent;
import com.br.saraweb20.repositories.ParentRepository;
import com.br.saraweb20.service.exceptions.DatabaseException;
import com.br.saraweb20.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ParentService {
	
	@Autowired
	private ParentRepository parentRepository;
	
	@Transactional(readOnly = true)
	public Page<ParentDTO> findAllPaged(Pageable pageable) {
		Page<Parent> list = parentRepository.findAll(pageable);
		return list.map(element -> new ParentDTO(element));
	}

	@Transactional(readOnly = true)
	public ParentDTO findById(Long id) {
		Optional<Parent> obj = parentRepository.findById(id);
		Parent entity = obj.orElseThrow(() -> new ResourceNotFoundException("Parent not found!"));
		return new ParentDTO(entity);
	}

	@Transactional
	public ParentDTO insert(ParentDTO dto) {
		Parent entity = new Parent();
		copyDtoToEntity(dto, entity);
		entity = parentRepository.save(entity);
		return new ParentDTO(entity);
	}
	
	@Transactional
	public ParentDTO update(Long id, ParentDTO dto) {
		
		parentRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("Parent not found!"));
		
		try {
			Parent entity = parentRepository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = parentRepository.save(entity);
			return new ParentDTO(entity);
		} 
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Parent not found!");
		}		
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		parentRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("Parent not found!"));
		
		try {
			parentRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException exception) {
			throw new DatabaseException(exception.getMessage());
		}
	}
	
	private void copyDtoToEntity(ParentDTO dto, Parent entity) {

		entity.setFather(dto.getFather());
		entity.setMother(dto.getMother());
	}
}
