package com.br.saraweb20.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.saraweb20.dto.BaptismDataDTO;
import com.br.saraweb20.entities.BaptismData;
import com.br.saraweb20.repositories.BaptismDataRepository;
import com.br.saraweb20.service.exceptions.DatabaseException;
import com.br.saraweb20.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BaptismDataService {
	
	@Autowired
	private BaptismDataRepository repository;
	
	@Transactional(readOnly = true)
	public Page<BaptismDataDTO> findAllPaged(Pageable pageable) {
		Page<BaptismData> list = repository.findAll(pageable);
		return list.map(x -> new BaptismDataDTO(x));
	}

	@Transactional(readOnly = true)
	public BaptismDataDTO findById(Long id) {
		Optional<BaptismData> obj = repository.findById(id);
		BaptismData entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new BaptismDataDTO(entity);
	}

	@Transactional
	public BaptismDataDTO insert(BaptismDataDTO dto) {
		BaptismData entity = new BaptismData();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new BaptismDataDTO(entity);
	}

	@Transactional
	public BaptismDataDTO update(Long id, BaptismDataDTO dto) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException(id);
		}
		try {
			BaptismData entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new BaptismDataDTO(entity);
		} 
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}		
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException(id);
		}
		try {
			repository.deleteById(id);
		}
		catch (DataIntegrityViolationException exception) {
			throw new DatabaseException(exception.getMessage());
		}
	}
	
	private void copyDtoToEntity(BaptismDataDTO dto, BaptismData entity) {

		entity.setNumberTerm(dto.getNumberTerm());
		entity.setNameChildren(dto.getNameChildren());
		entity.setDateBirth(dto.getDateBirth());
		entity.setFather(dto.getFather());
		entity.setMother(dto.getMother());
		entity.setGodFather(dto.getGodFather());
		entity.setGodMother(dto.getGodMother());
		entity.setDateBaptism(dto.getDateBaptism());
		entity.setBook(dto.getBook());
		entity.setCelebrant(dto.getCelebrant());
	}
}
